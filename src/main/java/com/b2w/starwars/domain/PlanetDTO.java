package com.b2w.starwars.domain;

public class PlanetDTO {

    public String id;
    public String nome;
    public String clima;
    public String terreno;
    public Integer quantidadeFilmes;

    public PlanetDTO(String id, String nome, String clima, String terreno, Integer quantidadeFilmes){
        this();
        this.id = id;
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
        this.quantidadeFilmes = quantidadeFilmes;
    }

    public PlanetDTO(){

    }

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
}
