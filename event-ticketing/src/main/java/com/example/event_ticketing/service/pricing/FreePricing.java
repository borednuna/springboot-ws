package com.example.event_ticketing.service.pricing;

import com.example.event_ticketing.enums.TicketType;

public class FreePricing implements TicketPricing {

    @Override
    public boolean supports(TicketType type) {
        return type == TicketType.FREE;
    }

    @Override
    public int calculatePrice() {
        return 0;
    }
}
