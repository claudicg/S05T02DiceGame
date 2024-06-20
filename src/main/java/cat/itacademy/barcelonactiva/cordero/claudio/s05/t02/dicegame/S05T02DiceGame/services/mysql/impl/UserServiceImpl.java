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
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.mappers.SQLMapper;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.repositories.mysql.UserRepository;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.UserService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.DateTimeUtils;
 
 
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
	public UserDTO update(UserDTO updatedUser) {
		
		logger.info("UserServiceImpl :: update :: update new user.");
		
		User user = SQLMapper.mapUserDtoToUserEntity(updatedUser);
		
		User userResponse = userRepository.save(user);
		
		return SQLMapper.mapUserEntityToUserDto(userResponse);	 
	}

	@Override
	public boolean delete(int userId) {
		
		int deletedRecords = userRepository.deleteUser(userId);
		return deletedRecords > 0 ? true : false;
		
	}

	@Override
	public UserDTO findUser(int userId) {
		
		logger.info("UserServiceImpl :: findUser :: find the user.");
		
		User user = userRepository.findById(userId).orElse(null);
		
		if(user != null) {
			return SQLMapper.mapUserEntityToUserDto(user);
		} else {
			return null;
		}	
		
	}
}
 
