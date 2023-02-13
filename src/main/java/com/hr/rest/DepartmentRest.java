/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hr.rest;

import com.hr.config.SecureAuth;
import com.hr.config.SecureRest;
import com.hr.entity.Department;
import com.hr.service.MySession;
import com.hr.service.PersistenceService;
import com.hr.service.QueryService;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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


@Path("department")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecureAuth
public class DepartmentRest {

    @Inject
    private PersistenceService persistenceService;

    @Inject
    private QueryService queryService;
    
    @Inject
    private MySession mySession;

    
    @SecureRest
    @Path("create")
    @POST
    public Response createDepartment(@NotNull @Valid Department department) {
        persistenceService.saveDepartment(department);
        return Response.ok(department).build();

    }

    
    @SecureRest
    @Path("update")
    @PUT
    public Response updateDepartment(@NotNull @Valid Department department) {
        persistenceService.saveDepartment(department);
        return Response.ok(department).build();
    }

    @Path("find/{name}")
    @GET
    public Department findDepartmentwithPathParam(@NotNull @PathParam("name") @DefaultValue("Front-End") String name) {
        return queryService.findDepartmentByName(name);
    }

    @Path("query")
    @GET
    public Department findDepartmentwithQueryParam(@NotNull @QueryParam("name") @DefaultValue("Front-End") String name) {
        return queryService.findDepartmentByName(name);
    }
    
    @Path("listStaffs")
    @GET
    public Response findAllStaffs(){
       return Response.ok( queryService.getStaffByDepartment() ).build();
    }
    
    @Path("select")
    @POST
    public Response selectADepartment(@QueryParam("name") String name ){
        Department department = queryService.findDepartmentByName(name);
        mySession.setDepartmentName(department.getDepartmentName());
        return Response.ok(department.getDepartmentName()).build();
    }
    
    @Path("staffTotal")
    @GET
    public Response countStaffByDepartment(){
        return Response.ok( queryService.countStaffByDepartment() ).build();
    }
    
    @Path("departments")
    @GET
    public Response getAllDepartment(){
        return Response.ok( queryService.getAllDepartments() ).build();
    }

}
