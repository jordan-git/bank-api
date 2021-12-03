package com.groupaaa.bankproject.resources;

import com.google.gson.Gson;
import com.groupaaa.bankproject.services.CustomerService;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

@Path("customers")
@RequestScoped
public class CustomerResource {
    private final CustomerService customerService = CustomerService.getService();

    public CustomerResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return new Gson().toJson(customerService.getCustomers());
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {

    }
}
