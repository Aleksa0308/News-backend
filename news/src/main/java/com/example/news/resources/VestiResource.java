package com.example.news.resources;

import com.example.news.entities.Komentar;
import com.example.news.entities.Vest;
import com.example.news.services.KomentariService;
import com.example.news.services.VestiService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/vesti")
public class VestiResource {
    @Inject
    private VestiService vestiService;

    @Inject
    private KomentariService komentariService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Vest create(@Valid Vest vest) {
        return this.vestiService.addVest(vest);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> all()
    {
        return this.vestiService.allVesti();
    }

    @GET
    @Path("/top")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> top()
    {
        return this.vestiService.topVesti();
    }

    @GET
    @Path("/popular")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> popular()
    {
        return this.vestiService.popularVesti();
    }



    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vest find(@PathParam("id") Integer id) {
        return this.vestiService.findVest(id);
    }

    @GET
    @Path("/{id}/komentari")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Komentar> articleComments (@PathParam("id") Integer id) {
        return this.komentariService.findKomentarZaVest(id);
    }
    @DELETE
    @Path("/{id}")
    public void deleteVest(@PathParam("id") Integer id) {
        this.vestiService.obrisiVest(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Vest izmeniVest(@Valid Vest vest) {
        return this.vestiService.editVest(vest);
    }

    @GET
    @Path("/search/{search}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> find(@PathParam("search") String search) {
        return this.vestiService.searchVest(search);
    }
    @GET
    @Path("/kategorije/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> findVestKategorija(@PathParam("id") Integer id) {
        return this.vestiService.findVestKategorija(id);
    }
    @POST
    @Path("/poseta/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void povecajPosetu(@PathParam("id") Integer id) {
        this.vestiService.povecajPosetu(id);
    }
}
