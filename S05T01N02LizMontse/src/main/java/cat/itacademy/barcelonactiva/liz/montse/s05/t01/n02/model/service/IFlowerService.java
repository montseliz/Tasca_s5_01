package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.service;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.dto.FlowerDTO;
import java.util.List;

public interface IFlowerService {

    FlowerDTO getFlowerById(int id);
    List<FlowerDTO> getListFlowers();
    void createFlower(FlowerDTO flowerDTO);
    void editFlower(int id, FlowerDTO flowerDTO);
    void removeFlower(int id);




}
