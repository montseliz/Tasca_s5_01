package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * L'anotació @Data afegeix els getters, setters, equals, hashCode i toString.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "branch offices")
public class BranchOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "name")
    private String name;
    @Column (name = "country")
    private String country;

    public BranchOffice(String name, String country) {
        this.name = name;
        this.country = country;
    }

}
