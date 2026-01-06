package com.example.event_ticketing.service.pricing;

import com.example.event_ticketing.enums.TicketType;

public interface TicketPricing {
    boolean supports(TicketType type);

    int calculatePrice();
}
