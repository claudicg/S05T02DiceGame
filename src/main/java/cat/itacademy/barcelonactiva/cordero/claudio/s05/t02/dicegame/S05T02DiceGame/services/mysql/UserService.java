package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql;

import org.springframework.security.core.userdetails.UserDetailsService;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql.UserDTO;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.sql.User;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.InvalidIdException;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions.NotFoundException;

 
public interface UserService {
	
	UserDetailsService userDetailsService();
	User save(User newUser);
	UserDTO update(UserDTO updatedUser, String nickname) throws InvalidIdException;
	boolean delete(int userId) throws InvalidIdException;
	UserDTO findUser(int userId) throws NotFoundException, InvalidIdException;
 
}
