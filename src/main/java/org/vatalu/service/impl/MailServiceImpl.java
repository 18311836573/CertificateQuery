package org.vatalu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.service.MailService;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private LinkedBlockingQueue<MimeMessage> mimeMessageLinkedBlockingQueue;

    public void setMimeMessageLinkedBlockingQueue(LinkedBlockingQueue<MimeMessage> mimeMessageLinkedBlockingQueue) {
        this.mimeMessageLinkedBlockingQueue = mimeMessageLinkedBlockingQueue;
    }

    @Value("${spring.mail.username}")
    private String myEmailAccount;

    @Override
    public CommonResponse sendMail(String filename, String name, String mailAccount) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        try {
            //制作邮件
            final MimeMessage message = createMimeMessage(session, mailAccount, filename);
            //将邮件加入队列
            new Thread(() -> {
                try {
                    addMessageToQueue(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            return new CommonResponse(true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new CommonResponse(false);
    }

    private void addMessageToQueue(final MimeMessage message) throws InterruptedException {
        mimeMessageLinkedBlockingQueue.put(message);
    }

    private MimeMessage createMimeMessage(Session session, String receiveMailAccount, String filename) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmailAccount, "获奖证书", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount));
        message.setSubject("计算机大赛获奖证书", "UTF-8");

        message.setSentDate(new Date());

        Multipart multipart = new MimeMultipart("mixed");
        BodyPart contentPart = new MimeBodyPart();
        String content = "获奖证书";
        contentPart.setContent(content, "text/html;charset=utf-8");
        multipart.addBodyPart(contentPart);
        if (filename != null) {
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(new File(filename));
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(MimeUtility.encodeWord("计算机大赛获奖证书.pdf"));
            multipart.addBodyPart(attachmentBodyPart);
        }
        message.setContent(multipart);
        message.saveChanges();

        return message;
    }


}
