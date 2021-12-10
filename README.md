# task-management-system-backend

The project is a Task Management sytem where a user can Add, Edit , update delete tasks. The main task will have sub tasks

- This project is implemented on Java 15 and Spring boot framework (2.5.2)
- H2 Database is used
- Hibernate is used for DB Transactions
- Multi module project structure  is used 
-lombok is used

# Setup 

- The Project was build using Java 15 , but can be run/build using another version by changing the parent pom file (at root folder)
- Maven must be installed and path should be added in environemnt of system (else eclipse can be used to import as maven project and run directly)

- Checkout the project from Git
- Go into the root folder  : task-management-system\com.task.management
- run command : mvn clean install
- by doing the above command , all the sub modules will be packaged and will be available at the folder : task-management-system\com.task.management\logger-application\target
- The packaged file name would be logger-application-0.0.1-SNAPSHOT.jar
- run the jar using command : java -jar logger-application-0.0.1-SNAPSHOT.jar
- The application will start up on port 8000
- The application end points can be accessed by using the swagger end point : http://localhost:8080/swagger-ui/#/task-log-controller
- by using the script data.sql some dummy data will already be created in the h2 DB.
- Postman orchestration is placed in this repository

# Application implementation 

- User can Add, Edit , Update and Delete Tasks.
- The validation that Main task can be deleted/updated to closed only if subtasks are closed is implemented.
- All Exceptions are handled at a global exception handler
- Basic Junit test cases are written and  will be run during mvn install/package 
- Integration test is available (separate module) but has not been included in package due to an error (Need time to figure out ), but can be tested in eclipse

# Integration with front end 

- The application can be run along with the front end available at [Task Management Backend](https://github.com/vishnubabu077/task-management-system-frontend)

# Future Updates/Improvements

- Improve Validation 
- Running the application in Cloud Environment 
- Using flyway to version control DB
- More Junits 
- Combine Constants in a separate file 
