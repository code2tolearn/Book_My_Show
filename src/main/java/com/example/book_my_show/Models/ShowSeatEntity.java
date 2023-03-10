package com.example.book_my_show.Models;

import com.example.book_my_show.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "showseats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

     private boolean isBooked ;

     private int price ;//price of CLASSIC Seat for that particular

     private  String seatNo ;

     @Enumerated(value = EnumType.STRING)
     private SeatType seatType ;

    private Date bookedAt ;


    // child class to show entity

    @ManyToOne
    @JoinColumn
    private  ShowEntity showEntity ;

}
