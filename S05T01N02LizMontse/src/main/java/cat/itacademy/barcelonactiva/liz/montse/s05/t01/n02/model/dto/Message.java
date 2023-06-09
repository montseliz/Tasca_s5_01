package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Message {

    private int statusCode;
    private Date timestamp;
    private String textMessage;
    private String description;

}
