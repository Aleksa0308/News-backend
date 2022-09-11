package com.example.news.services;

import com.example.news.entities.Komentar;
import com.example.news.repositories.komentari.KomentariRepository;

import javax.inject.Inject;
import java.util.List;

public class KomentariService {
    @Inject
    KomentariRepository komentariRepository;

    public Komentar addKomentar(Komentar komentar){
        return komentariRepository.addComment(komentar);
    }

    public List<Komentar> findKomentarZaVest(Integer id){
        return komentariRepository.findKomentarZaVest(id);
    }
}
