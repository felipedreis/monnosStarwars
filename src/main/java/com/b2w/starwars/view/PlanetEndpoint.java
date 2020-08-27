package com.b2w.starwars.view;

import com.b2w.starwars.application.PlanetFacade;
import com.b2w.starwars.domain.PlanetDTO;
import com.b2w.starwars.view.support.builders.PlanetResourceAssembler;
import com.b2w.starwars.view.support.resources.PlanetResource;
import com.b2w.starwars.infrastructure.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/planets")
public class PlanetEndpoint {

    @Autowired
    private PlanetResourceAssembler planetAssembler;

    @Autowired
    private PlanetFacade service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<PlanetResource> getPlanets(@RequestParam(value="query", required = false) String query) {
        if(query == null) {
            return new Resources<>(planetAssembler.toResources(service.findAll()));
        }

        return new Resources<>(planetAssembler.toResources(service.findByNome(query)));
    }

    @GetMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<PlanetResource> getPlanetsAPI(@RequestParam(value="page", required = false, defaultValue = "1") Integer page) {
        return new Resources<>(planetAssembler.toResources(service.findByAPI(page)));
    }

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanetResource getPlanet(@PathVariable String id) {
        Optional<PlanetDTO> planet = service.findById(id);

        if(!planet.isPresent()) {
            throw new NotFoundException("Planet not found in database.");
        }

        return planetAssembler.toResource(planet.get());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPlanet(@RequestBody PlanetResource planetResource){

        PlanetDTO planet = new PlanetDTO();

        planet.setNome(planetResource.getNome());
        planet.setClima(planetResource.getClima());
        planet.setTerreno(planetResource.getTerreno());

        service.create(planet);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePlanet(@PathVariable String id){
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}
