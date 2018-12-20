package com.sap.calcacademy.calculator.servlet;

import java.util.HashMap;

import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;

import com.google.gson.Gson;
import com.sap.calcacademy.calculator.*;
import com.sap.calcacademy.calculator.exceptions.CalculationException;

@Path("/result")

public class CalculatorService<syncronized> {
    
    private int count = 0;
    private static HashMap<Integer, Number> results = new HashMap<>(); //to be unique - sessionID? 
    
    @GET
    @Produces("application/json")
    public Response calculate() throws JSONException {
        return Response.status(200).entity(null).build();
    }
    
    @Path("{resultID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResult(@PathParam("resultID") Integer resultID) throws JSONException {
        Number res = results.get(resultID);
        Result result = new Result();
        result.setResult(res);
        Gson gson = new Gson();
        String json = gson.toJson(result);
        return Response.status(200).entity(json).build();
    }
    
    @Path("/expression")
    @POST
    @Consumes(MediaType.WILDCARD)
    @Produces("application/json")
    public Response submit(String s) throws IllegalArgumentException, CalculationException {
        Expression em = new Expression(s, getResultID(s));
        Gson gson = new Gson();
        String json = gson.toJson(em);
        return Response.status(200).entity(json).build();
    }
    
    private synchronized Number getResultID(String equation) throws IllegalArgumentException, CalculationException{
        Calculator calculator = new Calculator(); 
        Number res = calculator.calculate(equation);
        count++;
        results.put(count, res);
        return results.size();   
    }    
}