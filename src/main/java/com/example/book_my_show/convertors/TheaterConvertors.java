package com.example.book_my_show.convertors;

import com.example.book_my_show.EntryDtos.TheaterEntryDto;
import com.example.book_my_show.Models.TheaterEntity;

public class TheaterConvertors {

    public  static TheaterEntity convertDtoToEntity(TheaterEntryDto theaterEntryDto)
    {
        return  TheaterEntity.builder().location(theaterEntryDto.getLocation()).name(theaterEntryDto.getName()).build() ;

    }

}
