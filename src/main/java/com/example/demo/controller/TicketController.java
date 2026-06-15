package com.example.demo.controller;

import com.example.demo.dto.TicketCreateDto;
import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Показать форму создания заявки
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ticket", new TicketCreateDto());
        return "ticket-form";
    }

    // Обработка отправки формы
    @PostMapping
    public String createTicket(
            @Valid @ModelAttribute("ticket") TicketCreateDto ticketDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "ticket-form";
        }

        Ticket savedTicket = ticketService.createTicket(ticketDto);
        return "redirect:/tickets/" + savedTicket.getId() + "/success";
    }

    // Страница благодарности после создания
    @GetMapping("/{id}/success")
    public String showSuccessPage(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "ticket-success";
    }
}
