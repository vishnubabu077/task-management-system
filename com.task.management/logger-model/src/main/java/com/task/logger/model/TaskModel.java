package com.task.logger.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.task.logger.entity.SubTaskEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskModel {

	Long id;

	String taskName;

	Double timeSpent;

	String taskGroup;

	String taskAssignee;

	private List<SubTaskModel> subTasks;

	boolean isTaskFinished;

	String createddate;

	String updateddate;
	
	String details;

}
