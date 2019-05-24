package org.nil.learning.service;

import java.util.Optional;

import org.nil.learning.dao.TicketBookingDao;
import org.nil.learning.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TicketBookingService {

	@Autowired
	TicketBookingDao bookingDao;
	
	@Cacheable(value = "tickets-cache", key = "#ticketId", unless = "#result == null")
	public Ticket getTicketById(Integer ticketId) {
		return bookingDao.findById(ticketId).get();
	}
	
	@CacheEvict(value = "tickets-cache", key = "#ticketId")
	public void deleteTicket(Integer ticketId) {
		bookingDao.deleteById(ticketId);
	}
	
	@CachePut(value = "tickets-cache", key = "#ticketId")
	public Ticket updateTicket(Integer ticketId, String newEmail){
		Optional<Ticket> dbTicket = bookingDao.findById(ticketId);
		if (dbTicket.isPresent()) {
			dbTicket.get().setEmail(newEmail);
			return bookingDao.save(dbTicket.get());
		}
		return null;
	}
}
