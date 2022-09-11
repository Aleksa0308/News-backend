package com.example.news.repositories.vesti;

import com.example.news.entities.Vest;

import java.util.List;

public interface VestiRepository {

    Vest addVest(Vest vest);
    List<Vest> allVesti();

    List<Vest> topVesti();

    List<Vest> popularVesti();

    Vest findVest(Integer id);

    void obrisiVest(Integer id);

    Vest editVest(Vest vest);

    List<Vest> searchVest(String search);

    List<Vest> findVestKategorija(Integer id);
    void povecajPosetu(Integer id);
}
