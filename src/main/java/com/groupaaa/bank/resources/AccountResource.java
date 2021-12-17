package com.groupaaa.bank.resources;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.groupaaa.bank.models.Account;
import com.groupaaa.bank.models.Transaction;
import com.groupaaa.bank.services.AccountService;
import com.groupaaa.bank.services.CustomerService;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
public class AccountResource {
    private final AccountService accountService = AccountService.getService();
    private final CustomerService customerService = CustomerService.getService();
    private final int customerId;
    
    public AccountResource(int customerId) {
        this.customerId = customerId;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountsJson() {
        return Response.ok(new Gson().toJson(accountService.getAccounts(customerId))).build();
    }
    
    @GET
    @Path("/{accountNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByNo(@PathParam("accountNo") int accountNo) {
        Account account = accountService.getAccount(customerId, accountNo);
        
        if (account == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
       
        return Response.ok(new Gson().toJson(account)).build();
    }
    
    @GET
    @Path("/{accountNo}/balance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountBalanceJson(@PathParam("accountNo") int accountNo) {
        Account account = accountService.getAccount(customerId, accountNo);
        
        if (account == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // Easier to type manually for one value
        String jsonString = "{\"balance\":" + Double.toString(account.getBalance()) + "}";
       
        return Response.ok(jsonString).build();
    }
    
    @PUT
    @Path("/{accountNo}/lodge")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response lodgeJson(@PathParam("accountNo") int accountNo, String content) {
        Account account = accountService.getAccount(customerId, accountNo);
        
        if (account == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // parse content using JsonObject to access values
        JsonObject json = new JsonParser().parse(content).getAsJsonObject();
        
        if (!json.has("amount")) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        Transaction transaction = new Transaction("DEBIT", "Lodged at branch", account.getBalance() + json.get("amount").getAsDouble());
        
        account.addTransaction(transaction);
       
        return Response.ok(new Gson().toJson(transaction)).build();
    }
    
    @PUT
    @Path("/{accountNo}/withdraw")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response withdrawJson(@PathParam("accountNo") int accountNo, String content) {
        Account account = accountService.getAccount(customerId, accountNo);
        
        if (account == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // parse content using JsonObject to access values
        JsonObject json = new JsonParser().parse(content).getAsJsonObject();
        
        
        
        if (!json.has("amount")) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        Double amount = json.get("amount").getAsDouble();
        
        if (account.getBalance() < amount) {
            String jsonString = "{\"error\":\"Insufficient funds\"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonString).build();
        }
        
        Transaction transaction = new Transaction("DEBIT", "Withdraw at branch", account.getBalance() - amount);
        
        account.addTransaction(transaction);
       
        return Response.ok(new Gson().toJson(transaction)).build();
    }
    
    
    @PUT
    @Path("/{accountNo}/{otherAccountNo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transferJson(@PathParam("accountNo") int accountNo, @PathParam("otherAccountNo") int otherAccountNo, String content) {
        Account account = accountService.getAccount(customerId, accountNo);
        Account otherAccount = accountService.getAccount(otherAccountNo);
        
        if (account == null || otherAccount == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // parse content using JsonObject to access values
        JsonObject json = new JsonParser().parse(content).getAsJsonObject();
        
        if (!json.has("amount")) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        Double amount = json.get("amount").getAsDouble();
        
        if (account.getBalance() < amount) {
            String jsonString = "{\"error\":\"Insufficient funds\"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonString).build();
        }
        
        Transaction withdrawal = new Transaction("DEBIT", "Transferred to account " + otherAccount.getAccountNo(), account.getBalance() - amount);
        Transaction lodgement = new Transaction("DEBIT", "Received from account " + account.getAccountNo(), otherAccount.getBalance() + amount);
        
        account.addTransaction(withdrawal);
        otherAccount.addTransaction(lodgement);
       
        return Response.ok(new Gson().toJson(withdrawal)).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccountJson(String content) {
        // uses gson to create new Account from submitted json
        Account account = new Gson().fromJson(content, Account.class);
        account.setAccountNo(Account.getNextAccountNo());
        
        customerService.getCustomer(customerId).addAccount(account);
        
        return Response.ok(new Gson().toJson(account)).build();
    }
    
    @PUT
    @Path("/{accountNo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomerJson(@PathParam("accountNo") int accountNo, String content) {
        // parse content using JsonObject to access values
        JsonObject json = new JsonParser().parse(content).getAsJsonObject();
        
        // get existing customer object from "database"
        Account account = accountService.getAccount(customerId, accountNo);
        
        // check what json values were submitted and update customer with each
        if (json.has("sortCode")) {
            account.setSortCode(json.get("sortCode").getAsString());
        }
        if (json.has("balance")) {
            account.setBalance(json.get("balance").getAsDouble());
        }
               
        // save
        Account updatedAccount = accountService.updateAccount(customerId, accountNo, account);

        return Response.ok(new Gson().toJson(updatedAccount)).build();
    }
    
    @DELETE
    @Path("/{accountNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomerJson(@PathParam("accountNo") int accountNo) {
        Account account = accountService.deleteAccount(customerId, accountNo);
        
        if (account == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(new Gson().toJson(account)).build();
    }
    
    @Path("/{accountId}/transactions")
        public TransactionResource getTransactionResource(@PathParam("accountId") int accountId) {
            return new TransactionResource(customerId, accountId);
        }
}
