package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.dto.BranchOfficeDTO;

import java.util.List;

public interface IBranchOfficeService {

    public BranchOfficeDTO getBranchOfficeById(int id);
    public List<BranchOfficeDTO> getAllBranchOffices();
    public BranchOfficeDTO createBranchOffice(BranchOfficeDTO branchOfficeDTO);
    public BranchOfficeDTO updateBranchOffice(int id, BranchOfficeDTO branchOfficeDTO);
    public void deleteBranchOffice(int id);

}
