package com.example.book_my_show.services;

import com.example.book_my_show.EntryDtos.TheaterEntryDto;
import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Models.TheaterEntity;
import com.example.book_my_show.Models.TheaterSeatEntity;
import com.example.book_my_show.convertors.TheaterConvertors;
import com.example.book_my_show.repository.TheaterRepository;
import com.example.book_my_show.repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository ;
    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    @PostMapping
    public String addTheater(@RequestBody TheaterEntryDto theaterEntryDto)throws Exception
    {
        // check some validation
         if(theaterEntryDto.getName()== null || theaterEntryDto.getLocation() == null )
         {
             throw new Exception("Name and Location should be valid") ;
         }
         // set entity

        TheaterEntity  theaterEntity = TheaterConvertors.convertDtoToEntity(theaterEntryDto) ;
        List<TheaterSeatEntity>  theaterSeatEntityList = createTheaterSeatList(theaterEntryDto , theaterEntity) ;
         theaterEntity.setTheaterSeatEntityList(theaterSeatEntityList);
         theaterRepository.save(theaterEntity) ;

        return "Theater added Successfully " ;
    }
    public List<TheaterSeatEntity>  createTheaterSeatList(TheaterEntryDto theaterEntryDto , TheaterEntity theaterEntity)
    {
          List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>() ;
          int classicSeatCount = theaterEntryDto.getClassicSeatsCount() ;
          int premiumSeatCount = theaterEntryDto.getPremiumSeatsCount() ;
           // for classic seat count
         for(int i=1 ; i<=classicSeatCount ; i++)
         {
            TheaterSeatEntity theaterSeatEntity =   TheaterSeatEntity.builder().seatType(SeatType.CLASSIC)
                    .seatNo(i+"C").theaterEntity(theaterEntity).build() ;

            theaterSeatEntityList.add(theaterSeatEntity) ;
         }
         // for premium seat count
        for(int i=1 ; i<=premiumSeatCount ; i++)
        {
            // make theater seat entity first
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().
                    seatType(SeatType.PREMIUM).seatNo(i+"P").theaterEntity(theaterEntity).build() ;
            theaterSeatEntityList.add(theaterSeatEntity) ;

        }
        return theaterSeatEntityList ;
    }

}
