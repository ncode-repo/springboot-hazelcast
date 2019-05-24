package org.nil.learning.dao;

import org.nil.learning.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketBookingDao extends CrudRepository<Ticket, Integer> {

}
