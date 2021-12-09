package com.task.logger;

import static org.junit.Assert.assertEquals;

import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.task.logger.entity.TaskEntity;
import com.task.logger.model.TaskModel;
import com.task.logger.task.TaskRepository;
import com.task.logger.task.TaskServiceImpl;
import com.task.logger.task.TaskServiceUtil;

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
		
		assertEquals(taskEntity.getClass(),  entityCreated.getClass());
		assertEquals(taskModel.getId(), entityCreated.getId());
		assertEquals(taskModel.getTaskName(), entityCreated.getTaskName());
		

	}

}
