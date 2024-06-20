package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.impl;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.UserService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.DateTimeUtils;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.sql.User;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.enums.Role;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.repositories.mysql.UserRepository;

public class UserServiceImplTest {

	@InjectMocks
	private UserService userService = new UserServiceImpl();
	
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void saveTest() {
		
		Timestamp createdAt = DateTimeUtils.getCurrentDateTime();
		Timestamp updatedAt = DateTimeUtils.getCurrentDateTime();
		
		User userRequest = new User();
		userRequest.setUserId(null);
		userRequest.setEmail("test1@exemple.com");
		userRequest.setNickname("Juan");
		userRequest.setPassword("1234");
		
		User user = new User();
		user.setUserId(1);
		user.setEmail("test1@exemple.com");
		user.setNickname("Juan");
		user.setPassword("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS");
		user.setCreatedAt(createdAt);
		user.setUpdatedAt(updatedAt);
		user.setRole(Role.USER);
		
		when(userRepository.save(Mockito.any())).thenReturn(user);
		
		User result = userService.save(userRequest);
		
		Assertions.assertEquals(1, result.getUserId());
		Assertions.assertEquals("test1@exemple.com", result.getEmail());
		Assertions.assertEquals("Juan", result.getNickname());
		Assertions.assertEquals("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS", result.getPassword());
		Assertions.assertEquals(createdAt, result.getCreatedAt());
		Assertions.assertEquals(updatedAt, result.getUpdatedAt());
		Assertions.assertEquals(Role.USER, result.getRole());
		
	}
	
	@Test
	public void updateTest() {
		
		String createdAt = DateTimeUtils.parseTimestamp(DateTimeUtils.getCurrentDateTime());
		String updatedAt = DateTimeUtils.parseTimestamp(DateTimeUtils.getCurrentDateTime());
		
		UserDTO userDto = new UserDTO();
		userDto.setUserId(1);
		userDto.setEmail("test1@exemple.com");
		userDto.setPassword("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS");
		userDto.setCreatedAt(createdAt);
		userDto.setUpdatedAt(updatedAt);
		userDto.setRole(Role.USER);
		userDto.setNickname("Ana");
		
		User user = new User();
		user.setUserId(1);
		user.setEmail("test1@exemple.com");
		user.setNickname("Ana");
		user.setPassword("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS");
		user.setCreatedAt(Timestamp.valueOf(createdAt));
		user.setUpdatedAt(Timestamp.valueOf(updatedAt));
		user.setRole(Role.USER);
		
		when(userRepository.save(Mockito.any())).thenReturn(user);
		
		UserDTO result = userService.update(userDto);
		
		Assertions.assertEquals(1, result.getUserId());
		Assertions.assertEquals("test1@exemple.com", result.getEmail());
		Assertions.assertEquals("Ana", result.getNickname());
		Assertions.assertEquals("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS", result.getPassword());
		Assertions.assertEquals(createdAt, result.getCreatedAt());
		Assertions.assertEquals(updatedAt, result.getUpdatedAt());
		Assertions.assertEquals(Role.USER, result.getRole());
	
	}
	
	@Test
	public void deleteTrueTest() {
		
		when(userRepository.deleteUser(Mockito.anyInt())).thenReturn(1);
		
		boolean result = userService.delete(1);
		
		Assertions.assertTrue(result);
	}
	
	@Test
	public void deleteFalseTest() {
		
		when(userRepository.deleteUser(Mockito.anyInt())).thenReturn(0);
		
		boolean result = userService.delete(1);
		
		Assertions.assertFalse(result);
	}
	
	@Test
	public void findUserTest() {
		
		int userId = 1;
		
		Timestamp createdAt = DateTimeUtils.getCurrentDateTime();
		Timestamp updatedAt = DateTimeUtils.getCurrentDateTime();
		
		User user = new User();
		user.setUserId(userId);
		user.setEmail("test1@exemple.com");
		user.setNickname("Ana");
		user.setPassword("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS");
		user.setCreatedAt(createdAt);
		user.setUpdatedAt(updatedAt);
		user.setRole(Role.USER);
		
		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		
		UserDTO result = userService.findUser(userId);
		
		Assertions.assertEquals(userId, result.getUserId());
		Assertions.assertEquals("test1@exemple.com", result.getEmail());
		Assertions.assertEquals("Ana", result.getNickname());
		Assertions.assertEquals("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS", result.getPassword());
		Assertions.assertEquals(Role.USER, result.getRole());
	}
}
