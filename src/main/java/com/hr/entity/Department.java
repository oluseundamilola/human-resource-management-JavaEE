/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hr.entity;

import java.time.LocalDate;
import javax.json.bind.annotation.JsonbDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author OLUSEUN DAMILOLA
 */
@Entity
@Table(name = "DepartmentTable")
@NamedQuery(name = Department.FIND_DEPARTMENT_BY_NAME, query = "select d from Department d where d.departmentName = :departmentName")
@NamedQuery(name = Department.GET_ALL_DEPARTMENTS, query = "select d from Department d")
@NamedQuery(name = Department.FIND_DEPARTMENT_BY_ID, query = "select d from Department d where d.id = :id")
public class Department extends AbstractEntity {

    public static final String FIND_DEPARTMENT_BY_NAME = "Department.findDepartmentByName";
    public static final String GET_ALL_DEPARTMENTS = "Department.findAllDepartments";
    public static final String FIND_DEPARTMENT_BY_ID = "Department.findById";

    //create new department
    //update department
    //find department by name
    //search for department by name
    @Column(length = 100)
    @NotNull(message = "Department name must be set")
    @Size(min = 4, max = 40, message = "Name entered was too Long or Short")
    private String departmentName;

    @NotNull(message = "Description must be set")
    @Size(min = 10, max = 200, message = "Description entered was too Long or Short")
    private String description;
    
    
    
    
    
    
    
    
    
    
    
    

    /**
     *
     * @return
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     *
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
