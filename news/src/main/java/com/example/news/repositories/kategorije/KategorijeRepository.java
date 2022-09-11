package com.example.news.repositories.kategorije;

import com.example.news.entities.Kategorija;
import com.example.news.entities.User;

import java.util.List;

public interface KategorijeRepository {

    public Kategorija findKategorija(Integer id);
    public List<Kategorija> allKategorije();
    void deleteKategoriju(Integer id);
    Kategorija dodajKategoriju(Kategorija kategorija);
    Kategorija editKategorija(Kategorija kategorija);
}
