package com.example.event_ticketing.service.notification;

public class EmailNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}
