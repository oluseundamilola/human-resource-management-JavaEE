/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hr.config;

import com.hr.service.MySession;
import com.hr.service.SecurityUtil;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.security.Key;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author OLUSEUN DAMILOLA
 */

@Provider //Register filter with JAX-RS runtime
@SecureAuth //Bind SecureAuth annotation with this filter
@Priority(Priorities.AUTHENTICATION) //Prioritise this filter above others as a security filter
public class SecurityFilter implements ContainerRequestFilter{
    public static final String BEARER = "Bearer";
    
    @Inject
    private SecurityUtil securityUtil;
    
    @Inject
    private MySession mySession;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
        String authString = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        if(authString == null || authString.isEmpty() || !authString.startsWith(BEARER)){
            
            JsonObject jsonObject = Json.createObjectBuilder().add("error-message", "No valid string token found").build();

            throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).entity(jsonObject).build());
            
        }
        
        String token = authString.substring(BEARER.length()).trim();
        
        try{
            Key key = securityUtil.generateKey(mySession.getUsername());
            Jwts.parser().setSigningKey(key).parse(token);
        }catch(Exception e){
            requestContext.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() );
        }
    }
    
    
}
