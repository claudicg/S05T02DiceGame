package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class DateTimeUtils {
	
	public static Timestamp getCurrentDateTime() {
		  Date date = new Date();
		  Timestamp timestamp = new Timestamp(date.getTime());
		  return timestamp;
	}
	
	public static String parseTimestamp(Timestamp timestamp) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(timestamp.getTime());
	}
}
