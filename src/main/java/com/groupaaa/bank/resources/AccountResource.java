package com.groupaaa.bank.resources;

import com.google.gson.Gson;
import com.groupaaa.bank.models.Account;
import com.groupaaa.bank.services.AccountService;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    //Get account balance from account number 
    @GET
    @Path("/{accountNo}/balance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountBalance(@PathParam("accountNo") int accountId){
        return (Response) accountService.getAccountBalance(accountId);
    }
    //Updating an account by its account number
    @PUT
    @Path("updateAccount/{accountNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account updateAccount(@PathParam("accountNo") Account account,int accountId){
        return accountService.updateAccountByID(account, accountId);
    }
    
    @DELETE
    @Path("deleteAccount/{accountNo}/balance")
    @Produces(MediaType.APPLICATION_JSON)
    public Account deleteAccount(@PathParam("accountNo") Account account,int accountId){
        return accountService.deleteAccountByID(account, accountId);
    }
   
    
}
