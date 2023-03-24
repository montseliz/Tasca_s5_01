package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.dto.BranchOfficeDTO;

import java.util.List;

public interface IBranchOfficeService {

    BranchOfficeDTO getBranchOfficeById(int id);
    List<BranchOfficeDTO> getAllBranchOffices();
    void createBranchOffice(BranchOfficeDTO branchOfficeDTO);
    void updateBranchOffice(int id, BranchOfficeDTO branchOfficeDTO);
    void deleteBranchOffice(int id);

}
