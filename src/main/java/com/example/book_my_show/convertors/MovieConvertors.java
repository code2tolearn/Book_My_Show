package com.example.book_my_show.convertors;

import com.example.book_my_show.EntryDtos.MovieEntryDto;
import com.example.book_my_show.Models.MovieEntity;

public class MovieConvertors {
    public static MovieEntity convertEntryDtoToEntity(MovieEntryDto movieEntryDto){

        MovieEntity movieEntity = MovieEntity.builder()
                .movieName(movieEntryDto.getMovieName()).duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre()).language(movieEntryDto.getLanguage()).ratings(movieEntryDto.getRatings()).build();

        return movieEntity;
    }
}
