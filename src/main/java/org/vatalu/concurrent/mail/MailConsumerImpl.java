package org.vatalu.concurrent.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.vatalu.concurrent.AbstractConsumer;
import org.vatalu.concurrent.Consumer;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class MailConsumerImpl extends AbstractConsumer {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private LinkedBlockingQueue<MimeMessage> messageLinkedBlockingQueue;

    @Override
    public void consume() throws InterruptedException {
        MimeMessage mimeMessage = messageLinkedBlockingQueue.take();
        javaMailSender.send(mimeMessage);
        //每隔1分钟发送一次
        Thread.sleep(60000);
    }
}
