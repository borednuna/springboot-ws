package com.example.event_ticketing.service;

import java.util.List;
import com.example.event_ticketing.service.pricing.*;
import com.example.event_ticketing.service.notification.*;
import com.example.event_ticketing.service.payment.*;
import com.example.event_ticketing.model.*;

public class TicketService {

    private final List<TicketPricing> pricingStrategies;
    private final PaymentGateway paymentGateway;
    private final NotificationService notificationService;

    public TicketService(
            List<TicketPricing> pricingStrategies,
            PaymentGateway paymentGateway,
            NotificationService notificationService) {
        this.pricingStrategies = pricingStrategies;
        this.paymentGateway = paymentGateway;
        this.notificationService = notificationService;
    }

    public void book(Ticket ticket) {
        TicketPricing pricing = pricingStrategies.stream()
                .filter(p -> p.supports(ticket.getType()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No pricing found"));

        int price = pricing.calculatePrice();

        if (price > 0) {
            paymentGateway.pay(price);
        }

        notificationService.send("Ticket booked: " + ticket.getType());
    }
}
