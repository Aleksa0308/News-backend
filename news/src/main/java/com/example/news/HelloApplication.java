package com.example.news;

import com.example.news.repositories.kategorije.KategorijeRepository;
import com.example.news.repositories.kategorije.MySqlKategorijeRepository;
import com.example.news.repositories.komentari.KomentariRepository;
import com.example.news.repositories.komentari.MySqlKomentariRepository;
import com.example.news.repositories.user.MySqlUserRepository;
import com.example.news.repositories.user.UserRepository;
import com.example.news.repositories.vesti.MySqlVestiRepository;
import com.example.news.repositories.vesti.VestiRepository;
import com.example.news.services.KategorijaService;
import com.example.news.services.KomentariService;
import com.example.news.services.UserService;
import com.example.news.services.VestiService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;


import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication() {
        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {


                this.bind(MySqlUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MySqlVestiRepository.class).to(VestiRepository.class).in(Singleton.class);
                this.bind(MySqlKomentariRepository.class).to(KomentariRepository.class).in(Singleton.class);
                this.bind(MySqlKategorijeRepository.class).to(KategorijeRepository.class).in(Singleton.class);



                this.bindAsContract(VestiService.class);
                this.bindAsContract(UserService.class);
                this.bindAsContract(KomentariService.class);
                this.bindAsContract(KategorijaService.class);
            }
        };
        register(binder);


        // Ucitavamo resurse
        packages("com.example.news");
    }
}
