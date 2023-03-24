package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.controllers;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.dto.Message;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n02.model.service.IFlowerService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

/**
 * Anotació @RestController per manipular sol·licituds HTTP en una API RESTful i retornar
 * objectes que es serialitzen automàticament en JSON/XML.
 */
@RestController
@RequestMapping ("/flowers")
@Validated
@Tag(name = "Flowers Management System", description = "API operations pertaining to flowers MySQL database")
public class FlowerController {


    private final IFlowerService flowerService;

    @Autowired
    public FlowerController(IFlowerService flowerService) {
        super();
        this.flowerService = flowerService;
    }


    @PostMapping("/add")
    @Operation(summary = "Create a new flower", description = "Adds a new flower into the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flower created correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class))}),
            @ApiResponse(responseCode = "406", description = "Flower values not valid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while creating the flower", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<Message> addFlower(@Valid @RequestBody FlowerDTO flowerDTO, WebRequest request) throws Exception {
        try {
            flowerService.createFlower(flowerDTO);
            return new ResponseEntity<>(new Message(HttpStatus.CREATED.value(), new Date(), "Flower created and added successfully into the database", request.getDescription(false)), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception("Internal Server Error while creating the flower", e.getCause());
        }
    }


    @PutMapping("/update/{id}")
    @Operation(summary = "Update a flower", description = "Updates an existing flower in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower updated correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Flower not found by id", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "406", description = "Flower updated values not valid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while updating the flower", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<Message> updateFlower(@Parameter(description = "The id of the flower to be updated") @PathVariable int id,
                                                @Valid @RequestBody FlowerDTO flowerDTO, WebRequest request) throws Exception {
        try {
            flowerService.editFlower(id, flowerDTO);
            return new ResponseEntity<>(new Message(HttpStatus.OK.value(), new Date(),"Flower updated correctly", request.getDescription(false)), HttpStatus.OK);
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

    public ResponseEntity<Message> deleteFlower(@Parameter(description = "The id of the flower to be removed") @PathVariable int id, WebRequest request) throws Exception {
        try {
            flowerService.removeFlower(id);
            return new ResponseEntity<>(new Message(HttpStatus.OK.value(), new Date(), "Flower removed successfully", request.getDescription(false)), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Internal Server Error while deleting the flower", e.getCause());
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

    public ResponseEntity<FlowerDTO> getOneFlower(@Parameter(description = "The id of the flower to retrieve") @PathVariable int id) throws Exception {
        try {
            return new ResponseEntity<>(flowerService.getFlowerById(id), HttpStatus.OK);
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

    public ResponseEntity<List<FlowerDTO>> getAllFlowers() throws Exception {
        try {
            return new ResponseEntity<>(flowerService.getListFlowers(), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Internal Server Error while retrieving the flowers from the database", e.getCause());
        }
    }















}
