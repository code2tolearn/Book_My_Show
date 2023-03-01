package com.example.book_my_show.Controllers;

import com.example.book_my_show.EntryDtos.UserEntryDto;
import com.example.book_my_show.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
 @Slf4j // this is used to write logs in programme
public class UserController {

    @Autowired
    UserService userService ;
    @GetMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto)
    {
        try {
            String response = userService.addUser(userEntryDto) ;
            return new ResponseEntity<>(response , HttpStatus.CREATED) ;
        }catch (Exception e)
        {
            String result = "User could not be added" ;
            return new ResponseEntity<>(result , HttpStatus.BAD_REQUEST) ;
        }
    }




}
