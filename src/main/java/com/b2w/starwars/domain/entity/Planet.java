package com.b2w.starwars.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("Planet")
public class Planet {

    @Id
    private String id;

    private String nome;

    private String clima;

    private String terreno;

    private Integer quantidadeFilmes;

    public String getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(id, planet.id) &&
                Objects.equals(nome, planet.nome) &&
                Objects.equals(clima, planet.clima) &&
                Objects.equals(terreno, planet.terreno) &&
                Objects.equals(quantidadeFilmes, planet.quantidadeFilmes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, clima, terreno, quantidadeFilmes);
    }
}
