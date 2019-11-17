package com.kanbanboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kanbanboard.model.Board;
import com.kanbanboard.model.Task;
import com.kanbanboard.repository.BoardRepository;
import com.kanbanboard.repository.TaskRepository;

@Service
public class KanbanServiceImpl implements KanbanService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	BoardRepository boardRepository;

	public Task saveTask(Task task) {

		return taskRepository.save(task);
	}

	public Board saveBoard(Board board) {
		return boardRepository.save(board);
	}

	public Optional<Task> retriveTask(Long taskId) {
		return taskRepository.findById(taskId);
	}

	public Optional<Board> retrieveBoard(Long boardId) {
		return boardRepository.findById(boardId);

	}

	@Transactional
	public void deleteTask(Long taskId) {
		taskRepository.deleteById(taskId);
	}

	@Transactional
	public void deleteBoard(Long boardId) {
		boardRepository.deleteById(boardId);
		taskRepository.deleteTaskByBoardId(boardId);
	}

	@Override
	public List<Task> retriveAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public List<Task> retrieveTaskByBoardId(Long boardId) {
		return taskRepository.retrieveAllTaskByBoardId(boardId);
	}

}
