package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.service;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.exception.FlowerNotFoundException;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.repository.IFlowerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImpl implements IFlowerService {

    private IFlowerRepository flowerRepository;

    @Autowired
    public FlowerServiceImpl(IFlowerRepository flowerRepository) {
        super();
        this.flowerRepository = flowerRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    private FlowerDTO convertToDTO (Flower flower) {
        return modelMapper.map(flower, FlowerDTO.class);
    }

    private Flower convertToEntity (FlowerDTO flowerDTO) {
        return modelMapper.map(flowerDTO, Flower.class);
    }

    @Override
    public FlowerDTO getFlowerById(int id) {
        return flowerRepository.findById(id).map(this::convertToDTO).orElseThrow(() -> new FlowerNotFoundException("Flower with ID " + id + " not found"));
    }

    @Override
    public List<FlowerDTO> getListFlowers() {

        if (flowerRepository.findAll().isEmpty()) {
            throw new FlowerNotFoundException("There are no flowers introduced in the database");
        } else {
            return flowerRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        }
    }

    @Override
    public void createFlower(FlowerDTO flowerDTO) {
        Flower flowerConverted = convertToEntity(flowerDTO);
        flowerRepository.save(flowerConverted);
    }

    @Override
    public void editFlower(int id, FlowerDTO flowerDTO) {
        FlowerDTO flowerToUpdate = getFlowerById(id);
        flowerToUpdate.setName(flowerDTO.getName());
        flowerToUpdate.setCountry(flowerDTO.getCountry());
        flowerRepository.save(convertToEntity(flowerToUpdate));
    }

    @Override
    public void removeFlower(int id) {
        FlowerDTO flowerToDelete = getFlowerById(id);
        flowerRepository.delete(convertToEntity(flowerToDelete));
    }
}
