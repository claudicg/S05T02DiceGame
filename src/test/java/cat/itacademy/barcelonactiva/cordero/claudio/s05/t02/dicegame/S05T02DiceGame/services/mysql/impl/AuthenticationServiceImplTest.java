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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.JwtAuthenticationResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignInRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignUpRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.sql.User;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.enums.Role;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.InvalidEmailException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.repositories.mysql.UserRepository;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.JwtService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mongo.PlayerService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.UserService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.DateTimeUtils;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.AuthenticationService;

public class AuthenticationServiceImplTest {

	@InjectMocks
	private AuthenticationService authService = new AuthenticationServiceImpl();
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private UserService userService;
	
	@Mock
	private JwtService jwtService;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private AuthenticationManager authenticationManager;
	
	@Mock
	private PlayerService playerService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void signupTest() throws InvalidEmailException {
		
		UserSignUpRequestDTO userSignUp = new UserSignUpRequestDTO();
		userSignUp.setEmail("test2@exemple.com");
		userSignUp.setNickname("Juan");
		userSignUp.setPassword("1234");
		
		Timestamp createdAt = DateTimeUtils.getCurrentDateTime();
		Timestamp updatedAt = DateTimeUtils.getCurrentDateTime();
		
		User user = new User();
		user.setEmail("test2@exemple.com");
		user.setNickname("Juan");
		user.setPassword("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS");
		user.setCreatedAt(createdAt);
		user.setUpdatedAt(updatedAt);
		user.setRole(Role.USER);
		
		when(passwordEncoder.encode(Mockito.any())).thenReturn("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS");
		
		when(userService.save(Mockito.any())).thenReturn(user);
		
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBleGVtcGxlLmNvbSIsImlhdCI6MTcxODU2NzYzNywiZXhwIjoxNzE4NjUyMjM3fQ.4XjhTfQK1MmWUsbLshEO9TLzKfQY7Gn5Odj4RD5T-8M";
		
		when(jwtService.generateToken(Mockito.any())).thenReturn(token);
		
		JwtAuthenticationResponseDTO result = authService.signup(userSignUp);
		
		Assertions.assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBleGVtcGxlLmNvbSIsImlhdCI6MTcxODU2NzYzNywiZXhwIjoxNzE4NjUyMjM3fQ.4XjhTfQK1MmWUsbLshEO9TLzKfQY7Gn5Odj4RD5T-8M", result.getToken());
	}

	@Test
	public void signinTest() throws InvalidEmailException {
		
		UserSignInRequestDTO signInRequestDto = new UserSignInRequestDTO();
		signInRequestDto.setEmail("test2@exemple.com");
		signInRequestDto.setPassword("1234");
		
		Timestamp createdAt = DateTimeUtils.getCurrentDateTime();
		Timestamp updatedAt = DateTimeUtils.getCurrentDateTime();
		
		User user = new User();
		user.setUserId(1);
		user.setEmail("test2@exemple.com");
		user.setNickname("Ana");
		user.setPassword("$2a$10$2oWabFiUIkxed/6/AGPKnen.cyOy.WguOo6ajvmV241kAIuefBdsS");
		user.setCreatedAt(createdAt);
		user.setUpdatedAt(updatedAt);
		user.setRole(Role.USER);
		
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
		
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBleGVtcGxlLmNvbSIsImlhdCI6MTcxODU2NzYzNywiZXhwIjoxNzE4NjUyMjM3fQ.4XjhTfQK1MmWUsbLshEO9TLzKfQY7Gn5Odj4RD5T-8M";
		
		when(jwtService.generateToken(Mockito.any())).thenReturn(token);	
		
		JwtAuthenticationResponseDTO result = authService.signin(signInRequestDto);
		
		Assertions.assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBleGVtcGxlLmNvbSIsImlhdCI6MTcxODU2NzYzNywiZXhwIjoxNzE4NjUyMjM3fQ.4XjhTfQK1MmWUsbLshEO9TLzKfQY7Gn5Odj4RD5T-8M", result.getToken());
	}
	
}
