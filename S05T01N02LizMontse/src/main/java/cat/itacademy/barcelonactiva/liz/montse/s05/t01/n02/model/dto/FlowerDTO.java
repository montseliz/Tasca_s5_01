package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDTO {

    @Schema(description = "Identifier of the flower", example = "1", required = true)
    private int id;

    @Schema(description = "Name of the flower", example = "Rose", required = true)
    @NotBlank(message = "The flower must have a name")
    private String name;

    @Schema(description = "Country where the flower is from", example = "France", required = true)
    @NotBlank(message = "The flower must have a country")
    private String country;

    @Schema(description = "To indicate if the country belongs to the European Union or not", example = "EU")
    private String type;

    @JsonIgnore
    private List<String> EUcountries = List.of("Austria", "Belgium", "Bulgaria", "Croatia","Republic of Cyprus",
            "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy",
            "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia",
            "Slovenia", "Spain", "Sweden");

    public FlowerDTO(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public void setCountry(String country) {
        this.country = country;
        this.type = EUcountries.stream().anyMatch(c -> c.equalsIgnoreCase(country)) ? "EU" : "NOT EU";
    }

}
