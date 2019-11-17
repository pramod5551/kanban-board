package com.kanbanboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kanbanboard.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Modifying
	@Query("DELETE FROM Task t WHERE t.boardId = ?1")
	void deleteTaskByBoardId(Long boardId);

	@Query("select t from Task t where t.boardId = :boardId")
	List<Task> retrieveAllTaskByBoardId(Long boardId);

}
