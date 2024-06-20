package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mongo.impl;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.mongo.Game;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.mongo.Player;


@Service
public class PlayerServiceUtils {

	public Game throwDice() {
		 
		 Random random = new Random();
		 int dieOne = (int)(random.nextInt(6) + 1);
	     int dieTwo = (int)(random.nextInt(6) + 1);
	     return new Game(dieOne, dieTwo);
	}
	
	
	public Player recalculateSuccessPercentage(Player player) {
		
		List<Game> games = player.getGames();
		long successCount = games.stream().filter(Game::isResult).count();
	    int successPercentage = (int) ((successCount * 100) / games.size());
	    player.setSuccessPercentage(successPercentage);
	    
		return player;
	}
	
}
