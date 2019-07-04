package com.b2w.starwars.view.support.builders;

import com.b2w.starwars.domain.PlanetDTO;
import com.b2w.starwars.view.PlanetEndpoint;
import com.b2w.starwars.view.support.resources.PlanetResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PlanetResourceAssembler extends ResourceAssemblerSupport<PlanetDTO, PlanetResource> {

    public PlanetResourceAssembler(){
        super(PlanetEndpoint.class, PlanetResource.class);
    }

    @Override
    public PlanetResource toResource(PlanetDTO planet){
        final PlanetResource resource = new PlanetResource();

        resource.setId(planet.getId());
        resource.setNome(planet.getNome());
        resource.setClima(planet.getClima());
        resource.setTerreno(planet.getTerreno());
        resource.setQuantidadeFilmes(planet.getQuantidadeFilmes());

        return resource;
    }
}
