package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.exception;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Anotació utilitzada per manipular excepcions en controladors que produeixen una resposta en format JSON o XML,
 * és a dir, que utilitzen l'anotació @RestController.
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(FlowerNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Message> flowerNotFoundExceptionHandler(FlowerNotFoundException exception, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.NOT_FOUND.value(), new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Message> internalServerErrorExceptionHandler(Exception exception, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
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
