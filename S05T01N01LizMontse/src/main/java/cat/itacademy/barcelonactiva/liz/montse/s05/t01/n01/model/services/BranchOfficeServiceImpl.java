package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.domain.BranchOffice;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.dto.BranchOfficeDTO;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.exception.BranchOfficeNotFoundException;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.repository.IBranchOfficeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchOfficeServiceImpl implements IBranchOfficeService {

    private final IBranchOfficeRepository branchOfficeRepository;

    @Autowired
    public BranchOfficeServiceImpl(IBranchOfficeRepository branchOfficeRepository) {
        super();
        this.branchOfficeRepository = branchOfficeRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Mètode que converteix una entity a un DTO
     * @param branchOffice
     * @return branchOfficeDTO
     */
    private BranchOfficeDTO convertToDTO(BranchOffice branchOffice) {
        return modelMapper.map(branchOffice, BranchOfficeDTO.class);
    }

    /**
     * Mètode que converteix un DTO a una entity
     * @param branchOfficeDTO
     * @return branchOffice
     */
    private BranchOffice convertToEntity(BranchOfficeDTO branchOfficeDTO) {
        return modelMapper.map(branchOfficeDTO, BranchOffice.class);
    }

    @Override
    public BranchOfficeDTO getBranchOfficeById(int id) {

        return branchOfficeRepository.findById(id).map(this::convertToDTO)
                        .orElseThrow(() -> new BranchOfficeNotFoundException("Branch Office not found"));
    }

    @Override
    public List<BranchOfficeDTO> getAllBranchOffices() {
        List<BranchOffice> branchOfficesFromDB = branchOfficeRepository.findAll();

        return branchOfficesFromDB.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public BranchOfficeDTO createBranchOffice(BranchOfficeDTO branchOfficeDTO) {
        BranchOffice branchOfficeConverted = convertToEntity(branchOfficeDTO);
        BranchOffice newBranchOffice = branchOfficeRepository.save(branchOfficeConverted);

        return convertToDTO(newBranchOffice);
    }

    @Override
    public BranchOfficeDTO updateBranchOffice(int id, BranchOfficeDTO branchOfficeDTO) {
        BranchOfficeDTO branchOfficeToUpdate = getBranchOfficeById(id);
        branchOfficeToUpdate.setName(branchOfficeDTO.getName());
        branchOfficeToUpdate.setCountry(branchOfficeDTO.getCountry());
        branchOfficeRepository.save(convertToEntity(branchOfficeToUpdate));

        return branchOfficeToUpdate;
    }

    @Override
    public void deleteBranchOffice(int id) {
        BranchOfficeDTO branchOfficeToDelete = getBranchOfficeById(id);
        branchOfficeRepository.delete(convertToEntity(branchOfficeToDelete));
    }
}
