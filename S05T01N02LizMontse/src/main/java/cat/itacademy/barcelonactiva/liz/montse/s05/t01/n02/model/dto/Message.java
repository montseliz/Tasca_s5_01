package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Message {

    private int statusCode;
    private Date timestamp;
    private String textMessage;
    private String description;

}
