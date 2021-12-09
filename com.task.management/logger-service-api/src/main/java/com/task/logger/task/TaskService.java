package com.task.logger.task;

import java.util.List;

import javax.validation.Valid;

import com.task.logger.model.TaskModel;

public interface TaskService {

	List<TaskModel> getAllTasks();

	void updateTask(Long id, @Valid TaskModel task);

	void saveTask(TaskModel taskModel);

	void deleteTask(Long id);

}
