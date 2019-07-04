package com.b2w.starwars.application;

import com.b2w.starwars.domain.PlanetDTO;

import java.util.Collection;
import java.util.Optional;

public interface PlanetFacade {

    Collection<PlanetDTO> findAll();

    Collection<PlanetDTO> findByAPI(Integer page);

    Collection<PlanetDTO> findByNome(String nome);

    Optional<PlanetDTO> findById(String id);

    Optional<PlanetDTO> create(PlanetDTO planet);

    void remove(String id);
}
