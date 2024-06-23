package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.sql.User;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.InvalidIdException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.mappers.SQLMapper;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.repositories.mysql.UserRepository;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.UserService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.DateTimeUtils;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Validations;
 
 
@Service("UserService")
public class UserServiceImpl implements UserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetailsService userDetailsService() {
 
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(Constants.Exceptions.USER_NOTFOUND));
			}
		};
		
	}
 
	@Override
	public User save(User newUser) {
		
		logger.info("UserServiceImpl :: save :: save new user.");
 
		if(newUser.getUserId() == null) {
			newUser.setCreatedAt(DateTimeUtils.getCurrentDateTime());
		}
		
		newUser.setUpdatedAt(DateTimeUtils.getCurrentDateTime());
		
		return userRepository.save(newUser);
		
	}

	@Override
	public UserDTO update(UserDTO updatedUser, String nickname) throws InvalidIdException {
		
		logger.info("UserServiceImpl :: update :: update new user.");
		
		if(!Validations.isValidNickName(nickname)) {
			updatedUser.setNickname(Constants.ANONYMOUS);
		}else {
			updatedUser.setNickname(nickname);
		}
		updatedUser.setUpdatedAt(DateTimeUtils.parseTimestamp(DateTimeUtils.getCurrentDateTime()));
		
		User user = SQLMapper.mapUserDtoToUserEntity(updatedUser);
		
		User userResponse = userRepository.save(user);
		
		return SQLMapper.mapUserEntityToUserDto(userResponse);	 
	}

	@Override
	public boolean delete(int userId) throws InvalidIdException {
		
		if(!Validations.isValidNumber(userId)) {
			throw new InvalidIdException(Constants.Exceptions.INVALID_ID);
		}
		
		int deletedRecords = userRepository.deleteUser(userId);
		return deletedRecords > 0 ? true : false;
		
	}

	@Override
	public UserDTO findUser(int userId) throws NotFoundException, InvalidIdException {
		
		logger.info("UserServiceImpl :: findUser :: find the user.");
		
		if(!Validations.isValidNumber(userId)) {
			throw new InvalidIdException(Constants.Exceptions.INVALID_ID);
		}
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException(Constants.Exceptions.USER_NOTFOUND));
		
		return SQLMapper.mapUserEntityToUserDto(user);
		
	}
}
 
