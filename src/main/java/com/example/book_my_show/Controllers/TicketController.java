package com.example.book_my_show.Controllers;

import com.example.book_my_show.EntryDtos.TicketEntryDto;
import com.example.book_my_show.services.TicketService;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService ;

    @PostMapping("/book")
    public String bookTicket(@RequestBody TicketEntryDto ticketEntryDto)
    {

        try {
            String result   = ticketService.bookTicket(ticketEntryDto) ;
            return result ;
      }catch(Exception e )
      {
          return "Booking Failed" ;
      }

    }
    @PostMapping("/cancelTicket")
    public  String cancelTicket(@RequestParam ("ticketId") Integer ticketId)
    {
         return  ticketService.cancelTicket(ticketId) ;
    }


}
