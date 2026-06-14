package com.example.demo.service;

import com.example.demo.dto.TicketCreateDto;
import com.example.demo.model.Ticket;
import java.util.List;

public interface TicketService {
    Ticket createTicket(TicketCreateDto ticketCreateDto);
    List<Ticket> getAllTickets();
    Ticket getTicketById(Long id);
}
