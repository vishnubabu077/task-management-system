package com.task.logger.model;

import java.util.Date;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubTaskModel {

	Long id;
	String subTaskName;
	Double timeSpent;
	boolean isSubTaskFinished;

}
