package com.example.news.resources;

import com.example.news.entities.Komentar;
import com.example.news.services.KomentariService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/komentari")
public class KomentariResource {

    @Inject
    private KomentariService komentariService;

    @POST//radi
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Komentar addComment (Komentar komentar){
        return this.komentariService.addKomentar(komentar);
    }

}
