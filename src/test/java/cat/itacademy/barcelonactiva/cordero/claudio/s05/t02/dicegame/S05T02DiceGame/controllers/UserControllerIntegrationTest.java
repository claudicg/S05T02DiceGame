package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.controllers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
	
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@Autowired
	public UserControllerIntegrationTest(MockMvc mvc) {
		super();
		this.mvc = mvc;
	}
	
	@BeforeEach()
	public void setup(){
	    
	    this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void updateTest() throws Exception {
		
		this.mvc.perform(put("/dicegame/users/{userId}/update/{nickname}", 2, "Ana"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.userDto.nickname").value("Ana"));
	}
	
}
