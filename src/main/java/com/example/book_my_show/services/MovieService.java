package com.example.book_my_show.services;

import com.example.book_my_show.EntryDtos.MovieEntryDto;
import com.example.book_my_show.Models.MovieEntity;
import com.example.book_my_show.convertors.MovieConvertors;
import com.example.book_my_show.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto)throws Exception{
        MovieEntity movieEntity = MovieConvertors.convertEntryDtoToEntity(movieEntryDto);
        movieRepository.save(movieEntity);

        return "Movie Added successfully";

    }

}
