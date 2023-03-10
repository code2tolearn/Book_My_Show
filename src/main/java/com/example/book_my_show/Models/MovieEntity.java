package com.example.book_my_show.Models;

import com.example.book_my_show.Enums.Genre;
import com.example.book_my_show.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Data // no need of getter setter required constructor  bt do mention all arg constructor nd no arg constructor
@Builder // to build entity
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

    @Column(unique = true , nullable = false)
    private String movieName ;

    private int ratings ;

    private  int duration ;

    @Enumerated(value = EnumType.STRING)
    private Language language ;

   @Enumerated(value = EnumType.STRING)
    private Genre genre ;

    //this is parent wrt to shows
    @OneToMany(mappedBy = "movieEntity",cascade = CascadeType.ALL)
    private List<ShowEntity> showEntityList = new ArrayList<>();

  }
