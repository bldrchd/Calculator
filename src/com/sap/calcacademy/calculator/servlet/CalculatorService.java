package com.sap.calcacademy.calculator.servlet;

import java.util.HashMap;

import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;
import com.sap.calcacademy.calculator.*;

@Path("/result")

public class CalculatorService {

    private static HashMap<Integer, Number> results = new HashMap<>();
    
    @GET
    @Produces("application/json")
    public Response calculate() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        Double value = 0d;
        jsonObject.put("Here is the value: ", value); 
        String result = "@Produces(\"application/json\") Output: INITIAL OUTPUT 0 : \n\n" + jsonObject;
        return Response.status(200).entity(result).build();
    }
    
    @Path("{i}")
    @GET
    @Produces("application/json")
    public Response calculateWithInput(@PathParam("i") Integer i) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        Number res = results.get(i);
        jsonObject.put("Here is the value: ", res); 
        String result = "@Produces(\"application/json\") Result: \n\n" + jsonObject.toString();
        return Response.status(200).entity(result).build();
    }
    
    @Path("/expression")
    @POST
    @Consumes(MediaType.WILDCARD)
    public Response submit(String s) {
        Number resultID = getResult(s);
        return Response.status(200).entity("resultID: " + resultID).build();
    }
    
    private Number getResult(String equation){
        Calculator calculator = new Calculator(); 
        Number res = calculator.calculate(equation);
        results.put(results.size()+1, res);
        return results.size();   
    }
    
    @Path("/query")
    @GET
    @Produces("application/json")
    public Response result(@QueryParam("expression") String s) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        Calculator calculator = new Calculator();
        Number res = calculator.calculate(s);
        jsonObject.put("result", res);
        String result = jsonObject.toString();
        return Response.status(200).entity(result).build();
    }
}