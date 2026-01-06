package com.example.event_ticketing;

import java.util.List;

import com.example.event_ticketing.controller.BookingController;
import com.example.event_ticketing.model.FreeTicket;
import com.example.event_ticketing.model.RegularTicket;
import com.example.event_ticketing.model.VipTicket;
import com.example.event_ticketing.service.TicketService;
import com.example.event_ticketing.service.notification.EmailNotificationService;
import com.example.event_ticketing.service.notification.NotificationService;
import com.example.event_ticketing.service.payment.PaymentGateway;
import com.example.event_ticketing.service.payment.PaypalPaymentGateway;
import com.example.event_ticketing.service.pricing.FreePricing;
import com.example.event_ticketing.service.pricing.RegularPricing;
import com.example.event_ticketing.service.pricing.TicketPricing;
import com.example.event_ticketing.service.pricing.VipPricing;

public class TicketingApplication {

	public static void main(String[] args) {

		PaymentGateway paymentGateway = new PaypalPaymentGateway();
		NotificationService notificationService = new EmailNotificationService();

		List<TicketPricing> pricingStrategies = List.of(
				new RegularPricing(),
				new VipPricing(),
				new FreePricing());

		TicketService ticketService = new TicketService(
				pricingStrategies,
				paymentGateway,
				notificationService);

		BookingController bookingController = new BookingController(ticketService);

		bookingController.bookTicket(new RegularTicket());
		bookingController.bookTicket(new VipTicket());
		bookingController.bookTicket(new FreeTicket());
	}
}
