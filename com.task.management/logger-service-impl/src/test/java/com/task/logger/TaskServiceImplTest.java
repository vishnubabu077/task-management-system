package com.task.logger;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.task.logger.entity.TaskEntity;
import com.task.logger.model.TaskModel;
import com.task.logger.task.TaskRepository;
import com.task.logger.task.TaskServiceImpl;
import com.task.logger.task.TaskServiceUtil;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

	@Mock
	TaskRepository taskRepository;

	@Mock
	TaskServiceUtil taskServiceUtil;

	@InjectMocks
	TaskServiceImpl taskServiceImpl;

	@Test
	public void getAllEmployeesTest() {

		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setId(1L);
		taskEntity.setTaskName("Test-Task1");
		
		TaskModel model = new TaskModel();
		model.setId(1L);
		model.setTaskName("Test-Task1");
		
		List<TaskEntity> taskEntities = new ArrayList<>();
		taskEntities.add(taskEntity);
		Iterable<TaskEntity> iterable = taskEntities;
		Mockito.when(taskRepository.findAll()).thenReturn(iterable);
		Mockito.when(taskServiceUtil.createTaskModel(Mockito.any())).thenReturn(model);

		assertEquals(1, taskServiceImpl.getAllTasks().size());

	}

}
