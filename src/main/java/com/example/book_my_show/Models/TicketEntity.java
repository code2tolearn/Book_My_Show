package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
public class TicketEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

     private  String movieName ;

     private LocalDate showDate ;

     private LocalTime showTime ;

     private  int totalAmount ;

    private String ticketId = UUID.randomUUID().toString();

    private String theaterName;

    @JoinColumn
    @ManyToOne
    private UserEntity userEntity;

    //Ticket is child wrt to showEntity
    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;


}
