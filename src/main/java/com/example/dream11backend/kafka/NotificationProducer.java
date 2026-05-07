package com.example.dream11backend.kafka;

import com.example.dream11backend.config.KafkaTopicConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("null")
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public void sendNotification(NotificationEvent event) {
        event.setTimestamp(LocalDateTime.now());

        CompletableFuture<SendResult<String, NotificationEvent>> future =
                kafkaTemplate.send(KafkaTopicConfig.NOTIFICATION_TOPIC, event.getUsername(), event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Failed to send notification event [{}] for user [{}]: {}",
                        event.getEventType(), event.getUsername(), ex.getMessage());
            } else {
                log.info("Notification sent → topic={}, partition={}, offset={}, type={}, user={}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset(),
                        event.getEventType(),
                        event.getUsername());
            }
        });
    }

    // ── Convenience factory methods ──────────────────────────────────────────

    public void notifyUserRegistered(String username, String email) {
        sendNotification(NotificationEvent.builder()
                .eventType(NotificationEvent.USER_REGISTERED)
                .username(username)
                .email(email)
                .message("Welcome to Dream11! Your account has been created successfully.")
                .build());
    }

    public void notifyContestJoined(String username, String email,
                                    Long contestId, String contestName, Double entryFee) {
        sendNotification(NotificationEvent.builder()
                .eventType(NotificationEvent.CONTEST_JOINED)
                .username(username)
                .email(email)
                .contestId(contestId)
                .contestName(contestName)
                .amount(entryFee)
                .message(String.format("You have successfully joined contest '%s'. Entry fee: ₹%.2f",
                        contestName, entryFee))
                .build());
    }

    public void notifyContestLeft(String username, String email,
                                  Long contestId, String contestName, Double refundAmount) {
        sendNotification(NotificationEvent.builder()
                .eventType(NotificationEvent.CONTEST_LEFT)
                .username(username)
                .email(email)
                .contestId(contestId)
                .contestName(contestName)
                .amount(refundAmount)
                .message(String.format("You have left contest '%s'. ₹%.2f has been refunded to your wallet.",
                        contestName, refundAmount))
                .build());
    }

    public void notifyContestCreated(String adminUsername, Long contestId, String contestName) {
        sendNotification(NotificationEvent.builder()
                .eventType(NotificationEvent.CONTEST_CREATED)
                .username(adminUsername)
                .contestId(contestId)
                .contestName(contestName)
                .message(String.format("Contest '%s' has been created successfully.", contestName))
                .build());
    }

    public void notifyContestDeleted(String adminUsername, Long contestId, String contestName) {
        sendNotification(NotificationEvent.builder()
                .eventType(NotificationEvent.CONTEST_DELETED)
                .username(adminUsername)
                .contestId(contestId)
                .contestName(contestName)
                .message(String.format("Contest '%s' has been deleted.", contestName))
                .build());
    }
}
