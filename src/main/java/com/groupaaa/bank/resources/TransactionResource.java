/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupaaa.bank.resources;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.groupaaa.bank.models.Customer;
import com.groupaaa.bank.models.Transaction;
import com.groupaaa.bank.services.TransactionService;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
public class TransactionResource {
    private static final TransactionService transactionService = TransactionService.getService();
    private final int customerId, accountId;
    
    public TransactionResource(int customerId, int accountId) {
        this.customerId = customerId;
        this.accountId = accountId;
    }
    //get request to return all transactions
    @GET    
    @Produces(MediaType.APPLICATION_JSON)    
    public Response getTransactionsJson() {        
       return Response.ok(new Gson().toJson(transactionService.getTransactions())).build();
    }
    
//    @GET    
//    @Produces(MediaType.APPLICATION_JSON)    
//    public Transaction getTransactionsByID() {        
//       return transactionService.getTransactionsByID(transactionID);
//    }
    
    
    //get request by id
    @GET
    @Path("/{transactionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionJson(@PathParam("transactionId") int transactionId) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        
        if(transaction == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(new Gson().toJson(transactionId)).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTransactionJson(String content) {
        Transaction transaction = new Gson().fromJson(content, Transaction.class);
        
        transaction.setTransactionId(Transaction.getNextTransactionId());
        
        transactionService.addTransaction(transaction);
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    
    @PUT
    @Path("/{transactionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTransactionJson(@PathParam("transactionId") int transactionId, String content) {
        // parse content using JsonObject to access values
        JsonObject json = new JsonParser().parse(content).getAsJsonObject();
        
        // get existing customer object from "database"
        Transaction transaction = transactionService.getTransactionById(transactionId);
        
        // check what json values were submitted and update transaction with each
        if (json.has("type")) {
            transaction.setType(json.get("type").getAsString());
        }
        if (json.has("description")) {
            transaction.setDescription(json.get("description").getAsString());
        }
        if (json.has("newBalance")) {
            transaction.setNewBalance(json.get("newBalance").getAsDouble());
        }
        // save
        Transaction updatedTransaction = transactionService.updateTransaction(transactionId, transaction);

        return Response.ok(new Gson().toJson(updatedTransaction)).build();
    }
    
    @DELETE
    @Path("/{transactionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTransactionJson(@PathParam("transactionId") int transactionId) {
        Transaction transaction = transactionService.deleteTransaction(transactionId);
        
        if (transaction == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(new Gson().toJson(transaction)).build();
    }
    
}
