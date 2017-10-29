package com.certificateQuery.service.impl;

import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.certificateQuery.service.MailService;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


@Service
public class MailServiceImpl implements MailService {

	@Resource
	private TaskExecutor taskExecutor;
	@Resource
	private JavaMailSender javaMailSender;

	private static final String myEmailAccount = "certificateinquire@163.com";

	@Override
	public void sendMail(String filename, String number, String receiveMailAccount) {
		MimeMessage message = javaMailSender.createMimeMessage();
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props);
		session.setDebug(false);
		message = createMimeMessage(session, receiveMailAccount, filename, number);
		addSendMailTask(message);

	}

	private void addSendMailTask(final MimeMessage message) {
		try {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					javaMailSender.send(message);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private MimeMessage createMimeMessage(Session session, String receiveMailAccount, String filename, String number) {
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myEmailAccount, "获奖证书", "UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount));
			message.setSubject("计算机大赛获奖证书" + number, "UTF-8");

			message.setSentDate(new Date());

			Multipart multipart = new MimeMultipart("mixed");
			BodyPart contentPart = new MimeBodyPart();
			String content = new String("获奖证书");
			contentPart.setContent(content, "text/html;charset=utf-8");
			multipart.addBodyPart(contentPart);
			if (filename != null) {
				BodyPart attachmentBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(new File(filename));
				attachmentBodyPart.setDataHandler(new DataHandler(source));
				attachmentBodyPart.setFileName(MimeUtility.encodeWord(number + ".pdf"));
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