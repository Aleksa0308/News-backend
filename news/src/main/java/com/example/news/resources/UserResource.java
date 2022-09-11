package com.example.news.resources;

import com.example.news.entities.User;
import com.example.news.requests.LoginRequest;
import com.example.news.services.UserService;


import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;
    @GET//radi
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> allUsers(){
        return this.userService.allUsers();
    }

    @GET//radi
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUser(@PathParam("username") String username){
        return this.userService.findUser(username);
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest)
    {
        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }
    @DELETE//radi
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("id") Integer id){
        this.userService.deleteUser(id);
    }

    @PUT//radi
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User editUser (User user){
        return this.userService.editUser(user);
    }

    @POST//radi
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser (User user){
        return this.userService.addUser(user);
    }

}
