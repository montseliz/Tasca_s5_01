package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.exception;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(FlowerNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Message> flowerNotFoundExceptionHandler(FlowerNotFoundException exception, ServerWebExchange exchange) {

        return new ResponseEntity<>(new Message(HttpStatus.NOT_FOUND.value(), new Date(), exception.getMessage(), exchange.getRequest().getURI().toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Message> internalServerErrorExceptionHandler(Exception exception, ServerWebExchange exchange) {

        return new ResponseEntity<>(new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), exception.getMessage(), exchange.getRequest().getURI().toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<List<Message>> validationExceptionHandler(MethodArgumentNotValidException exception) {

        List<Message> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(error ->
                errors.add(new Message(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), error.getDefaultMessage(), ((FieldError) error).getField())));

        return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
    }

}
