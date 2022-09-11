package com.example.news.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Komentar {
    private Integer id;
    private Integer vestId;
    @NotNull(message = "ime can't be null")
    @NotEmpty(message = "ime cant be empty")
    private String ime;
    @NotNull(message = "tekst can't be null")
    @NotEmpty(message = "tekst cant be empty")
    private String tekst;
    private Date datumKreiranja;

    public Komentar() {
    }

    public Komentar(Integer id, Integer vestId, String ime, String tekst, Date datumKreiranja) {
        this();
        this.id = id;
        this.vestId = vestId;
        this.ime = ime;
        this.tekst = tekst;
        this.datumKreiranja = datumKreiranja;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVestId() {
        return vestId;
    }

    public void setVestId(Integer vestId) {
        this.vestId = vestId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }
}
