package com.b2w.starwars.repository;

import com.b2w.starwars.domain.PlanetAPI;
import com.b2w.starwars.domain.PlanetAPIList;
import com.b2w.starwars.domain.PlanetDTO;
import com.b2w.starwars.infrastructure.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StarWarsAPI {

    private RestTemplate restTemplate;

    private final String URL = "https://swapi.py4e.com/api/planets";

    @Autowired
    public StarWarsAPI(@Qualifier(WebConfig.API_TEMPLATE) RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<PlanetDTO> getPlanetsByPage(Integer page){
        List<PlanetDTO> planets = new ArrayList<>();

        ResponseEntity<PlanetAPIList> response = restTemplate.exchange(URL + "?page=" + page, HttpMethod.GET, entity(), PlanetAPIList.class);

        for (PlanetAPI planetAPI : response.getBody().getResults()){
            planets.add(toDTO(planetAPI));
        }

        return planets;
    }

    public PlanetDTO getPlanetByName(String name){

        ResponseEntity<PlanetAPIList> response = restTemplate.exchange(URL + "?search=" + name, HttpMethod.GET, entity(), PlanetAPIList.class);
        PlanetDTO planetDTO = new PlanetDTO();

        if(response.getBody().getResults().size() == 0){
            return null;
        }

        for (PlanetAPI planetAPI : response.getBody().getResults()) {
            planetDTO = toDTO(planetAPI);
        }

        return planetDTO;
    }

    private PlanetDTO toDTO(PlanetAPI planetAPI){
        PlanetDTO planetDTO = new PlanetDTO();

        planetDTO.setNome(planetAPI.getName());
        planetDTO.setClima(planetAPI.getClimate());
        planetDTO.setTerreno(planetAPI.getTerrain());
        planetDTO.setQuantidadeFilmes(planetAPI.getFilms().size());

        return planetDTO;
    }

    private HttpEntity<String> entity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<String>("parameters", headers);
    }
}
