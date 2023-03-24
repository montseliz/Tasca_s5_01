package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FlowerDTO {

    private int id;
    @NotBlank(message = "The flower must have a name")
    private String name;
    @NotBlank(message = "The flower must have a country")
    private String country;
    private String type;
    private final List<String> EUCOUNTRIES = List.of("Austria", "Belgium", "Bulgaria", "Croatia","Republic of Cyprus",
            "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy",
            "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia",
            "Slovenia", "Spain", "Sweden");

    public FlowerDTO(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public void setCountry(String country) {
        this.country = country;
        this.type = EUCOUNTRIES.stream().anyMatch(c -> c.equalsIgnoreCase(country)) ? "EU" : "NOT EU";
    }

}
