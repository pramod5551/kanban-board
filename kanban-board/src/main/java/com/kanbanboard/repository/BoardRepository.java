package com.kanbanboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kanbanboard.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
