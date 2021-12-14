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
    
    @GET
    @Path("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountById(@PathParam("accountId") int accountId) {
        Account account = accountService.getAccount(customerId, accountId);
        
        if (account == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
       
        return Response.ok(new Gson().toJson(account)).build();
    }
    
//    //Get account balance from account number 
//    @GET
//    @Path("/{accountId}/balance")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAccountBalance(@PathParam("accountId") int accountId){
//        return (Response) accountService.getAccountBalance(accountId);
//    }
//    //Updating an account by its account number
//    @PUT
//    @Path("/{accountNo}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Account updateAccount(@PathParam("accountNo") Account account,int accountId){
//        return accountService.updateAccountByID(account, accountId);
//    }
//    //Deleting account by account number
//    @DELETE
//    @Path("/{accountNo}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Account deleteAccount(@PathParam("accountNo") Account account,int accountId){
//        return accountService.deleteAccountByID(account, accountId);
//    }
    
    @Path("/{accountId}/transactions")
    public TransactionResource getTransactionsResource(@PathParam("accountId") int accountId) {
        return new TransactionResource(customerId, accountId);
    }
}
