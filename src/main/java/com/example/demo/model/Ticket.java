package com.example.demo.model;

import jakarta.persistence.*; // Импортируем JPA
import java.time.LocalDateTime;

@Entity // 1. Скажи: "Этот класс будет таблицей в БД"
@Table(name = "tickets") // 2. Название таблицы
public class Ticket {

    @Id // 3. Это главный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // База сама даст номер
    private Long id;

    @Column(nullable = false) // Не может быть пустым
    private String title;      // Тема заявки

    @Column(nullable = false, length = 1000)
    private String description; // Описание проблемы

    private String customerName; // Имя клиента

    private LocalDateTime createdAt; // Дата создания

    public Ticket() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
    @PrePersist
    public void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = TicketStatus.NEW;
        }
    }
}
