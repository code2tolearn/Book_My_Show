package com.example.book_my_show.Models;

import com.example.book_my_show.Enums.ShowType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="shows")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int  id ;

   private LocalDate showDate ;

   private LocalTime showTime ;

    @Enumerated(EnumType.STRING)
    private ShowType showType ;

   @CreationTimestamp
   private Date createdOn ;

   @UpdateTimestamp
   private Date updatedOn ;

    //This is child wrt to the movieEntity
    @ManyToOne
    @JoinColumn
    private MovieEntity movieEntity;

    @ManyToOne
    @JoinColumn
    private TheaterEntity theaterEntity;

    //Show is parent wrt to ticket
    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<TicketEntity> listOfBookedTickets = new ArrayList<>();

    // according to show price of seat will vary
    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<ShowSeatEntity> listOfShowSeats = new ArrayList<>();

}
