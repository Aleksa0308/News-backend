package com.example.news.services;

import com.example.news.entities.Vest;
import com.example.news.repositories.vesti.VestiRepository;

import javax.inject.Inject;
import java.util.List;

public class VestiService {
    public VestiService() {
        System.out.println(this);
    }
        @Inject
        private VestiRepository vestiRepository;

        public Vest addVest(Vest vest) {
        return this.vestiRepository.addVest(vest);
    }

        public List<Vest> allVesti() {
            return this.vestiRepository.allVesti();
        }

    public List<Vest> topVesti() {
        return this.vestiRepository.topVesti();
    }
    public List<Vest> popularVesti() {
        return this.vestiRepository.popularVesti();
    }


    public Vest findVest(Integer id) {
        return this.vestiRepository.findVest(id);
    }
    public void obrisiVest(Integer id){
             this.vestiRepository.obrisiVest(id);
    }
    public Vest editVest(Vest vest){
            return  this.vestiRepository.editVest(vest);
    }
    public List<Vest> searchVest(String search){
            return this.vestiRepository.searchVest(search);
    }
    public List<Vest> findVestKategorija(Integer id){
        return this.vestiRepository.findVestKategorija(id);
    }
    public void povecajPosetu(Integer id){
            vestiRepository.povecajPosetu(id);
    }
}
