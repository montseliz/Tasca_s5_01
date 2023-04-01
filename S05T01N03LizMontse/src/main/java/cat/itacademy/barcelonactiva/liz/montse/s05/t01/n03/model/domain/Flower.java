package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flower {

    private int id;
    private String name;
    private String country;

    public Flower(String name, String country) {
        this.name = name;
        this.country = country;
    }

}