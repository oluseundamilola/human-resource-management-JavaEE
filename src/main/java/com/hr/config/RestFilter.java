/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hr.config;

import com.hr.service.MySession;
import java.io.IOException;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author OLUSEUN DAMILOLA
 */

@Provider
@SecureRest
@Priority(Priorities.AUTHENTICATION)
public class RestFilter implements ContainerRequestFilter{
    
    @Inject
    private MySession mySession;

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        int permission = mySession.getHr_permission();
        
        if( permission < 1 || permission != 1){
            JsonObject jsonObject = Json.createObjectBuilder().add("error-message", "You do not have the permission to perform this task").build();
            throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).entity(jsonObject));
        }
        
    }
    
}
