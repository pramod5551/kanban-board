package com.kanbanboard.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiConstants {

	//@formatter:off

	TASK_NOT_FOUND("Task not found"), 
	TASK_CREATED("Task created successfully"), 
	TASK_UPDATED("Task updated successfully"),
	TASK_CREATE_FAILED("Task creation failed"), 
	BOARD_CREATED("Board created successfully"),
	BOARD_CREATE_FAILED("Board crreation failed"), 
	BOARD_UPDATED("Board updated"),
	BOARD_NOT_FOUND("Board not found"),
	INVALID_STATUS_VALUE("Invalid Status Value"), 
	TASK_UPDATE_FAILED("Task update failed"),
	BOARD_UPDATE_FAILED("Board update failed"),
	TASK_DELETED("Task deleted"), 
	BOARD_DELETED("Board deleted"),
	INVALID_ID("Invalid ID supplied");

	private String value;

}
