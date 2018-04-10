package org.vatalu.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.vatalu.concurrent.mail.MailConsumerImpl;
import org.vatalu.service.impl.MailServiceImpl;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class MailServiceRunner implements ApplicationRunner {
    @Autowired
    private MailConsumerImpl mailConsumer;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Thread(mailConsumer).start();
    }
}
