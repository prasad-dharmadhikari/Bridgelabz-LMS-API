package com.bridgelabz.lmsapi.util;

import com.bridgelabz.lmsapi.dto.MailDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Utility to handle rabbitMQ messages.
 */
@Component
public class RabbitMQ {


    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private JavaMailSender javaMailSender;

    FileSystemResource personalInfoButton = new FileSystemResource(new File("C:\\Users\\personal\\Desktop\\lmsapi\\src\\main\\resources\\personal-info.jpg"));
    FileSystemResource educationalInfoButton = new FileSystemResource(new File("C:\\Users\\personal\\Desktop\\lmsapi\\src\\main\\resources\\educational-info.jpg"));
    FileSystemResource bankInfoButton = new FileSystemResource(new File("C:\\Users\\personal\\Desktop\\lmsapi\\src\\main\\resources\\bank-info.jpg"));
    FileSystemResource acceptedButton = new FileSystemResource(new File("C:\\Users\\personal\\Desktop\\lmsapi\\src\\main\\resources\\accepted.jpg"));
    FileSystemResource rejectedButton = new FileSystemResource(new File("C:\\Users\\personal\\Desktop\\lmsapi\\src\\main\\resources\\rejected.jpg"));

    /**
     * @param message
     */
    // PRODUCER
    public void sendMessageToQueue(MailDTO message) {
        final String exchange = "QueueExchangeConn";
        final String routingKey = "RoutingKey";
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    /**
     * @param message
     */
    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void send(MimeMessage message) {
        javaMailSender.send(message);
    }

    /**
     * @param message
     */
    // listener
    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receiveMessage(MimeMessage message) {
        send(message);
    }

    /**
     * @param mailDTO
     * @throws MessagingException
     */
    public void sendHiringMail(MailDTO mailDTO) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(mailDTO.getTo());
        helper.setText(mailDTO.getBody(), true);
        helper.setSubject(mailDTO.getSubject());
        helper.addInline("identifier1234", acceptedButton);
        helper.addInline("identifier5678", rejectedButton);
        sendMessageToQueue(mailDTO);
        send(message);
    }

    /**
     * @param mailDTO
     * @throws MessagingException
     */
    public void sendJobOfferMail(MailDTO mailDTO) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(mailDTO.getTo());
        helper.setText(mailDTO.getBody(), true);
        helper.setSubject(mailDTO.getSubject());
        helper.addInline("identifier11", personalInfoButton);
        helper.addInline("identifier22", educationalInfoButton);
        helper.addInline("identifier33", bankInfoButton);
        sendMessageToQueue(mailDTO);
        send(message);
    }
}