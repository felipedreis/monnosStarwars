package com.b2w.starwars.services;

import com.b2w.starwars.application.impl.PlanetService;
import com.b2w.starwars.domain.PlanetDTO;
import com.b2w.starwars.domain.entity.Planet;
import com.b2w.starwars.repository.PlanetDAO;
import com.b2w.starwars.repository.StarWarsAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext
public class PlanetServiceTest {

    @MockBean
    private PlanetDAO planetDAO;

    @MockBean
    private StarWarsAPI starWarsAPI;

    @Autowired
    private PlanetService service;

    @Test
    public void listarPlanetas() {

        List<Planet> planets = new ArrayList<>();

        Planet planet1 = new Planet();
        planet1.setId("d5343jd8n8758");
        planet1.setNome("Uribe");
        planet1.setClima("Quente");
        planet1.setTerreno("Arido");
        planet1.setQuantidadeFilmes(2);

        Planet planet2 = new Planet();
        planet2.setId("f74k0gv1n334mm51");
        planet2.setNome("Kratos");
        planet2.setClima("Umido");
        planet2.setQuantidadeFilmes(1);

        planets.add(planet1);
        planets.add(planet2);

        when(planetDAO.findAll()).thenReturn(planets);

        List<PlanetDTO> dtos = new ArrayList<>();

        dtos.add(toDTO(planet1));
        dtos.add(toDTO(planet2));

        assertEquals(dtos, service.findAll());
    }

    @Test
    public void listarPlanetasAPI() {

        List<PlanetDTO> planets = new ArrayList<>();

        PlanetDTO planet1 = new PlanetDTO();
        planet1.setId("d5343jd8n8758");
        planet1.setNome("Uribe");
        planet1.setClima("Quente");
        planet1.setTerreno("Arido");
        planet1.setQuantidadeFilmes(2);

        PlanetDTO planet2 = new PlanetDTO();
        planet2.setId("f74k0gv1n334mm51");
        planet2.setNome("Kratos");
        planet2.setClima("Umido");
        planet2.setQuantidadeFilmes(1);

        planets.add(planet1);
        planets.add(planet2);

        when(starWarsAPI.getPlanetsByPage(1)).thenReturn(planets);

        assertEquals(planets, service.findByAPI(1));
    }

    @Test
    public void getPlaneta() {

        String id = "d5343jd8n8758";

        Planet planet = new Planet();
        planet.setId(id);
        planet.setNome("Uribe");
        planet.setClima("Quente");
        planet.setTerreno("Arido");
        planet.setQuantidadeFilmes(2);

        when(planetDAO.findById(id)).thenReturn(Optional.of(planet));

        assertEquals(toDTO(planet), service.findById(id).get());
    }

    @Test
    public void inserirPlaneta() {

        PlanetDTO planet = new PlanetDTO();
        planet.setNome("Uribe");
        planet.setClima("Quente");
        planet.setTerreno("Arido");

        when(starWarsAPI.getPlanetByName(planet.getNome())).thenReturn(null);

        service.create(planet);

        verify(planetDAO, times(1)).save(toEntity(planet));
        verify(starWarsAPI, times(1)).getPlanetByName(planet.getNome());
    }

    @Test
    public void inserirPlanetaExistenteNaAPI() {

        PlanetDTO planet = new PlanetDTO();
        planet.setNome("Uribe");
        planet.setClima("Quente");
        planet.setTerreno("Arido");

        PlanetDTO planetAPI = new PlanetDTO();
        planetAPI.setNome("Uribe");
        planetAPI.setClima("Quente");
        planetAPI.setTerreno("Arido");
        planetAPI.setQuantidadeFilmes(2);

        when(starWarsAPI.getPlanetByName(planet.getNome())).thenReturn(planetAPI);

        service.create(planet);

        verify(planetDAO, times(1)).save(toEntity(planet));
        verify(starWarsAPI, times(1)).getPlanetByName(planet.getNome());
    }

    @Test
    public void deletarPlaneta() {
        String id = "d5343jd8n8758";

        service.remove(id);

        verify(planetDAO, times(1)).deleteById(id);
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
