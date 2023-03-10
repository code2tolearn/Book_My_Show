package com.example.book_my_show.services;

import com.example.book_my_show.EntryDtos.ShowEntryDto;
import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Models.*;
import com.example.book_my_show.convertors.ShowConverter;
import com.example.book_my_show.repository.MovieRepository;
import com.example.book_my_show.repository.ShowRepository;
import com.example.book_my_show.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository ;

    @Autowired
    MovieRepository movieRepository ;
    @Autowired
    TheaterRepository theaterRepository ;


     public String addShow(@RequestBody ShowEntryDto showEntryDto)
     {
         // First all set entity first for that
         // convert Dto to Entity
         ShowEntity showEntity = ShowConverter.convertDtoToEntity(showEntryDto) ;
         //
         int movieId =  showEntryDto.getMovieId() ;
         int theaterId = showEntryDto.getTheaterId() ;
         // set thid attribute to showEntity

         MovieEntity movieEntity =  movieRepository.findById(movieId).get() ;
         TheaterEntity theaterEntity =  theaterRepository.findById(theaterId).get() ;
         //Setting the attribute of foreignKe
         showEntity.setMovieEntity(movieEntity);
         showEntity.setTheaterEntity(theaterEntity);
         //Pending attributes the listOfShowSeatsEnity
         List<ShowSeatEntity> seatEntityList =  createShowSeatEntity(showEntryDto,showEntity);

         showEntity.setListOfShowSeats(seatEntityList);
         //Now we  also need to update the parent entities
         showEntity = showRepository.save(showEntity);

         movieEntity.getShowEntityList().add(showEntity);
         theaterEntity.getShowEntityList().add(showEntity);

     // When parent will be saved child will automatically save
         movieRepository.save(movieEntity);

         theaterRepository.save(theaterEntity);

         return "The show has been added successfully";

     }

       public List<ShowSeatEntity>   createShowSeatEntity(ShowEntryDto showEntryDto,ShowEntity showEntity)
       {

           TheaterEntity theaterEntity = showEntity.getTheaterEntity();

           List<TheaterSeatEntity> theaterSeatEntityList = theaterEntity.getTheaterSeatEntityList();

           List<ShowSeatEntity> seatEntityList = new ArrayList<>();

           for(TheaterSeatEntity theaterSeatEntity : theaterSeatEntityList){

               ShowSeatEntity showSeatEntity = new ShowSeatEntity();

               showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
               showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

               if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC))
                   showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());

               else
                   showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());

               showSeatEntity.setBooked(false);
               showSeatEntity.setShowEntity(showEntity); //parent : foreign key for the showSeat Entity

               seatEntityList.add(showSeatEntity); //Adding it to the list
           }

           return  seatEntityList;
       }

}
