package com.example.event_ticketing.service.pricing;

import com.example.event_ticketing.enums.TicketType;

public class RegularPricing implements TicketPricing {

    @Override
    public boolean supports(TicketType type) {
        return type == TicketType.REGULAR;
    }

    @Override
    public int calculatePrice() {
        return 100;
    }
}
