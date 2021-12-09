package com.task.logger.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sub_task")
public class SubTaskEntity {

	@Id
	@SequenceGenerator(sequenceName = "task_sequence", name = "task_sequence", allocationSize = 1)
	@GeneratedValue(generator = "task_sequence", strategy = GenerationType.SEQUENCE)
	Long id;
	@Column(name = "sub_task_name")
	String subTaskName;
	@Column(name = "time_spent")
	Double timeSpent;
	@Column(name = "is_sub_task_finished")
	boolean isSubTaskFinished;

}
