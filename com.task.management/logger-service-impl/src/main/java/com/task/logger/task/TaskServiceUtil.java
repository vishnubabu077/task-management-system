package com.task.logger.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.task.logger.entity.SubTaskEntity;
import com.task.logger.entity.TaskEntity;
import com.task.logger.model.SubTaskModel;
import com.task.logger.model.TaskModel;
import com.task.logger.task.exception.CustomException;

@Service
public class TaskServiceUtil {

	public TaskModel createTaskModel(TaskEntity taskEntity) {
		TaskModel taskModel = new TaskModel();
		List<SubTaskModel> subTaskModelsList = new ArrayList<>();

		taskModel.setId(taskEntity.getId());
		taskModel.setTimeSpent(taskEntity.getTimeSpent());
		taskModel.setTaskAssignee(taskEntity.getTaskAssignee());
		taskModel.setTaskFinished(taskEntity.isTaskFinished());
		taskModel.setTaskGroup(taskEntity.getTaskGroup());
		taskModel.setDetails(taskEntity.getDetails());
		taskModel.setTaskName(taskEntity.getTaskName());
		taskModel.setCreateddate(parseDate(taskEntity.getCreateddate()));
		taskModel.setUpdateddate(parseDate(taskEntity.getUpdateddate()));

		if (null != taskEntity.getSubTasks()) {
			taskEntity.getSubTasks().forEach(e -> {
				subTaskModelsList.add(createSubTaskModel(e));
			});
			taskModel.setSubTasks(subTaskModelsList);
		}
		return taskModel;

	}

	public SubTaskModel createSubTaskModel(SubTaskEntity subTaskEntity) {

		SubTaskModel subTaskModel = new SubTaskModel();
		subTaskModel.setId(subTaskEntity.getId());
		subTaskModel.setSubTaskName(subTaskEntity.getSubTaskName());
		subTaskModel.setSubTaskFinished(subTaskEntity.isSubTaskFinished());
		subTaskModel.setTimeSpent(subTaskEntity.getTimeSpent());

		return subTaskModel;

	}

	public String parseDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(date);

	}

	public TaskEntity updateTaskEntity(TaskModel taskModel, TaskEntity taskEntity) {

		taskEntity.setTaskAssignee(taskModel.getTaskAssignee());
		taskEntity.setDetails(taskModel.getDetails());
		taskEntity.setTaskFinished(taskModel.isTaskFinished());
		taskEntity.setTaskGroup(taskModel.getTaskGroup());
		taskEntity.setTaskName(taskModel.getTaskName());
		taskEntity.setTimeSpent(taskModel.getTimeSpent());
		if (null != taskModel.getSubTasks()) {
			taskEntity.setSubTasks(updateSubTask(taskModel.getSubTasks(), taskEntity.getSubTasks()));
		}

		return taskEntity;

	}

	public void validateSubTasksForUpdate(TaskEntity taskEntity) {

		taskEntity.getSubTasks().forEach(subTask -> {
			if (!subTask.isSubTaskFinished() && taskEntity.isTaskFinished()) {
				throw new CustomException(
						"Task Group cannot be completed " + taskEntity.getTaskGroup() + " when sub tasks are pending");
			}
		});

	}

	public void validateSubTasksForDelete(TaskEntity taskEntity) {

		taskEntity.getSubTasks().forEach(subTask -> {
			if (!subTask.isSubTaskFinished()) {
				throw new CustomException("Sub Task/Tasks for task group " + taskEntity.getTaskGroup()
						+ " are in Pending state, Please close the sub tasks before Deleting");
			}
		});

	}

	private List<SubTaskEntity> updateSubTask(List<SubTaskModel> subTaskModels, List<SubTaskEntity> subTaskEntities) {
		Map<Long, SubTaskEntity> subTaskEntityMap = new HashMap<>();
		// List<SubTaskEntity> updatedSubTasks = new ArrayList<>();
		List<SubTaskEntity> updatedSubTasks = new ArrayList<>();
		subTaskEntities.forEach(task -> {
			subTaskEntityMap.put(task.getId(), task);
		});

		subTaskModels.forEach(task -> {
			if (null == task.getId()) {
				updatedSubTasks.add(createSubTaskEntity(task));
			} else if (subTaskEntityMap.containsKey(task.getId())) {
				SubTaskEntity subTaskEntity = subTaskEntityMap.get(task.getId());
				subTaskEntity.setSubTaskName(task.getSubTaskName());
				subTaskEntity.setSubTaskFinished(task.isSubTaskFinished());
				subTaskEntity.setTimeSpent(task.getTimeSpent());
				updatedSubTasks.add(subTaskEntity);
				subTaskEntityMap.remove(task.getId());

			} else {
				throw new CustomException("Sub Task Not found");
			}
		});

		// Adding unchanged subtasks
		subTaskEntityMap.forEach((k, v) -> {
			updatedSubTasks.add(v);
		});
		return updatedSubTasks;

	}

	public TaskEntity createTaskEntity(TaskModel taskModel) {
		TaskEntity taskEntity = new TaskEntity();

		taskEntity = new TaskEntity();

		List<SubTaskEntity> subTaskEntityList = new ArrayList<>();

		taskEntity.setTaskAssignee(taskModel.getTaskAssignee());
		taskEntity.setTaskFinished(false);
		taskEntity.setTaskGroup(taskModel.getTaskGroup());
		taskEntity.setTaskName(taskModel.getTaskName());
		
		taskEntity.setTimeSpent(taskModel.getTimeSpent());
		taskEntity.setCreateddate(new Date());
		taskEntity.setDetails(taskModel.getDetails());
		if (null != taskModel.getSubTasks()) {
			taskModel.getSubTasks().forEach(e -> {
				subTaskEntityList.add(createSubTaskEntity(e));
			});
			taskEntity.setSubTasks(subTaskEntityList);
		}
		return taskEntity;

	}
	
	

	public SubTaskEntity createSubTaskEntity(SubTaskModel subTaskModel) {

		SubTaskEntity subTaskEntity = new SubTaskEntity();
		subTaskEntity.setSubTaskName(subTaskModel.getSubTaskName());
		subTaskEntity.setSubTaskFinished(false);
		subTaskEntity.setTimeSpent(subTaskModel.getTimeSpent());

		return subTaskEntity;

	}

}
