/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hr.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 *
 * @author OLUSEUN DAMILOLA
 */
@Entity
@NamedQuery(name = Hr.FIND_HR_BY_USERNAME, query = "select h from Hr h where h.userName = :username")
@NamedQuery(name = Hr.COUNT_HR_BY_USERNAME, query = "select count(h) from Hr h where h.userName = :username")
public class Hr extends AbstractEntity {

    public static final String FIND_HR_BY_USERNAME = "Hr.findByUsername";
    public static final String COUNT_HR_BY_USERNAME = "Hr.countByUsername";

    private String userName;
    private String role;

    private int permission;

    private String password;
    private String salt;
    
    
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
