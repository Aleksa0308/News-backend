package com.example.news.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Vest {
    private Integer id;
    @NotNull(message = "kategorija can't be null")
    @NotEmpty(message = "kategorija cant be empty")
    private Integer kategorijaId;
    @NotNull(message = "naslov can't be null")
    @NotEmpty(message = "naslov cant be empty")
    private String naslov;
    @NotNull(message = "tekst can't be null")
    @NotEmpty(message = "tekst cant be empty")
    private String tekst;
    private String autor;
    private Date vremeKreiranja;
    private Integer brPoseta;

    public Vest() {
    }


    public Vest(String naslov, String tekst, String autor) {
        this();
        this.naslov = naslov;
        this.tekst = tekst;
        this.autor = autor;
    }
    public Vest(Integer id, Integer kategorijaId, String naslov, String tekst, String autor, Integer brPoseta, Date vremeKreiranja) {
        this(naslov, tekst, autor);
        this.id = id;
        this.kategorijaId = kategorijaId;
        this.brPoseta = brPoseta;
        this.vremeKreiranja = vremeKreiranja;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(Integer kategorijaId) {
        this.kategorijaId = kategorijaId;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getVremeKreiranja() {
        return vremeKreiranja;
    }

    public void setVremeKreiranja(Date vremeKreiranja) {
        this.vremeKreiranja = vremeKreiranja;
    }

    public Integer getBrPoseta() {
        return brPoseta;
    }

    public void setBrPoseta(Integer brPoseta) {
        this.brPoseta = brPoseta;
    }

}
