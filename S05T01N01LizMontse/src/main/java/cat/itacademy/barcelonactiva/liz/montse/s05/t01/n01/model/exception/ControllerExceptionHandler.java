package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Anotació utilitzada per manipular excepcions en controladors que produeixen una resposta en format HTML,
 * és a dir, que utilitzen l'anotació @Controller.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BranchOfficeNotFoundException.class)
    public String branchOfficeNotFoundExceptionHandler(BranchOfficeNotFoundException exception, Model model) {
        return "branchOffices";
    }

}
