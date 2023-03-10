package com.example.book_my_show.repository;

import com.example.book_my_show.Models.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity , Integer> {
}
