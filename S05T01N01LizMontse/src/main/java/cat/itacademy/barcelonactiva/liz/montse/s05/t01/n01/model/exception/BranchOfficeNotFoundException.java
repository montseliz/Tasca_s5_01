package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.exception;

import java.io.Serial;

public class BranchOfficeNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public BranchOfficeNotFoundException(String message) {
        super(message);
    }

}
