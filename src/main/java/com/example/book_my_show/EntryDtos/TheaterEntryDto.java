package com.example.book_my_show.EntryDtos;

import lombok.Data;

@Data
public class TheaterEntryDto {
    //Attributes that we require
    private String name;

    private String location;

    private int classicSeatsCount;

    private int premiumSeatsCount;
}
