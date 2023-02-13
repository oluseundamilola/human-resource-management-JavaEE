/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hr.service;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author OLUSEUN DAMILOLA
 */
@SessionScoped
public class MySession implements Serializable {

    private String username;
    private String departmentName;
    private int hr_permission;

    public int getHr_permission() {
        return hr_permission;
    }

    public void setHr_permission(int hr_permission) {
        this.hr_permission = hr_permission;
    }

    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public void setUsername(String username) {   
        this.username = username;
    }

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

}
