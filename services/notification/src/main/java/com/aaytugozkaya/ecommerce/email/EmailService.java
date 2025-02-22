package com.aaytugozkaya.ecommerce.email;

import com.aaytugozkaya.ecommerce.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(
            String to,
            String customerName,
            BigDecimal amount,
            String orderReference) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(message, MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        helper.setFrom("aaytugozkaya@gmail.com");
        final String template = EmailTemplates.PAYMENT_CONFIRMATION.getTemplateName();
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);
        Context context = new Context();
        context.setVariables(variables);
        helper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());
        try {
            String htmlTemplate = templateEngine.process(template, context);
            helper.setText(htmlTemplate, true);
            helper.setTo(to);
            mailSender.send(message);
            log.info("Email sent to {} with {}", to, htmlTemplate);
        } catch (MessagingException e) {
            log.warn("Failed to send email", e);
            throw new MessagingException("Failed to send email", e);
        }
    }

    @Async
    public void sendOrderConfirmationEmail(
            String to,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(message, MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        helper.setFrom("aaytugozkaya@gmail.com");
        final String template = EmailTemplates.ORDER_CONFIRMATION.getTemplateName();
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);
        Context context = new Context();
        context.setVariables(variables);
        helper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());
        try {
            String htmlTemplate = templateEngine.process(template, context);
            helper.setText(htmlTemplate, true);
            helper.setTo(to);
            mailSender.send(message);
            log.info("Email sent to {} with {}", to, htmlTemplate);
        } catch (MessagingException e) {
            log.warn("Failed to send email", e);
            throw new MessagingException("Failed to send email", e);
        }
    }


}
