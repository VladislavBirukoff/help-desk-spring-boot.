package com.example.demo.repository;

import com.example.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JpaRepository<Какой класс, Тип ID>
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Автоматически найдет все и отсортирует по дате
    List<Ticket> findAllByOrderByCreatedAtDesc();
}
