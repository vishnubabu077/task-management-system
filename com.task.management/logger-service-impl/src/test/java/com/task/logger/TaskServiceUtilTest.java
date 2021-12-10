package com.task.logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.task.logger.entity.SubTaskEntity;
import com.task.logger.entity.TaskEntity;
import com.task.logger.model.SubTaskModel;
import com.task.logger.model.TaskModel;
import com.task.logger.task.TaskRepository;
import com.task.logger.task.TaskServiceImpl;
import com.task.logger.task.TaskServiceUtil;
import com.task.logger.task.exception.CustomException;

@ExtendWith(MockitoExtension.class)
public class TaskServiceUtilTest {

	@Mock
	TaskRepository taskRepository;

	@InjectMocks
	TaskServiceUtil taskServiceUtil;

	@InjectMocks
	TaskServiceImpl taskServiceImpl;

	@Test
	public void createTaskModelTest() {

		TaskModel taskModel = new TaskModel();
		taskModel.setTaskName("Test-Task1");

		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setTaskName("Test-Task1");

		TaskEntity entityCreated = taskServiceUtil.createTaskEntity(taskModel);

		assertEquals(taskEntity.getClass(), entityCreated.getClass());
		assertEquals(taskModel.getId(), entityCreated.getId());
		assertEquals(taskModel.getTaskName(), entityCreated.getTaskName());

	}

	@Test()
	public void validateSubTasksForUpdate() {

		List<SubTaskEntity> subTaskEntities = new ArrayList<>();

		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setTaskName("Test-Task1");
		taskEntity.setTaskFinished(true);

		SubTaskEntity subTaskEntity = new SubTaskEntity();
		subTaskEntity.setSubTaskName("SubTask - test");
		subTaskEntity.setSubTaskFinished(false);
		taskEntity.setSubTasks(null);
		subTaskEntities.add(subTaskEntity);

		taskEntity.setSubTasks(subTaskEntities);

		// Test if exception is thrown
		Assertions.assertThrows(CustomException.class, () -> {
			taskServiceUtil.validateSubTasksForUpdate(taskEntity);
		});

	}

	@Test()
	public void updateSubTask() {

		List<SubTaskModel> subTaskModels = new ArrayList<>();
		SubTaskModel subTaskModel = new SubTaskModel();
		subTaskModel.setSubTaskName("subtask-test");
		subTaskModel.setTimeSpent(1.0);
		subTaskModels.add(subTaskModel);

		List<SubTaskEntity> subTaskEntities = new ArrayList<>();
		SubTaskEntity subTaskEntity = new SubTaskEntity();
		subTaskEntity.setSubTaskName("subtask-test");
		subTaskEntity.setTimeSpent(1.0);

		subTaskEntities.add(subTaskEntity);

		TaskModel taskModel = new TaskModel();
		taskModel.setTaskName("Test-Task1");
		taskModel.setSubTasks(subTaskModels);

		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setTaskName("Test-Task1");
		taskEntity.setTaskFinished(true);
		taskEntity.setSubTasks(subTaskEntities);

		TaskEntity entity = taskServiceUtil.updateTaskEntity(taskModel, taskEntity);

		assertEquals(taskEntity.getClass(), entity.getClass());
		assertEquals(taskModel.getId(), entity.getId());
		assertEquals(taskModel.getTaskName(), entity.getTaskName());
	}

}
