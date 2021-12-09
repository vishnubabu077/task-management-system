package com.task.logger.application.task;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.logger.model.TaskModel;
import com.task.logger.task.TaskService;
import com.task.logger.task.TaskServiceImpl;

@RestController
@RequestMapping("/task")
public class TaskLogController {

	@Autowired
	TaskService taskService;

	@PostMapping(path = "/save")
	public ResponseEntity<String> saveTask(@Valid @RequestBody TaskModel task) {
		taskService.saveTask(task);
		return new ResponseEntity<String>("Saved task details Successfully", HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<String> updateTask(@Valid @RequestBody TaskModel task, @PathVariable(name = "id") Long id) {
		taskService.updateTask(id, task);
		return new ResponseEntity<String>("Updated task details Successfully", HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable(name = "id") Long id) {
		taskService.deleteTask(id);
		return new ResponseEntity<String>("Updated task details Successfully", HttpStatus.OK);
	}

	@GetMapping(path = "/getAll")
	public ResponseEntity<List<TaskModel>> getAllTask() {
		return ResponseEntity.ok().body(taskService.getAllTasks());
	}

}
