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

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.JwtAuthenticationResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignInRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignUpRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.AuthenticationService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;

//@WebMvcTest(
//        controllers =  AuthenticationController.class,
//        excludeAutoConfiguration = {
//                UserDetailsServiceAutoConfiguration.class, SecurityAutoConfiguration.class
//        }
//)
public class AuthenticationControllerTest {
	
	@InjectMocks
	private AuthenticationController authenticationController;
	
	@Mock
	private AuthenticationService authenticationService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void signUpInvalidEmailTest() {
		
		UserSignUpRequestDTO userSignUpRequestDto = new UserSignUpRequestDTO();
		userSignUpRequestDto.setEmail("invalid_email");
		userSignUpRequestDto.setNickname("Juan");
		userSignUpRequestDto.setPassword("1234");
		
		ResponseEntity<JwtAuthenticationResponseDTO> result = authenticationController.signup(userSignUpRequestDto);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		Assertions.assertEquals("", result.getBody().getToken());
		Assertions.assertEquals("", result.getBody().getMessage());
		Assertions.assertEquals(Constants.Messages.INVALID_FORMAT_MAIL, result.getBody().getError());
	
	}
	
	@Test
	public void signUpOkTest() {
		
		UserSignUpRequestDTO userSignUpRequestDto = new UserSignUpRequestDTO();
		userSignUpRequestDto.setEmail("admin1@exemple.com");
		userSignUpRequestDto.setNickname("Juan");
		userSignUpRequestDto.setPassword("1234");
		
		JwtAuthenticationResponseDTO jwtDto = new JwtAuthenticationResponseDTO();
		jwtDto.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBleGVtcGxlLmNvbSIsImlhdCI6MTcxODU2NzYzNywiZXhwIjoxNzE4NjUyMjM3fQ.4XjhTfQK1MmWUsbLshEO9TLzKfQY7Gn5Odj4RD5T-8M");
		jwtDto.setMessage(Constants.Messages.SUCCESSFUL_SIGNUP);
		jwtDto.setError("");
		
		when(authenticationService.signup(Mockito.any())).thenReturn(jwtDto);
		
		ResponseEntity<JwtAuthenticationResponseDTO> result = authenticationController.signup(userSignUpRequestDto);
		
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBleGVtcGxlLmNvbSIsImlhdCI6MTcxODU2NzYzNywiZXhwIjoxNzE4NjUyMjM3fQ.4XjhTfQK1MmWUsbLshEO9TLzKfQY7Gn5Odj4RD5T-8M", result.getBody().getToken());
		Assertions.assertEquals(Constants.Messages.SUCCESSFUL_SIGNUP, result.getBody().getMessage());
		Assertions.assertEquals("", result.getBody().getError());
		
	}
	
	@Test
	public void signInInvalidEmailTest() {
		
		UserSignInRequestDTO userSignInRequestDto = new UserSignInRequestDTO();
		userSignInRequestDto.setEmail("invalid_email");
		userSignInRequestDto.setPassword("1234");
		
		ResponseEntity<JwtAuthenticationResponseDTO> result = authenticationController.signin(userSignInRequestDto);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		Assertions.assertEquals("", result.getBody().getToken());
		Assertions.assertEquals("", result.getBody().getMessage());
		Assertions.assertEquals(Constants.Messages.INVALID_FORMAT_MAIL, result.getBody().getError());
	
	}
	
	@Test
	public void signInOkTest() {
		
		UserSignInRequestDTO userSignInRequestDto = new UserSignInRequestDTO();
		userSignInRequestDto.setEmail("admin1@exemple.com");
		userSignInRequestDto.setPassword("1234");
		
		JwtAuthenticationResponseDTO jwtDto = new JwtAuthenticationResponseDTO();
		jwtDto.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBleGVtcGxlLmNvbSIsImlhdCI6MTcxODU2NzYzNywiZXhwIjoxNzE4NjUyMjM3fQ.4XjhTfQK1MmWUsbLshEO9TLzKfQY7Gn5Odj4RD5T-8M");
		jwtDto.setMessage(Constants.Messages.SUCCESSFUL_SINGIN);
		jwtDto.setError("");
		
		when(authenticationService.signin(Mockito.any())).thenReturn(jwtDto);
		
		ResponseEntity<JwtAuthenticationResponseDTO> result = authenticationController.signin(userSignInRequestDto);
		
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBleGVtcGxlLmNvbSIsImlhdCI6MTcxODU2NzYzNywiZXhwIjoxNzE4NjUyMjM3fQ.4XjhTfQK1MmWUsbLshEO9TLzKfQY7Gn5Odj4RD5T-8M", result.getBody().getToken());
		Assertions.assertEquals(Constants.Messages.SUCCESSFUL_SINGIN, result.getBody().getMessage());
		Assertions.assertEquals("", result.getBody().getError());
		
	}

}
