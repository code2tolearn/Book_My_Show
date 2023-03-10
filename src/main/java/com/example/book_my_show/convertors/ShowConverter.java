package com.example.book_my_show.convertors;

import com.example.book_my_show.EntryDtos.ShowEntryDto;
import com.example.book_my_show.Models.ShowEntity;
import com.example.book_my_show.Models.ShowSeatEntity;
import com.example.book_my_show.services.ShowService;

public class ShowConverter {

    public static ShowEntity convertDtoToEntity(ShowEntryDto showEntryDto)
    {
           ShowEntity showEntity = ShowEntity.builder()
                   .showDate(showEntryDto.getLocalDate())
                   .showTime(showEntryDto.getLocalTime())
                   .showType(showEntryDto.getShowType())
                                    .build() ;

           return showEntity ;

    }



}
