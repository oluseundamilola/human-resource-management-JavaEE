/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hr.rest;

import com.hr.config.SecureAuth;
import com.hr.config.SecureRest;
import com.hr.entity.Staff;
import com.hr.service.PersistenceService;
import com.hr.service.QueryService;
import java.util.List;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author OLUSEUN DAMILOLA
 */

@SecureAuth
@Path("staff")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StaffRest {
    
    @Inject
    private PersistenceService persistenceService;
    
    @Inject
    private QueryService queryService;
    
    /**
     *
     * @param staff
     * @return
     */
    
    @SecureRest
    @Path("create")
    @POST
    public Response createStaff(Staff staff){
        persistenceService.saveStaff(staff);
        return Response.ok(staff).build();
    }
    
    /**
     *
     * @param staff
     * @return
     */
    @SecureRest
    @Path("update")
    @PUT
    public Response updateStaff(Staff staff){
        persistenceService.updateStaff(staff);
        return Response.ok(staff).build();
    }
    
    /**
     *
     * @param id
     * @return
     */
    @Path("find/{id}")
    @GET
    public Staff getStaffById(@PathParam("id") Long id){
        Staff staff = queryService.findStaffById(id);
        persistenceService.updateDateAndTime(staff);
        return staff;
    }
    
    /**
     *
     * @param email
     * @return
     */
    @Path("staff/{email}")
    @GET
    public Staff getStaffByEmail(@PathParam("email") String email){
        return queryService.findStaffByEmail(email);
    }
    
    /**
     *
     * @param email
     * @return
     */
    @Path("count")
    @GET
    public List countStaffByEmail(@QueryParam("email") String email){
        List count = queryService.countStaffByEmail(email);
        return count;
    }
    
    /**
     *
     * @param email
     * @return
     */
    @Path("countNamed")
    @GET
    public List countStaffByEmailNamedQuery( @QueryParam("email") String email){
         List count = queryService.countStaffByEmailNamedQuery(email);
         return count;
    }
    
    @Path("list")
    @GET
    public Response listAllStaff(){
        return Response.ok( queryService.getAllStaffs() ).build();
    }
    
    
    @Path("age")
    @GET
    public Response staffAge(@QueryParam("age") Long age ){
        return Response.ok( queryService.getAllStaffsWithAge(age) ).build();
    }
    
    @Path("years")
    @GET
    public Response yearsWorked(@QueryParam("yearsWorked") Long yearsWorked ){
        return Response.ok( queryService.getAllStaffsByYearsWorks(yearsWorked) ).build();
    }
    
    @PUT
    @Path("update-email")
    public Response updateStaffEmail(@NotNull @QueryParam("id") Long id,@NotNull @QueryParam("email") String email){
        Staff staff = persistenceService.updateStaffEmail(id, email);
        
        return Response.ok(staff).build();
    }
    
    @Path("countStaffs")
    @GET
    public Response countAllStaffs(){
        return Response.ok( queryService.countAllStaffs() ).build();
    }
    
    
    
}
