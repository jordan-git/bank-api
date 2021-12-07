package com.groupaaa.bankproject.resources;

import com.google.gson.Gson;
import com.groupaaa.bankproject.models.Customer;
import com.groupaaa.bankproject.services.CustomerService;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("customers")
@RequestScoped
public class CustomerResource {
    private final CustomerService customerService = CustomerService.getService();

    public CustomerResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomersJson() {
        return new Gson().toJson(customerService.getCustomers());
    }
    
    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerJson(@PathParam("customerId") int customerId) {
        Customer customer = customerService.getCustomer(customerId);
        
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(new Gson().toJson(customer)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {

    }
}
