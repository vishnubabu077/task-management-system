package com.task.logger.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.task.logger.entity.TaskEntity;
import com.task.logger.model.TaskModel;
import com.task.logger.task.exception.CustomException;
/**
 * The service class for Database operations
 * @author Vishnu
 *
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Resource
	TaskRepository taskRepository;

	@Autowired
	TaskServiceUtil taskServiceUtil;

	@Override
	public List<TaskModel> getAllTasks() {

		Iterable<TaskEntity> taskEntities = taskRepository.findAll();
		List<TaskModel> taskModelsList = new ArrayList<>();
		taskEntities.forEach(e -> {
			taskModelsList.add(taskServiceUtil.createTaskModel(e));
		});

		return taskModelsList;
	}

	@Override
	public void saveTask(TaskModel taskModel) {

		taskRepository.save(taskServiceUtil.createTaskEntity(taskModel));

	}

	@Override
	public void updateTask(Long id, TaskModel taskModel) {
		Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(id);
		optionalTaskEntity.ifPresentOrElse((taskEntity) -> {
			taskServiceUtil.updateTaskEntity(taskModel, taskEntity);
			taskServiceUtil.validateSubTasksForUpdate(taskEntity);
			taskRepository.save(taskEntity);
		}, () -> {
			throw new CustomException("Task Not found");
		});

	}

	@Override
	public void deleteTask(Long id) {

		Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(id);
		optionalTaskEntity.ifPresentOrElse((taskEntity) -> {
			taskServiceUtil.validateSubTasksForDelete(taskEntity);
			taskRepository.deleteById(id);
		}, () -> {
			throw new CustomException("Task Not found");
		});
		
		
		
	}

}
