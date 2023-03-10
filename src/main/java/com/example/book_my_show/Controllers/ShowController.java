package com.example.book_my_show.Controllers;


import com.example.book_my_show.EntryDtos.ShowEntryDto;
import com.example.book_my_show.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService ;
    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto)
    {
         String result  = showService.addShow(showEntryDto);
         return new ResponseEntity<>(result , HttpStatus.CREATED) ;
    }


}
