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

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Value("${spring.mail.username}")
    private  String myEmailAccount;

    @Override
    public CommonResponse sendMail(String filename, String name, String mailAccount) {

        MimeMessage message = mailSender.createMimeMessage();
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        message = createMimeMessage(session, mailAccount, filename);
        addSendMailTask(message);
        return new CommonResponse(true);
    }

    private void addSendMailTask(final MimeMessage message) {
        try {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    mailSender.send(message);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private MimeMessage createMimeMessage(Session session, String receiveMailAccount, String filename) {
        MimeMessage message = new MimeMessage(session);
        try {
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

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }
}
