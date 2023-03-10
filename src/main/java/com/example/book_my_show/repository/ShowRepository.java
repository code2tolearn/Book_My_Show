package com.example.book_my_show.repository;

import com.example.book_my_show.Models.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity , Integer> {
}
