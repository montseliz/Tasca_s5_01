package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.service;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.dto.Message;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.exception.FlowerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientFlowerServiceImpl implements WebClientFlowerService {

    private final WebClient webClient;

    @Autowired
    public WebClientFlowerServiceImpl(WebClient webClient) {
        super();
        this.webClient = webClient;
    }

    /**
     * Duu a terme una sol·licitud HTTP GET a l'API del nivell 2 amb l'ID especificat per obtenir la flor amb l'identificador.
     * Retorna una instància Mono que conté la flor amb l'ID si aquesta es troba a la base de dades,
     * o una excepció personalitzada FlowerNotFoundException amb el missatge "Flower with ID {id} not found" si no es troba.
     * @param id l'ID de la flor a obtenir.
     */
    @Override
    public Mono<FlowerDTO> getFlowerById(int id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/getOne/{id}").build(id))
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .onErrorMap(WebClientResponseException.NotFound.class, error -> new FlowerNotFoundException("Flower with ID " + id + " not found"));
    }

    /**
     * Realitza una sol·licitud HTTP GET al servidor (API del nivell 2) per obtenir la llista de totes les flors de la base de dades.
     * Retorna un Flux que conté totes les flors, però si no hi ha cap flor introduïda a la base de dades,
     * llança l'excepció FlowerNotFoundException amb el missatge "There are no flowers introduced in the database".
     */
    @Override
    public Flux<FlowerDTO> getListFlowers() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/getAll").build())
                .retrieve()
                .bodyToFlux(FlowerDTO.class)
                .onErrorMap(WebClientResponseException.NotFound.class, error -> new FlowerNotFoundException("There are no flowers introduced in the database"));
    }

    /**
     * Fa una sol·licitud HTTP POST al servidor del nivell 2 per crear una nova flor amb les dades proporcionades
     * pel paràmetre flowerDTO. Si la creació de la flor es duu a terme correctament, retorna un Mono<Message> completat,
     * que indica la finalització de la sol·licitud.
     * @param flowerDTO l'objecte DTO que conté les dades de la flor a crear.
     */
    @Override
    public Mono<Message> createFlower(FlowerDTO flowerDTO) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/add").build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(flowerDTO)
                .retrieve()
                .bodyToMono(Message.class);
    }

    /**
     * Porta a terme una sol·licitud HTTP PUT al servidor del nivell 2, per editar una flor de la base de dades
     * amb l'ID proporcionat i els atributs de la flor actualitzats. Retorna un Mono<Message> completat,
     * o bé llença una FlowerNotFoundException en cas que retorni un error de client 404.
     * @param id l'ID de la flor a editar.
     * @param flowerDTO l'objecte DTO que conté els detalls actualitzats.
     */
    @Override
    public Mono<Message> editFlower(int id, FlowerDTO flowerDTO) {
        return webClient.put()
                .uri(uriBuilder -> uriBuilder.path("/update/{id}").build(id))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(flowerDTO)
                .retrieve()
                .bodyToMono(Message.class)
                .onErrorMap(WebClientResponseException.NotFound.class, error -> new FlowerNotFoundException("Flower with ID " + id + " not found"));
    }

    /**
     * Executa una sol·licitud HTTP DELETE al servidor (API del nivell 2) per eliminar una flor de la base de dades amb l'ID específic.
     * Retorna un Mono que emet un Message i que indica que la flor ha estat eliminada satisfactòriament,
     * o bé llença una FlowerNotFoundException si no es troba la flor amb l'ID especificat.
     * @param id el ID de la flor a eliminar.
     */
    @Override
    public Mono<Message> removeFlower(int id) {
        return webClient.delete()
                .uri(uriBuilder -> uriBuilder.path("/delete/{id}").build(id))
                .retrieve()
                .bodyToMono(Message.class)
                .onErrorMap(WebClientResponseException.NotFound.class, error -> new FlowerNotFoundException("Flower with ID " + id + " not found"));
    }
}
