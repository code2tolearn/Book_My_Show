package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private String name ;

    private   int  age ;

    @Column(unique = true , nullable = false) // can't be null
    private  String email ;

    @Column(unique = true , nullable = false) // nullable = false can't be null
    private  String mobNo ;

    private String address;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<TicketEntity> bookedTickets = new ArrayList<>();
 }
