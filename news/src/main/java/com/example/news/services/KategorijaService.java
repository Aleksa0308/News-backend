package com.example.news.services;

import com.example.news.entities.Kategorija;
import com.example.news.repositories.kategorije.KategorijeRepository;

import javax.inject.Inject;
import java.util.List;

public class KategorijaService {
    @Inject
    KategorijeRepository kategorijeRepository;

    public Kategorija findKategorija(Integer id){
        return this.kategorijeRepository.findKategorija(id);
    }

    public List<Kategorija> findAllKategorije(){return this.kategorijeRepository.allKategorije();}
    public void deleteKategoriju(Integer id){
        this.kategorijeRepository.deleteKategoriju(id);
    }
    public Kategorija dodajKategoriju(Kategorija kategorija){
        return this.kategorijeRepository.dodajKategoriju(kategorija);
    }
    public Kategorija editKategorija(Kategorija kategorija) {
        return this.kategorijeRepository.editKategorija(kategorija);
    }
}
