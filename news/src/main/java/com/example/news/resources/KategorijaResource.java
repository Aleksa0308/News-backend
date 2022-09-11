package com.example.news.resources;

import com.example.news.entities.Kategorija;
import com.example.news.entities.User;
import com.example.news.entities.Vest;
import com.example.news.services.KategorijaService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/kategorije")
public class KategorijaResource {
    @Inject
    KategorijaService kategorijaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kategorija> findAllKategorije(){
        return this.kategorijaService.findAllKategorije();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija find(@PathParam("id") Integer id) {
        return this.kategorijaService.findKategorija(id);
    }


    @DELETE
    @Path("/{id}")
    public void deleteKategoriju(@PathParam("id") Integer id) {
        this.kategorijaService.deleteKategoriju(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija dodajKategoriju(Kategorija kategorija) {
        return this.kategorijaService.dodajKategoriju(kategorija);
    }

    @PUT//radi
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija editKategorija (Kategorija kategorija){
        return this.kategorijaService.editKategorija(kategorija);
    }

}
