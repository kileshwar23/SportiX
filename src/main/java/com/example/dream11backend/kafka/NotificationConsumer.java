package com.example.dream11backend.kafka;

import com.example.dream11backend.config.KafkaTopicConfig;
import com.example.dream11backend.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final EmailService emailService;

    @KafkaListener(
            topics = KafkaTopicConfig.NOTIFICATION_TOPIC,
            groupId = "dream11-notification-group"
    )
    public void consume(
            @Payload NotificationEvent event,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {

        log.info("Notification received ← partition={}, offset={}, type={}, user={}",
                partition, offset, event.getEventType(), event.getUsername());

        processNotification(event);
    }

    private void processNotification(NotificationEvent event) {
        // Skip if no email address
        if (event.getEmail() == null || event.getEmail().isBlank()) {
            log.warn("[NOTIFY] No email for user={}, skipping email send", event.getUsername());
            return;
        }

        switch (event.getEventType()) {

            case NotificationEvent.USER_REGISTERED:
                emailService.sendWelcomeEmail(event.getEmail(), event.getUsername());
                break;

            case NotificationEvent.CONTEST_JOINED:
                emailService.sendContestJoinedEmail(
                        event.getEmail(),
                        event.getUsername(),
                        event.getContestName(),
                        event.getAmount() != null ? event.getAmount() : 0.0);
                break;

            case NotificationEvent.CONTEST_LEFT:
                emailService.sendContestLeftEmail(
                        event.getEmail(),
                        event.getUsername(),
                        event.getContestName(),
                        event.getAmount() != null ? event.getAmount() : 0.0);
                break;

            case NotificationEvent.CONTEST_CREATED:
                emailService.sendContestCreatedEmail(
                        event.getEmail() != null ? event.getEmail() : "",
                        event.getUsername(),
                        event.getContestName());
                break;

            case NotificationEvent.CONTEST_DELETED:
                emailService.sendContestDeletedEmail(
                        event.getEmail() != null ? event.getEmail() : "",
                        event.getUsername(),
                        event.getContestName());
                break;

            default:
                log.warn("[NOTIFY] Unknown event type: {}", event.getEventType());
        }
    }
}
