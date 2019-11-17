package com.kanbanboard.util;

import com.kanbanboard.model.Task;
import com.kanbanboard.model.TaskStatus;

public class Validator {

	public static boolean isValidTask(Task task) {
		return null != task.getStatus() && TaskStatus.BACKLOG.toString().equals(task.getStatus());

	}

	public static boolean isValidStatus(Task task) {
		return TaskStatus.BACKLOG.toString().equals(task.getStatus())
				|| TaskStatus.WIP.toString().equals(task.getStatus())
				|| TaskStatus.COMPLETED.toString().equals(task.getStatus());
	}

}
