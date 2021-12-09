package com.task.logger.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.print.attribute.standard.Severity;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "task")
@Getter
@Setter
public class TaskEntity {

	@Id
	@SequenceGenerator(sequenceName = "task_sequence", name = "task_sequence", allocationSize = 1)
	@GeneratedValue(generator = "task_sequence", strategy = GenerationType.SEQUENCE)
	Long id;
	@NotBlank(message = "task name cannot be null")
	@Column(name = "task_name")
	String taskName;
	@Column(name = "time_spent")
	Double timeSpent;
	@Column(name = "task_group")
	String taskGroup;
	@Column(name = "task_assignee")
	String taskAssignee;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_task_id")
	private List<SubTaskEntity> subTasks;
	@Column(name = "is_task_finished")
	boolean isTaskFinished;
	@Column(name = "details")
	String details;

	@Column(name = "created_date")
	Date createddate;
	@Column(name = "updated_date")
	Date updateddate;

	@PrePersist
	public void upateTime() {
		this.updateddate = new Date();
	}
	

}
