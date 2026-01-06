package com.example.event_ticketing.controller;

import org.springframework.stereotype.Component;

import com.example.event_ticketing.model.Ticket;
import com.example.event_ticketing.service.TicketService;

@Component
public class BookingController {

    private final TicketService ticketService;

    public BookingController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public void bookTicket(Ticket ticket) {
        System.out.println("\n--- Booking started ---");
        ticketService.book(ticket);
        System.out.println("--- Booking finished ---\n");
    }
}
