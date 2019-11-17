package com.kanbanboard.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ResponseDetails {

	private String description;
	private Board board;
	@JsonInclude(Include.NON_EMPTY)
	private List<Task> task;

}