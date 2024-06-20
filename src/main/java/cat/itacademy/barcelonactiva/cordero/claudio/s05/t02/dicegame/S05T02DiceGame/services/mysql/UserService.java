package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql;

import org.springframework.security.core.userdetails.UserDetailsService;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.sql.User;

 
public interface UserService {
	
	UserDetailsService userDetailsService();
	User save(User newUser);
	UserDTO update(UserDTO updatedUser);
	boolean delete(int userId);
	UserDTO findUser(int userId);
 
}
