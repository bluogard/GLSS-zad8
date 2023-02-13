package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
@Slf4j
public class RegistryController {
    private List<Vehicle> list = new ArrayList<>();

    @Operation(summary = "Get vehicles",description = "Get a list of vehicles",tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found the Vehicles",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Vehicle.class))}),
            @ApiResponse(responseCode = "404",description = "Vehicles not found",
                    content = @Content)})
    @GetMapping
    public List<Vehicle> getData(){
        return list;
    }

    @Operation(summary = "Post vehicle",description = "Post a vehicle",tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Created vehicle",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehicle.class))}),
            @ApiResponse(responseCode = "400",description = "Cannot create a vehicle",
                    content = @Content)})
    @PostMapping
    public String addVehicle(@RequestBody Vehicle vehicle){
        list.add(vehicle);
        return "Dodano pojazd do listy";
    }
    @Operation(summary = "Delete vehicles",description = "Delete all vehicles",tags = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Deleted all vehicles",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehicle.class))}),
            @ApiResponse(responseCode = "400",description = "Cannot delete vehicles",
                    content = @Content)})
    @DeleteMapping
    public  String removeData(){
        list.clear();
        return "Usunięto listę pojazdów";
    }
}
