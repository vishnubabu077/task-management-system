
CREATE TABLE task(
    id              numeric(30) CONSTRAINT PK_TASK PRIMARY KEY,
    task_name            varchar(40) NOT NULL,
    time_spent             numeric(10,2) NOT NULL,
    task_group    varchar(30) NOT NULL,
    task_assignee       varchar(30) ,
    is_task_finished           BOOLEAN NOT NULL,
    created_date			DATE,
    updated_date            DATE,
    details         varchar(200)
);

CREATE TABLE sub_task(
    id              numeric(30) CONSTRAINT PK_SUBTASK PRIMARY KEY,
    sub_task_name            varchar(40) NOT NULL,
  	is_sub_task_finished           BOOLEAN NOT NULL,
  	time_spent             numeric(10,2) NOT NULL,
  	parent_task_id               numeric(30),
  	 CONSTRAINT fk_task FOREIGN KEY(parent_task_id) REFERENCES task(id)
   
);
CREATE SEQUENCE task_sequence
  START WITH 100
  INCREMENT BY 1
  MINVALUE 100;
  
  INSERT INTO task VALUES (NEXT VALUE FOR task_sequence, 'test1', 1,'test','admin',false,CURRENT_DATE,CURRENT_DATE,'test');
  INSERT INTO task VALUES (NEXT VALUE FOR task_sequence, 'test2', 1.4,'test','user',false,CURRENT_DATE,CURRENT_DATE,'test');
   INSERT INTO task VALUES (NEXT VALUE FOR task_sequence, 'test3', 1,'test','admin',false,CURRENT_DATE,CURRENT_DATE,'test');
    INSERT INTO task VALUES (NEXT VALUE FOR task_sequence, 'test4', 1,'test','admin',false,CURRENT_DATE,CURRENT_DATE,'test');
  
  INSERT INTO sub_task VALUES (NEXT VALUE FOR task_sequence, 'test2',false,1,(SELECT id FROM task where task_name='test2'));
  INSERT INTO sub_task VALUES (NEXT VALUE FOR task_sequence, 'test3',false,1,(SELECT id FROM task where task_name='test2'));
  INSERT INTO sub_task VALUES (NEXT VALUE FOR task_sequence, 'test2',false,1,(SELECT id FROM task where task_name='test1'));
  
COMMIT;
