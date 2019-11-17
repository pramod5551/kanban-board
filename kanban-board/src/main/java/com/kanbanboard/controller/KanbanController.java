package com.kanbanboard.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanbanboard.exception.ProblemDetailsException;
import com.kanbanboard.model.Board;
import com.kanbanboard.model.ResponseDetails;
import com.kanbanboard.model.Task;
import com.kanbanboard.model.TaskStatus;
import com.kanbanboard.service.KanbanService;
import com.kanbanboard.util.ApiConstants;
import com.kanbanboard.util.Validator;

@RestController
//@CrossOrigin
@RequestMapping("/kanban")
public class KanbanController {

	@Autowired
	KanbanService kanbanService;

	@GetMapping("/task/{taskId}")
	@PreAuthorize("#oauth2.hasScope('read:task')")
	public ResponseEntity<ResponseDetails> getTask(@PathVariable Long taskId) throws ProblemDetailsException {
		Optional<Task> task = kanbanService.retriveTask(taskId);
		if (!task.isPresent())
			throw new ProblemDetailsException(HttpStatus.NOT_FOUND, ApiConstants.TASK_NOT_FOUND.getValue());
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDetails(null, null, Arrays.asList(task.get())));

	}

	@GetMapping("/tasks")
	@PreAuthorize("#oauth2.hasScope('read:task')")
	public ResponseEntity<ResponseDetails> getAllTask() throws ProblemDetailsException {
		List<Task> tasklist = kanbanService.retriveAllTasks();
		if (tasklist.isEmpty())
			throw new ProblemDetailsException(HttpStatus.NOT_FOUND, ApiConstants.TASK_NOT_FOUND.getValue());
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDetails(null, null, tasklist));

	}

	@GetMapping("/board/{boardId}")
	@PreAuthorize("#oauth2.hasScope('read:board')")
	public ResponseEntity<ResponseDetails> getBoard(@PathVariable Long boardId) throws ProblemDetailsException {
		Optional<Board> board = kanbanService.retrieveBoard(boardId);
		if (!board.isPresent())
			throw new ProblemDetailsException(HttpStatus.NOT_FOUND, ApiConstants.BOARD_NOT_FOUND.getValue());
		List<Task> taskList = kanbanService.retrieveTaskByBoardId(boardId);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDetails(null, board.get(), taskList));
	}

	@PostMapping("/task")
	@PreAuthorize("#oauth2.hasScope('write:task')")
	public ResponseEntity<ResponseDetails> saveTask(@RequestBody Task task) throws ProblemDetailsException {
		if (StringUtils.isEmpty(task.getStatus()))
			task.setStatus(TaskStatus.BACKLOG.toString());
		if (!Validator.isValidTask(task))
			throw new ProblemDetailsException(HttpStatus.BAD_REQUEST, ApiConstants.INVALID_STATUS_VALUE.getValue());

		Task savedTask = kanbanService.saveTask(task);
		if (null == savedTask)
			throw new ProblemDetailsException(HttpStatus.INTERNAL_SERVER_ERROR,
					ApiConstants.TASK_CREATE_FAILED.getValue());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDetails(ApiConstants.TASK_CREATED.getValue(), null, Arrays.asList(savedTask)));
	}

	@PostMapping("/board")
	@PreAuthorize("#oauth2.hasScope('write:board')")
	public ResponseEntity<ResponseDetails> saveBoard(@RequestBody Board board) throws ProblemDetailsException {
		Board savedBoard = kanbanService.saveBoard(board);
		if (null == savedBoard)
			throw new ProblemDetailsException(HttpStatus.INTERNAL_SERVER_ERROR,
					ApiConstants.BOARD_CREATE_FAILED.getValue());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDetails(ApiConstants.BOARD_CREATED.getValue(), savedBoard, null));

	}

	@PutMapping("/task")
	@PreAuthorize("#oauth2.hasScope('write:task')")
	public ResponseEntity<ResponseDetails> updateTask(@RequestBody Task task) throws ProblemDetailsException {
		if (StringUtils.isEmpty(task.getStatus()))
			task.setStatus(TaskStatus.BACKLOG.toString());
		if (!Validator.isValidStatus(task))
			throw new ProblemDetailsException(HttpStatus.BAD_REQUEST, ApiConstants.INVALID_STATUS_VALUE.getValue());
		Optional<Task> savedTask = kanbanService.retriveTask(task.getId());
		if (!savedTask.isPresent())
			throw new ProblemDetailsException(HttpStatus.NOT_FOUND, ApiConstants.TASK_NOT_FOUND.getValue());
		Task updatedTask = kanbanService.saveTask(task);
		if (null == updatedTask)
			throw new ProblemDetailsException(HttpStatus.INTERNAL_SERVER_ERROR,
					ApiConstants.TASK_UPDATE_FAILED.getValue());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDetails(ApiConstants.TASK_UPDATED.getValue(), null, Arrays.asList(updatedTask)));

	}

	@PutMapping("/board")
	@PreAuthorize("#oauth2.hasScope('write:board')")
	public ResponseEntity<ResponseDetails> updateBoard(@RequestBody Board board) throws ProblemDetailsException {
		Optional<Board> responseBoard = kanbanService.retrieveBoard(board.getId());
		if (!responseBoard.isPresent())
			throw new ProblemDetailsException(HttpStatus.BAD_REQUEST, ApiConstants.INVALID_ID.getValue());
		Board updatedBoard = kanbanService.saveBoard(board);
		if (null == updatedBoard)
			throw new ProblemDetailsException(HttpStatus.INTERNAL_SERVER_ERROR,
					ApiConstants.BOARD_UPDATE_FAILED.getValue());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDetails(ApiConstants.BOARD_UPDATED.getValue(), updatedBoard, null));

	}

	@DeleteMapping("/task/{taskId}")
	@PreAuthorize("#oauth2.hasScope('delete:task')")
	public ResponseEntity<ResponseDetails> deleteTask(@PathVariable Long taskId) throws ProblemDetailsException {
		Optional<Task> task = kanbanService.retriveTask(taskId);
		if (!task.isPresent())
			throw new ProblemDetailsException(HttpStatus.BAD_REQUEST, ApiConstants.INVALID_ID.getValue());
		kanbanService.deleteTask(taskId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDetails(ApiConstants.TASK_DELETED.getValue(), null, null));

	}

	@DeleteMapping("/board/{boardId}")
	@PreAuthorize("#oauth2.hasScope('delete:board')")
	public ResponseEntity<ResponseDetails> deleteBoard(@PathVariable Long boardId) throws ProblemDetailsException {
		Optional<Board> board = kanbanService.retrieveBoard(boardId);
		if (!board.isPresent())
			throw new ProblemDetailsException(HttpStatus.BAD_REQUEST, ApiConstants.INVALID_ID.getValue());
		kanbanService.deleteBoard(boardId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDetails(ApiConstants.BOARD_DELETED.getValue(), null, null));

	}

}
