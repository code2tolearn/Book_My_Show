package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private  int id ;

    private String name;

    private String location;

    //This is the parent wrt to theaterSeats
    @OneToMany(mappedBy = "theaterEntity" , cascade =  CascadeType.ALL)
    private List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>() ;

    @OneToMany(mappedBy = "theaterEntity" , cascade =  CascadeType.ALL)
    private  List<ShowEntity> showEntityList = new ArrayList<>() ;

}
