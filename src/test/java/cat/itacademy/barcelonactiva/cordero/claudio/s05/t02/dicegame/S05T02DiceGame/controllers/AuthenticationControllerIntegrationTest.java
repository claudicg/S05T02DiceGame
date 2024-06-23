package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignInRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignUpRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerIntegrationTest {

private final MockMvc mvc;
	
	@Autowired
	public AuthenticationControllerIntegrationTest(MockMvc mvc) {
		super();
		this.mvc = mvc;
	}
	
	@Test
	public void signinTest() throws Exception {
		
		UserSignInRequestDTO signInRequestDto = new UserSignInRequestDTO();
		
		signInRequestDto.setEmail("admin1@exemple.com");
		signInRequestDto.setPassword("123!");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		
		String requestJson = ow.writeValueAsString(signInRequestDto);
		
		this.mvc.perform(post("/dicegame/players/signin").contentType("application/json").content(requestJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.token").exists());
	}
	
//	@Test
//	public void signupTest() throws Exception {
//		
//		UserSignUpRequestDTO signUpRequestDto = new UserSignUpRequestDTO();
//		
//		signUpRequestDto.setEmail("test100@exemple.com");
//		signUpRequestDto.setNickname("Julio");
//		signUpRequestDto.setPassword("123!");
//		
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		
//		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//		
//		String requestJson = ow.writeValueAsString(signUpRequestDto);
//		
//		this.mvc.perform(post("/dicegame/players/signup").contentType("application/json").content(requestJson))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json"))
//				.andExpect(jsonPath("$.token").exists());
//	}
	
}
