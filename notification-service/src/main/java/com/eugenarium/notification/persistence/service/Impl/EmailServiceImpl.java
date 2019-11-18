package com.eugenarium.notification.persistence.service.Impl;

import com.eugenarium.notification.persistence.domain.NotificationType;
import com.eugenarium.notification.persistence.domain.Recipient;
import com.eugenarium.notification.persistence.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.MessageFormat;

@Service
@RefreshScope
public class EmailServiceImpl implements EmailService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final JavaMailSender mailSender;

    private Environment env;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send (NotificationType type, Recipient recipient, String attachment)
            throws MessagingException, IOException {

        final String subject = env.getProperty(type.getSubject());
        final String text = MessageFormat.format(env.getProperty(type.getText()), recipient.getAccountName());

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipient.getEmail());
        helper.setSubject(subject);
        helper.setText(text);

        if (StringUtils.hasLength(attachment)) {
            helper.addAttachment(env.getProperty(type.getAttachment()), new ByteArrayResource(attachment.getBytes()));
        }

        mailSender.send(message);

        LOGGER.info("{} email notification has been send to {}", type, recipient.getEmail());
    }
}
