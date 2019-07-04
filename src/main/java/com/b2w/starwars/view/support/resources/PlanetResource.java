package com.b2w.starwars.view.support.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;

@JsonPropertyOrder({
        "id",
        "nome",
        "clima",
        "terreno",
        "quantidadeFilmes"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanetResource extends ResourceSupport {

    private String id;
    private String nome;
    private String clima;
    private String terreno;
    private Integer quantidadeFilmes;

    @JsonProperty("id")
    public String getPlanetId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public Integer getQuantidadeFilmes() {
        return quantidadeFilmes;
    }

    public void setQuantidadeFilmes(Integer quantidadeFilmes) {
        this.quantidadeFilmes = quantidadeFilmes;
    }
}
