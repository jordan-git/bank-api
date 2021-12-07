/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupaaa.bank.resources;

import com.groupaaa.bank.models.Transaction;
import com.groupaaa.bank.services.TransactionService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public class TransactionResource {
    
    TransactionService transactionService = new TransactionService();
    
    @GET    
    @Produces(MediaType.APPLICATION_JSON)    
    public List<Transaction> getAllTransactions() {        
       return transactionService.getAllTransactions();
    }
    
    
}
