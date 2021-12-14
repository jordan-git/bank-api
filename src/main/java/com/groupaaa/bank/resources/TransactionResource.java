/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupaaa.bank.resources;

import com.google.gson.Gson;
import com.groupaaa.bank.models.Transaction;
import com.groupaaa.bank.services.TransactionService;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
public class TransactionResource {
    
    private int customerId;
    private int accountId;
    
    private static final TransactionService transactionService = TransactionService.getService();
    
    public TransactionResource(int customerId, int accountId) {
        this.customerId = customerId;
        this.accountId = accountId;
    }
    
    //get request to return all transactions
    @GET    
    @Produces(MediaType.APPLICATION_JSON)    
    public List<Transaction> getAllTransactions() {        
       return transactionService.getAllTransactions(customerId, accountId);
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
        Transaction transaction = transactionService.getTransaction(transactionId);
        
        if(transaction == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(new Gson().toJson(transactionId)).build();
    }
    
    
}
