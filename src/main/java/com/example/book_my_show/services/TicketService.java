package com.example.book_my_show.services;

import com.example.book_my_show.EntryDtos.TicketEntryDto;
import com.example.book_my_show.Models.ShowEntity;
import com.example.book_my_show.Models.ShowSeatEntity;
import com.example.book_my_show.Models.TicketEntity;
import com.example.book_my_show.Models.UserEntity;
import com.example.book_my_show.convertors.TicketConverters;
import com.example.book_my_show.repository.ShowRepository;
import com.example.book_my_show.repository.TicketRepository;
import com.example.book_my_show.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TicketService {
 @Autowired
    TicketRepository ticketRepository ;
 @Autowired
    ShowRepository showRepository ;
 @Autowired
    UserRepository userRepository ;

     public String bookTicket(TicketEntryDto ticketEntryDto) throws Exception {
          // set the attribute first rule No 1
         TicketEntity ticketEntity = TicketConverters.converterEntityToDto(ticketEntryDto) ;
         // check validation weather required seats are available are not
         boolean isValidRequest =  checkValidityOfRequestedSeats(ticketEntryDto ) ;

         // if seats are available
         if(!isValidRequest)
         {
             throw new Exception("Requested Seats are not available " );
         }
         // valid
// Calculate the total amount  and booked the seats
         ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get() ;
          List<ShowSeatEntity> seatEntityList = showEntity.getListOfShowSeats() ;
          List<String> requestedSeats = ticketEntryDto.getRequestedSeats() ;


          int totalAmount = 0 ;
          for(ShowSeatEntity showSeatEntity: seatEntityList )
          {
              if(requestedSeats.contains(showSeatEntity.getSeatNo()))
              {
                  totalAmount = totalAmount+ showSeatEntity.getPrice() ;
                  showSeatEntity.setBooked(true);
                  showSeatEntity.setBookedAt(new Date());
              }
          }
         ticketEntity.setTotalAmount(totalAmount);  // total amount is saved

         //Setting the other attributes for the ticketEntity
           ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
           ticketEntity.setShowDate(showEntity.getShowDate());
           ticketEntity.setShowTime(showEntity.getShowTime());
           ticketEntity.setTheaterName(showEntity.getTheaterEntity().getName());

         //We need to set that string that talked about requested Seats
             String  allottedSeats  = getAllottedSeatsFromShowSeats(requestedSeats) ;
             ticketEntity.setBookedSeats(allottedSeats) ;

             // setting the foreign key attributes
           UserEntity userEntity = userRepository.findById(ticketEntryDto.getUserId()) .get() ;


           ticketEntity.setUserEntity(userEntity);
           ticketEntity.setShowEntity(showEntity);

           // save parents
         ticketEntity = ticketRepository.save(ticketEntity) ; // save ticket entity to avoid duplicate entry at the time of cascading effect

          List<TicketEntity> ticketEntityList = showEntity.getListOfBookedTickets() ;
          ticketEntityList.add(ticketEntity) ;
          showEntity.setListOfBookedTickets(ticketEntityList);
          showRepository.save(showEntity) ;

          List<TicketEntity> ticketEntityList1 = userEntity.getBookedTickets() ;
           ticketEntityList1.add(ticketEntity) ;
           userEntity.setBookedTickets(ticketEntityList1);

           userRepository.save(userEntity) ;

            // String body ="Hi this is to confirm your booking for seat No "+allottedSeats +"for the movie : " + ticketEntity.getMovieName();
         return  "Ticket has successfully been added" ;
     }

     private String getAllottedSeatsFromShowSeats(List<String> requestedSeats)
     {
         String result = "" ;
         for(String seat : requestedSeats)
         {
             result = result+seat+"," ;
         }
         return  result ;
     }

     public  boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto)
     {
          int showId = ticketEntryDto.getShowId() ;
         ShowEntity showEntity = showRepository.findById(showId).get() ;
         int  userId = ticketEntryDto.getUserId() ;
         UserEntity userEntity = userRepository.findById(userId).get() ;

         List<ShowSeatEntity> listOfShowSeats = showEntity.getListOfShowSeats() ;
         List<String> requestedSeatEntityList =  ticketEntryDto.getRequestedSeats();
         for(ShowSeatEntity showSeatEntity : listOfShowSeats)
         {
             String seatNo = showSeatEntity.getSeatNo();
            if(requestedSeatEntityList.contains(seatNo))
            {
                if(showSeatEntity.isBooked() == true)
                {
                    return false ;
                    // since this seat is booked can't be occupied
                }
             }
         }
         // All seats are available
         return  true ;
     }
     // Cancel Ticket
    public  String cancelTicket(Integer ticketId){
         // find the ticket from repository
        TicketEntity ticketEntity = ticketRepository.findById(ticketId).get() ;
         String bookSeats = ticketEntity.getBookedSeats() ;
         String []seat = bookSeats.split(",") ;
         Set<String> tickets = new HashSet<>() ;
         for(String s: seat )
         {
             tickets.add(s) ;
         }
         // all book tickets are in the set Now
        ShowEntity showEntity = ticketEntity.getShowEntity() ;
        List<ShowSeatEntity> showSeatEntityList = showEntity.getListOfShowSeats() ;
        // check / unbooked the seats from the  showSeatEntityList
        for(ShowSeatEntity st : showSeatEntityList)
        {
            if(tickets.contains(st.getSeatNo()) && st.getShowEntity().getId() == showEntity.getId())
            {
                // unbooked and set booked at null
                st.setBooked(false);
                st.setBookedAt(null) ;
                tickets.remove(st.getSeatNo()) ;
            }
        }
        ticketEntity.setTotalAmount(ticketEntity.getTotalAmount()*-1);
        // set  amount to be - so that that will be refund
        ticketRepository.save(ticketEntity) ;
        showRepository.save(showEntity) ;
        // showSeat Entity will be saved automatically due to cascading effect
        return "Your refund will be credited within 7 working days" ;
    }


}
