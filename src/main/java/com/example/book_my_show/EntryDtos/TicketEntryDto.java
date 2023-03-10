package com.example.book_my_show.EntryDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntryDto {

    private  int showId ;

    private  int userId ;

    private List<String>  requestedSeats  = new ArrayList<>() ;


}
