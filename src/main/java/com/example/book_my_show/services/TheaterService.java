package com.example.book_my_show.services;

import com.example.book_my_show.EntryDtos.TheaterEntryDto;
import com.example.book_my_show.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository ;

    @PostMapping
    public String addTheater(@RequestBody TheaterEntryDto theaterEntryDto)
    {
        @Autowired
        TheaterSeatRepository theaterSeatRepository;


    }
}
