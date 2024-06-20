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

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo.DeleteResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.enums.Role;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.InvalidException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.UserService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;
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
	public void updateInvalidUserIdTest() throws InvalidException {
		
		int userId = 0;
		String nickname = "Juan";
		
		ResponseEntity<UserResponseDTO> result = userController.update(userId, nickname);
	
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		Assertions.assertEquals(null, result.getBody().getUserDto());
		Assertions.assertEquals("", result.getBody().getMessage());
		Assertions.assertEquals(Constants.Exceptions.INVALID_ID, result.getBody().getError());
		
	}
	
	@Test
	public void updateInvalidNicknameTest() throws InvalidException {
		
		int userId = 1;
		String nickname = null;
		
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
		saveDto.setNickname(Constants.ANONYMOUS);
		
		when(userService.findUser(Mockito.anyInt())).thenReturn(userDto);
		
		when(userService.update(Mockito.any())).thenReturn(saveDto);
		
		ResponseEntity<UserResponseDTO> result = userController.update(userId, nickname);
	
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody().getUserDto());
		Assertions.assertEquals(userId, result.getBody().getUserDto().getUserId());
		Assertions.assertEquals("admin1@exemple.com", result.getBody().getUserDto().getEmail());
		Assertions.assertEquals("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS", result.getBody().getUserDto().getPassword());
		Assertions.assertEquals(createdAt, result.getBody().getUserDto().getCreatedAt());
		Assertions.assertEquals(updatedAt, result.getBody().getUserDto().getUpdatedAt());
		Assertions.assertEquals(Constants.ANONYMOUS, result.getBody().getUserDto().getNickname());
		Assertions.assertEquals(Constants.Messages.SUCCESSFUL_UPDATE, result.getBody().getMessage());
		Assertions.assertEquals("", result.getBody().getError());
		
	}
	
	@Test
	public void updateUserNotFoundTest() throws InvalidException {
		
		int userId = 1;
		String nickname = "Juan";
		
		when(userService.findUser(Mockito.anyInt())).thenReturn(null);
		
		ResponseEntity<UserResponseDTO> result = userController.update(userId, nickname);
	
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
		Assertions.assertNull(result.getBody().getUserDto());
		Assertions.assertEquals("", result.getBody().getMessage());
		Assertions.assertEquals(Constants.Exceptions.USER_NOTFOUND, result.getBody().getError());
		
	}
	
	@Test
	public void updateOkTest() throws InvalidException {
		
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
		
		when(userService.update(Mockito.any())).thenReturn(saveDto);
		
		ResponseEntity<UserResponseDTO> result = userController.update(userId, nickname);
	
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertNotNull(result.getBody().getUserDto());
		Assertions.assertEquals(userId, result.getBody().getUserDto().getUserId());
		Assertions.assertEquals("admin1@exemple.com", result.getBody().getUserDto().getEmail());
		Assertions.assertEquals("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS", result.getBody().getUserDto().getPassword());
		Assertions.assertEquals(createdAt, result.getBody().getUserDto().getCreatedAt());
		Assertions.assertEquals(updatedAt, result.getBody().getUserDto().getUpdatedAt());
		Assertions.assertEquals(nickname, result.getBody().getUserDto().getNickname());
		Assertions.assertEquals(Constants.Messages.SUCCESSFUL_UPDATE, result.getBody().getMessage());
		Assertions.assertEquals("", result.getBody().getError());
		
	}
	
	@Test
	public void deleteInvalidIdTest() throws InvalidException {
		
		int userId = 0;
		
		ResponseEntity<DeleteResponseDTO> result = userController.delete(userId);
	
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		Assertions.assertFalse(result.getBody().isResult());
		Assertions.assertEquals("", result.getBody().getMessage());
		Assertions.assertEquals(Constants.Exceptions.INVALID_ID, result.getBody().getError());
		
	}
	
	@Test
	public void deleteOkTest() throws InvalidException {
		
		int userId = 1;
		
		when(userService.delete(Mockito.anyInt())).thenReturn(true);
		
		ResponseEntity<DeleteResponseDTO> result = userController.delete(userId);
	
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertTrue(result.getBody().isResult());
		Assertions.assertEquals(Constants.Messages.DELETED, result.getBody().getMessage());
		Assertions.assertEquals("", result.getBody().getError());
		
	}

}
