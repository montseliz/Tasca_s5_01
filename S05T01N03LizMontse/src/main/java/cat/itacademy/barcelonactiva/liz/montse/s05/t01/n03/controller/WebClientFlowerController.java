package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.controller;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.dto.Message;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.exception.FlowerNotFoundException;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.model.service.WebClientFlowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/clientFlowers")
@Validated
@Tag(name = "Flowers WebClient API", description = "Reactive API with WebClient to make HTTP requests to the Flowers Management System server API")
public class WebClientFlowerController {

    private WebClientFlowerService flowerService;

    @Autowired
    public WebClientFlowerController(WebClientFlowerService flowerService) {
        super();
        this.flowerService = flowerService;
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new flower", description = "Adds a new flower into the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flower created correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "406", description = "Flower values not valid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while creating the flower", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<Mono<Message>> addFlower(@Valid @RequestBody FlowerDTO flowerDTO, ServerWebExchange exchange) throws Exception {
        try {
            return new ResponseEntity<>(flowerService.createFlower(flowerDTO)
                    .map(flower -> new Message(HttpStatus.CREATED.value(), new Date(), "Flower created and added successfully into the database", exchange.getRequest().getURI().toString())),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception( "Internal Server Error while creating the flower", e.getCause());
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a flower", description = "Updates an existing flower in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower updated correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Flower not found by id", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "406", description = "Flower updated values not valid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while updating the flower", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<Mono<Message>> updateFlower(@Parameter(description = "The id of the flower to be updated") @PathVariable int id,
                                                      @Valid @RequestBody FlowerDTO flowerDTO, ServerWebExchange exchange) throws Exception {
        try {
            return new ResponseEntity<>(flowerService.editFlower(id, flowerDTO)
                    .map(flower -> new Message(HttpStatus.OK.value(), new Date(), "Flower updated correctly", exchange.getRequest().getURI().toString())),
                    HttpStatus.OK);
        } catch (FlowerNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while updating the flower", e.getCause());
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a flower", description = "Deletes an existing flower from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower removed successfully"),
            @ApiResponse(responseCode = "404", description = "Flower not found by id", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while deleting the flower", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<Mono<Message>> deleteFlower(@Parameter(description = "The id of the flower to be removed") @PathVariable int id,
                                                      ServerWebExchange exchange) throws Exception {
        try {
            return new ResponseEntity<>(flowerService.removeFlower(id)
                    .map(flower -> new Message(HttpStatus.OK.value(), new Date(), "Flower removed successfully", exchange.getRequest().getURI().toString())),
                    HttpStatus.OK);
        } catch (FlowerNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while updating the flower", e.getCause());
        }
    }

    @GetMapping("/getOne/{id}")
    @Operation(summary = "Get a flower by its ID", description = "Retrieves a flower from the database by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower retrieved successfully", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Flower not found by id", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while retrieving the flower", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<Mono<FlowerDTO>> getOneFlower(@Parameter(description = "The id of the flower to retrieve") @PathVariable int id) throws Exception {

        try {
            return new ResponseEntity<>(flowerService.getFlowerById(id), HttpStatus.OK);
        } catch (FlowerNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while retrieving the flower", e.getCause());
        }
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all flowers", description = "Returns a list with all the flowers stored in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of flowers retrieved successfully", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = FlowerDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "There are no flowers introduced in the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while retrieving the flowers from the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<Flux<FlowerDTO>> getAllFlowers() throws Exception {

        try {
            return new ResponseEntity<>(flowerService.getListFlowers(), HttpStatus.OK);
        } catch (FlowerNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while retrieving the flowers from the database", e.getCause());
        }

    }
}