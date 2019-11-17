package com.kanbanboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatus {

	// @formatter:off
	
	BACKLOG("Backlog"), 
	WIP("Work In Progress"), 
	COMPLETED("Completed");
	
	private String value;
}
