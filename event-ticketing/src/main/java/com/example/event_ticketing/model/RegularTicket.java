package com.example.event_ticketing.model;

import com.example.event_ticketing.enums.TicketType;

public class RegularTicket extends Ticket {
    @Override
    public TicketType getType() {
        return TicketType.REGULAR;
    }
}
