package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.repository;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.domain.BranchOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBranchOfficeRepository extends JpaRepository<BranchOffice, Integer> {

}
