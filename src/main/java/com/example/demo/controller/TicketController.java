package com.example.demo.controller;

import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketController {

    @Autowired // Просим Spring дать нам репозиторий
    private TicketRepository ticketRepository;

    @GetMapping("/tickets")
    public String listTickets(Model model) {
        // Берем из базы список, кладем в модель под именем "tickets"
        model.addAttribute("tickets", ticketRepository.findAllByOrderByCreatedAtDesc());
        return "tickets"; // Покажем страницу tickets.html
    }
}
