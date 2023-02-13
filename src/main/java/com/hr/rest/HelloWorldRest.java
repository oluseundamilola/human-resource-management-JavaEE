/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hr.rest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author OLUSEUN DAMILOLA
 */
@Path("hello")
public class HelloWorldRest {
    
    /**
     *
     * @param name
     * @return
     */
    @Path("{name}")
    @GET
    public JsonObject greet(@PathParam("name") String name){
        return Json.createObjectBuilder().add("greetins", "Hello " + name).build();
    }
    
}
