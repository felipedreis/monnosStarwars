package com.b2w.starwars.application.impl;

import com.b2w.starwars.application.PlanetFacade;
import com.b2w.starwars.domain.PlanetDTO;
import com.b2w.starwars.domain.entity.Planet;
import com.b2w.starwars.repository.PlanetDAO;
import com.b2w.starwars.repository.StarWarsAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetService implements PlanetFacade {

    @Autowired
    PlanetDAO planetDAO;

    @Autowired
    StarWarsAPI starWarsAPI;

    @Override
    public Collection<PlanetDTO> findAll() {
        List<PlanetDTO> planets = new ArrayList<>();

        for(Planet planet : planetDAO.findAll()){
            planets.add(toDTO(planet));
        }

        return planets;
    }

    @Override
    public Collection<PlanetDTO> findByAPI(Integer page) {
        Collection<PlanetDTO> planets = starWarsAPI.getPlanetsByPage(page);
        persistPlanetsLocally(planets);
        return planets;
    }

    @Override
    public Collection<PlanetDTO> findByNome(String nome) {

        List<PlanetDTO> planets = new ArrayList<>();

        for(Planet planet : planetDAO.findByNomeContaining(nome)){
            planets.add(toDTO(planet));
        }

        return planets;
    }

    @Override
    public Optional<PlanetDTO> findById(String id) {

        Optional<Planet> optionalPlanet = planetDAO.findById(id);

        if(!optionalPlanet.isPresent()){
            return Optional.empty();
        }

        return Optional.of(toDTO(optionalPlanet.get()));
    }

    @Override
    public Optional<PlanetDTO> create(PlanetDTO planet) {

        PlanetDTO planetAPI = starWarsAPI.getPlanetByName(planet.getNome());

        if(planetAPI != null){
            planet.setQuantidadeFilmes(planetAPI.getQuantidadeFilmes());
        }

        planetDAO.save(toEntity(planet));

        return Optional.of(planet);
    }

    @Override
    public void remove(String id) {
        planetDAO.deleteById(id);
    }

    private void persistPlanetsLocally(Collection<PlanetDTO> planets) {
        for (PlanetDTO externalPlanet : planets) {
            List<Planet> localPlanets = planetDAO.findByNomeContaining(externalPlanet.getNome());

            if (!localPlanets.isEmpty()) {
                for (Planet local : localPlanets) {
                    if (local.getNome().equals(externalPlanet.getNome())) {
                        local.setNome(externalPlanet.getNome());
                        local.setClima(externalPlanet.getClima());
                        local.setTerreno(externalPlanet.getTerreno());
                        local.setQuantidadeFilmes(externalPlanet.getQuantidadeFilmes());
                        planetDAO.save(local);
                    }
                }
            } else {
                planetDAO.save(toEntity(externalPlanet));
            }
        }
    }

    private Planet toEntity(PlanetDTO dto){
        Planet planet = new Planet();

        planet.setNome(dto.getNome());
        planet.setClima(dto.getClima());
        planet.setTerreno(dto.getTerreno());
        planet.setQuantidadeFilmes(dto.getQuantidadeFilmes());

        return planet;
    }

    private PlanetDTO toDTO(Planet planet){
        PlanetDTO dto = new PlanetDTO();

        dto.setId(planet.getId());
        dto.setNome(planet.getNome());
        dto.setClima(planet.getClima());
        dto.setTerreno(planet.getTerreno());
        dto.setQuantidadeFilmes(planet.getQuantidadeFilmes());

        return dto;
    }
}
