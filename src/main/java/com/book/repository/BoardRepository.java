package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
