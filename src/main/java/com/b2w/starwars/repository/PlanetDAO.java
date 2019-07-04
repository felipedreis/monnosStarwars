package com.b2w.starwars.repository;

import com.b2w.starwars.domain.entity.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetDAO extends MongoRepository<Planet, String> {

    public List<Planet> findAll();

    public List<Planet> findByNomeContaining(String name);

}
