package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.controllers;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.enums.Role;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.InvalidIdException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.UserService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.DateTimeUtils;

public class UserControllerTest {
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	public void updateOkTest() throws InvalidIdException, NotFoundException {
		
		int userId = 1;
		String nickname = "Julio";
		
		String createdAt = DateTimeUtils.parseTimestamp(DateTimeUtils.getCurrentDateTime());
		String updatedAt = DateTimeUtils.parseTimestamp(DateTimeUtils.getCurrentDateTime());
		
		UserDTO userDto = new UserDTO();
		userDto.setUserId(userId);
		userDto.setEmail("admin1@exemple.com");
		userDto.setPassword("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS");
		userDto.setCreatedAt(createdAt);
		userDto.setUpdatedAt(updatedAt);
		userDto.setRole(Role.USER);
		userDto.setNickname("Ana");
		
		UserDTO saveDto = new UserDTO();
		saveDto.setUserId(userId);
		saveDto.setEmail("admin1@exemple.com");
		saveDto.setPassword("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS");
		saveDto.setCreatedAt(createdAt);
		saveDto.setUpdatedAt(updatedAt);
		saveDto.setRole(Role.USER);
		saveDto.setNickname(nickname);
		
		when(userService.findUser(Mockito.anyInt())).thenReturn(userDto);
		
		when(userService.update(Mockito.any(), Mockito.anyString())).thenReturn(saveDto);
		
		ResponseEntity<UserDTO> result = userController.update(userId, nickname);
	
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody());
		Assertions.assertEquals(userId, result.getBody().getUserId());
		Assertions.assertEquals("admin1@exemple.com", result.getBody().getEmail());
		Assertions.assertEquals("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS", result.getBody().getPassword());
		Assertions.assertEquals(createdAt, result.getBody().getCreatedAt());
		Assertions.assertEquals(updatedAt, result.getBody().getUpdatedAt());
		Assertions.assertEquals(nickname, result.getBody().getNickname());
		
	}
	
		
	@Test
	public void deleteOkTest() throws InvalidIdException, NotFoundException {
		
		int userId = 1;
		
		when(userService.delete(Mockito.anyInt())).thenReturn(true);
		
		ResponseEntity<Boolean> result = userController.delete(userId);
	
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertTrue(result.getBody());
		
	}

}
