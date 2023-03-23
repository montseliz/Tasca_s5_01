package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchOfficeDTO {

    private int id;
    @NotBlank(message = "The branch office must have a name")
    private String name;
    @NotBlank(message = "The branch office must have a country")
    private String country;
    private String type;
    private final List<String> EUCOUNTRIES = List.of("Austria", "Belgium", "Bulgaria", "Croatia","Republic of Cyprus",
            "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy",
            "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia",
            "Slovenia", "Spain", "Sweden");

    public BranchOfficeDTO(String name, String country) {
        this.name = name;
        this.country = country;
    }

    /**
     * L'expressió booleana de l'operador ternari és el resultat del mètode anyMatch d'Stream, que retorna true ("EU")
     * si el país es troba en la llista EUCOUNTRIES. Si no és així, retorna false assignant "NOT EU" a l'atribut type.
     */
    public void setCountry(String country) {
        this.country = country;
        this.type = EUCOUNTRIES.stream().anyMatch(c -> c.equalsIgnoreCase(country)) ? "EU" : "NOT EU";
    }
}