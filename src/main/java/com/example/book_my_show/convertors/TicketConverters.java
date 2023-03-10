package com.example.book_my_show.convertors;

import com.example.book_my_show.EntryDtos.TicketEntryDto;
import com.example.book_my_show.Models.TicketEntity;

public class TicketConverters {
    public static  TicketEntity converterEntityToDto(TicketEntryDto ticketEntryDto)
    {
        TicketEntity ticketEntity =  new TicketEntity() ;
        return  ticketEntity ;

    }

}
