package com.example.news.repositories.komentari;

import com.example.news.entities.Komentar;
import com.example.news.entities.User;

import java.util.List;

public interface KomentariRepository {
    public Komentar addComment(Komentar komentar);
    public List<Komentar> findKomentarZaVest(Integer id);
}
