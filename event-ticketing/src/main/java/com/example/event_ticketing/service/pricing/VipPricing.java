package com.example.event_ticketing.service.pricing;

import com.example.event_ticketing.enums.TicketType;

public class VipPricing implements TicketPricing {

    @Override
    public boolean supports(TicketType type) {
        return type == TicketType.VIP;
    }

    @Override
    public int calculatePrice() {
        return 250;
    }
}
