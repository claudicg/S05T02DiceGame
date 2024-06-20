package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.JwtAuthenticationResponseDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignInRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserSignUpRequestDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.sql.User;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.enums.Role;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.repositories.mysql.UserRepository;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.JwtService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mongo.PlayerService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.AuthenticationService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.UserService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;

@Service("AuthenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private static Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JwtAuthenticationResponseDTO signup(UserSignUpRequestDTO signUpRequestDto) {
		
		String nickname = (signUpRequestDto.getNickname() != null && !signUpRequestDto.getNickname().trim().isEmpty()) ?
				signUpRequestDto.getNickname().trim() : Constants.ANONYMOUS;
		
		User user = new User(signUpRequestDto.getEmail(), nickname, passwordEncoder.encode(signUpRequestDto.getPassword()), Role.USER);
		
		logger.info("AuthenticationServiceImpl :: AuthenticationManager :: save player data in sql.");
		user = userService.save(user);
		
		
		logger.info("AuthenticationServiceImpl :: AuthenticationManager :: save player data in Mongo.");
		playerService.saveDataInMongoCollectionPlayer(user);
		
		String jwt = jwtService.generateToken(user);
		
		return new JwtAuthenticationResponseDTO(jwt);
		
	}
	
	public JwtAuthenticationResponseDTO signin(UserSignInRequestDTO signInRequestDto) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDto.getEmail(), signInRequestDto.getPassword()));
		
		User user = userRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(() -> new IllegalArgumentException(Constants.Exceptions.INVALID_USER_PARAMS));
		
		String jwt = jwtService.generateToken(user);
		
		return new JwtAuthenticationResponseDTO(jwt);
		
	}
		
}
 
