package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.exception;

import java.io.Serial;

public class FlowerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    public FlowerNotFoundException(String message) {
        super(message);
    }
}
