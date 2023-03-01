package com.example.book_my_show.services;

import com.example.book_my_show.EntryDtos.UserEntryDto;
import com.example.book_my_show.Models.UserEntity;
import com.example.book_my_show.convertors.UserConvertor;
import com.example.book_my_show.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository ;

    public String addUser(UserEntryDto userEntryDto)
    {
        UserEntity userEntity = UserConvertor.convertDtoToEntity(userEntryDto);
         userRepository.save(userEntity) ;
         return "User added Successfully " ;
    }
}
