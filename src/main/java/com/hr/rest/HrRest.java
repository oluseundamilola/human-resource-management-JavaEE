/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hr.rest;

import com.hr.config.SecurityFilter;
import com.hr.entity.Hr;
import com.hr.service.MySession;
import com.hr.service.PersistenceService;
import com.hr.service.QueryService;
import com.hr.service.SecurityUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author OLUSEUN DAMILOLA
 */
@Path("hr")
public class HrRest {

    @Inject
    private PersistenceService persistenceService;

    @Inject
    private SecurityUtil securityUtil;

    @Context
    private UriInfo uriInfo;

    @Inject
    private MySession mySession;

    @Inject
    private QueryService queryService;

    @Path("new")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createHr(Hr hr) {
        persistenceService.saveHr(hr);
        return Response.ok(hr).build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@NotNull(message = "Username must be set") @FormParam("username") String username,
                          @NotNull(message = "Password must be set") @FormParam("password") String password) {

        Hr current_hr = queryService.findHrByUserName(username);

        if (!securityUtil.authenticateUser(username, password)) {
            throw new SecurityException("Email or Password invalid");
        }
        String token = getToken(username);
        mySession.setUsername(username);
        mySession.setHr_permission(current_hr.getPermission());

        return Response.ok().header(AUTHORIZATION, SecurityFilter.BEARER + " " + token).build();
    }

    private String getToken(String email) {
        Key key = securityUtil.generateKey(email);

        String token = Jwts.builder().setSubject(email).setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date()).setExpiration(securityUtil.toDate(LocalDateTime.now().plusMinutes(15)))
                .signWith(SignatureAlgorithm.HS512, key).setAudience(uriInfo.getBaseUri().toString())
                .compact();

//                logger.log(Level.INFO, "Generated token is {0}", token);
        return token;
    }

}
