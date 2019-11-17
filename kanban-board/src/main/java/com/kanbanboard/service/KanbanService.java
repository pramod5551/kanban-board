package com.kanbanboard.service;

import java.util.List;
import java.util.Optional;

import com.kanbanboard.model.Board;
import com.kanbanboard.model.Task;

public interface KanbanService {

	public Task saveTask(Task task);

	public Board saveBoard(Board board);

	public Optional<Task> retriveTask(Long taskId);

	public Optional<Board> retrieveBoard(Long boardId);

	public void deleteTask(Long taskId);

	public void deleteBoard(Long boardId);

	public List<Task> retriveAllTasks();

	public List<Task> retrieveTaskByBoardId(Long boardId);

}
