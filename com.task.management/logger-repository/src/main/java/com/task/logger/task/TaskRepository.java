package com.task.logger.task;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task.logger.entity.TaskEntity;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

}
