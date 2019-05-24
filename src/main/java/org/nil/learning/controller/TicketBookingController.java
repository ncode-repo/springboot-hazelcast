package org.nil.learning.controller;

import org.nil.learning.entity.Ticket;
import org.nil.learning.service.TicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketBookingController {

	@Autowired
	TicketBookingService bookingService;
	
	@GetMapping(value = "/ticket/{ticketId}")
	public Ticket getTicketById(@PathVariable("ticketId") Integer ticketId) {
		return bookingService.getTicketById(ticketId);
	}
	@DeleteMapping(value = "/ticket/{ticketId}")
	public void deleteTicket(@PathVariable("ticketId") Integer ticketId) {
		bookingService.deleteTicket(ticketId);
	}
	@PutMapping(value = "/ticekt/{ticketId}")
	public Ticket updateTicket(@PathVariable("ticketId") Integer ticketId, @PathVariable("newEmail") String newEmail) {
		return bookingService.updateTicket(ticketId, newEmail);
	}
}
