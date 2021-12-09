package com.task.logger.integration.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.task.logger.application.TaskLogger;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { TaskLogger.class })
@RunWith(SpringRunner.class)
public class TaskLogControllerTest {

//	@Autowired
//	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetTask() throws Exception {
		mockMvc.perform(get("/task/getAll")).andDo(print()).andExpect(status().isOk())
         .andExpect(jsonPath("$.[0].taskName").value("test")) 
         .andExpect(jsonPath("$.[0]taskAssignee").value("admin"));
				
	}
	
	@Test
	public void testAddTask() throws Exception {
		JSONObject obj = new JSONObject();

		obj.put("taskName", "test");
		obj.put("timeSpent", 1);
		obj.put("taskGroup", "test");
		mockMvc.perform(post("/task/save")
		           .contentType(MediaType.APPLICATION_JSON)
		           .content(obj.toString()) 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isCreated());	
		           
				
	}
	
	

}
