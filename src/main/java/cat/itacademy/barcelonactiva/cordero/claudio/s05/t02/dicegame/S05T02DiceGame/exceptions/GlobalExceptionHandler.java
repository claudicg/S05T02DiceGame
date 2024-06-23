package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<ErrorMessage> invalidIdException(InvalidIdException exception, WebRequest request){
		
		ErrorMessage message = new ErrorMessage(
									HttpStatus.BAD_REQUEST.value(),
									new Date(),
									exception.getMessage(),
									request.getDescription(false));
		
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundException.class) 
	public ResponseEntity<ErrorMessage> notFoundException(NotFoundException exception, WebRequest request){
		
		ErrorMessage message = new ErrorMessage(
									HttpStatus.NOT_FOUND.value(),
									new Date(),
									exception.getMessage(),
									request.getDescription(false));
		
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<ErrorMessage> invalidEmailException(InvalidEmailException exception, WebRequest request){
		
		ErrorMessage message = new ErrorMessage(
									HttpStatus.BAD_REQUEST.value(),
									new Date(),
									exception.getMessage(),
									request.getDescription(false));
		
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}
}
