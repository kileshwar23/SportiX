package com.example.dream11backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@SuppressWarnings("null")
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.from:Dream11 <no-reply@dream11.com>}")
    private String fromAddress;

    /**
     * Send an HTML email asynchronously so it never blocks the main thread.
     */
    @Async
    public void sendHtmlEmail(String to, String subject, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromAddress);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true = HTML

            mailSender.send(message);
            log.info("Email sent → to={}, subject={}", to, subject);

        } catch (MessagingException e) {
            log.error("Failed to send email to {}: {}", to, e.getMessage());
        }
    }

    // ── Pre-built email templates ─────────────────────────────────────────────

    public void sendWelcomeEmail(String to, String username) {
        String subject = "Welcome to Dream11! 🏏";
        String body = """
                <html><body style="font-family:Arial,sans-serif;color:#333">
                  <h2 style="color:#1a73e8">Welcome to Dream11, %s! 🎉</h2>
                  <p>Your account has been created successfully.</p>
                  <p>You start with <strong>₹1000</strong> in your wallet. Join a contest and start winning!</p>
                  <br>
                  <p style="color:#888;font-size:12px">Dream11 Team</p>
                </body></html>
                """.formatted(username);
        sendHtmlEmail(to, subject, body);
    }

    public void sendContestJoinedEmail(String to, String username,
                                       String contestName, double entryFee) {
        String subject = "You joined " + contestName + " 🏆";
        String body = """
                <html><body style="font-family:Arial,sans-serif;color:#333">
                  <h2 style="color:#1a73e8">Contest Joined Successfully!</h2>
                  <p>Hi <strong>%s</strong>,</p>
                  <p>You have successfully joined <strong>%s</strong>.</p>
                  <table style="border-collapse:collapse;margin:16px 0">
                    <tr>
                      <td style="padding:8px;border:1px solid #ddd"><strong>Contest</strong></td>
                      <td style="padding:8px;border:1px solid #ddd">%s</td>
                    </tr>
                    <tr>
                      <td style="padding:8px;border:1px solid #ddd"><strong>Entry Fee</strong></td>
                      <td style="padding:8px;border:1px solid #ddd">₹%.2f</td>
                    </tr>
                  </table>
                  <p>Good luck! 🤞</p>
                  <p style="color:#888;font-size:12px">Dream11 Team</p>
                </body></html>
                """.formatted(username, contestName, contestName, entryFee);
        sendHtmlEmail(to, subject, body);
    }

    public void sendContestLeftEmail(String to, String username,
                                     String contestName, double refundAmount) {
        String subject = "You left " + contestName;
        String body = """
                <html><body style="font-family:Arial,sans-serif;color:#333">
                  <h2 style="color:#e8710a">Contest Left</h2>
                  <p>Hi <strong>%s</strong>,</p>
                  <p>You have left <strong>%s</strong>.</p>
                  <p>A refund of <strong>₹%.2f</strong> has been credited to your wallet.</p>
                  <p style="color:#888;font-size:12px">Dream11 Team</p>
                </body></html>
                """.formatted(username, contestName, refundAmount);
        sendHtmlEmail(to, subject, body);
    }

    public void sendContestCreatedEmail(String to, String adminUsername, String contestName) {
        String subject = "Contest Created: " + contestName;
        String body = """
                <html><body style="font-family:Arial,sans-serif;color:#333">
                  <h2 style="color:#1a73e8">New Contest Created</h2>
                  <p>Hi <strong>%s</strong>,</p>
                  <p>Contest <strong>%s</strong> has been created successfully and is now live.</p>
                  <p style="color:#888;font-size:12px">Dream11 Team</p>
                </body></html>
                """.formatted(adminUsername, contestName);
        sendHtmlEmail(to, subject, body);
    }

    public void sendContestDeletedEmail(String to, String adminUsername, String contestName) {
        String subject = "Contest Deleted: " + contestName;
        String body = """
                <html><body style="font-family:Arial,sans-serif;color:#333">
                  <h2 style="color:#d93025">Contest Deleted</h2>
                  <p>Hi <strong>%s</strong>,</p>
                  <p>Contest <strong>%s</strong> has been deleted.</p>
                  <p style="color:#888;font-size:12px">Dream11 Team</p>
                </body></html>
                """.formatted(adminUsername, contestName);
        sendHtmlEmail(to, subject, body);
    }
}
