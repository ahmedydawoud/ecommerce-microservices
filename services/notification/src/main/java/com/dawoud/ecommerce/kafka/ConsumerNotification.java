package com.dawoud.ecommerce.kafka;

import com.dawoud.ecommerce.email.EmailService;
import com.dawoud.ecommerce.kafka.order.OrderConfirmation;
import com.dawoud.ecommerce.kafka.payment.PaymentConfirmation;
import com.dawoud.ecommerce.notification.Notification;
import com.dawoud.ecommerce.notification.NotificationRepository;
import com.dawoud.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import static com.dawoud.ecommerce.notification.NotificationType.ORDER_CONFIRMATION;
import static com.dawoud.ecommerce.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerNotification {

    private final NotificationRepository repository;
    private final EmailService emailService;


    @KafkaListener(topics = "order-topic")
    public void ConsumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order topic::%s", orderConfirmation));

        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        var customerName = orderConfirmation.customer().firstname() + " " +
                orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products());


    }


    @KafkaListener(topics = "payment-topic")
    public void ConsumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from payment topic::%s", paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        var customerName = paymentConfirmation.customerFirstname() + " " +
                paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,paymentConfirmation.amount(),
                paymentConfirmation.orderReference());

    }


}
