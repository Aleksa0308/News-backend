package com.example.news.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Kategorija {
    private Integer id;
    @NotNull(message = "tip can't be null")
    @NotEmpty(message = "tip cant be empty")
    private String tip;
    @NotNull(message = "opis can't be null")
    @NotEmpty(message = "opis cant be empty")
    private String opis;

    public Kategorija() {
    }

    public Kategorija(Integer id, String tip, String opis) {
        this();
        this.id = id;
        this.tip = tip;
        this.opis = opis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
