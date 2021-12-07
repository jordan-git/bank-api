package com.groupaaa.bank.resources;

import com.google.gson.Gson;
import com.groupaaa.bank.services.AccountService;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
public class AccountResource {
    private final AccountService accountService = AccountService.getService();
    private final int customerId;
    
    public AccountResource(int customerId) {
        this.customerId = customerId;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccountsJson() {
        return Response.ok(new Gson().toJson(accountService.getAccountsByCustomerId(customerId))).build();
    }
}
