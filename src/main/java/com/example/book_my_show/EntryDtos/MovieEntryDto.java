package com.example.book_my_show.EntryDtos;

import com.example.book_my_show.Enums.Genre;
import com.example.book_my_show.Enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntryDto {

    private String movieName;

    private int ratings;

    private int duration;

    private Language language;

    private Genre genre;

 }
