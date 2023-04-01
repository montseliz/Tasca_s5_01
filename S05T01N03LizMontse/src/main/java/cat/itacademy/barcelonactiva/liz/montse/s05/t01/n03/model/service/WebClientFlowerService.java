package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.service;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.dto.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WebClientFlowerService {

    Mono<FlowerDTO> getFlowerById(int id);
    Flux<FlowerDTO> getListFlowers();
    Mono<Message> createFlower(FlowerDTO flowerDTO);
    Mono<Message> editFlower(int id, FlowerDTO flowerDTO);
    Mono<Message> removeFlower(int id);

}
