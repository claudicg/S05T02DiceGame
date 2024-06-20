package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.mappers;

import java.sql.Timestamp;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.sql.User;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.DateTimeUtils;

public class SQLMapper {

	public static UserDTO mapUserEntityToUserDto(User user) {
		
		UserDTO userDto = new UserDTO();
		userDto.setUserId(user.getUserId());
		userDto.setEmail(user.getEmail());
		userDto.setNickname(user.getNickname());
		userDto.setPassword(user.getPassword());
		userDto.setCreatedAt(DateTimeUtils.parseTimestamp(user.getCreatedAt()));
		userDto.setUpdatedAt(DateTimeUtils.parseTimestamp(user.getUpdatedAt()));
		userDto.setRole(user.getRole());
		
		return userDto;
	}
	
	public static User mapUserDtoToUserEntity(UserDTO userDto) {
		
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setEmail(userDto.getEmail());
		user.setNickname(userDto.getNickname());
		user.setPassword(userDto.getPassword());
		user.setCreatedAt(Timestamp.valueOf(userDto.getCreatedAt()));
		user.setUpdatedAt(Timestamp.valueOf(userDto.getUpdatedAt()));
		user.setRole(userDto.getRole());
		
		return user;
	}
	
}
