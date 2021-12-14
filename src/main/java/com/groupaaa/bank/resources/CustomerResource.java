package com.groupaaa.bank.resources;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.groupaaa.bank.models.Customer;
import com.groupaaa.bank.services.CustomerService;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("customers")
@RequestScoped
public class CustomerResource {
    private final CustomerService customerService = CustomerService.getService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomersJson() {
        return Response.ok(new Gson().toJson(customerService.getCustomers())).build();
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomerJson(String content) {
        Customer customer = new Gson().fromJson(content, Customer.class);
        customer.setId(Customer.getNextAccountId());
        
        customerService.addCustomer(customer);
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @PUT
    @Path("/{customerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomerJson(@PathParam("customerId") int customerId, String content) {
        JsonObject json = new JsonParser().parse(content).getAsJsonObject();
        Customer customer = customerService.getCustomer(customerId);
        
        if (json.has("name")) {
            customer.setName(json.get("name").getAsString());
        }
        if (json.has("address")) {
            customer.setAddress(json.get("address").getAsString());
        }
        if (json.has("email")) {
            customer.setEmail(json.get("email").getAsString());
        }
        if (json.has("pin")) {
            customer.setPin(json.get("pin").getAsString());
        }
        
        customerService.updateCustomer(customerId, customer);

        return Response.ok(new Gson().toJson(customer)).build();
    }
    
    @DELETE
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomerJson(@PathParam("customerId") int customerId) {
        Customer customer = customerService.deleteCustomer(customerId);
        
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(new Gson().toJson(customer)).build();
    }
    
    @Path("/{customerId}/accounts")
    public AccountResource getAccountsResource(@PathParam("customerId") int customerId) {
        return new AccountResource(customerId);
    }
}
