/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hr.service;

import com.hr.entity.Department;
import com.hr.entity.Hr;
import com.hr.entity.Staff;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author OLUSEUN DAMILOLA
 */
@Stateless
public class QueryService {
    
    @Inject
    MySession mySession;

    @PersistenceContext
    EntityManager entityManager;
    
    @Inject
    SecurityUtil securityUtil;
    


//    Department --------------------------------------
    public Department findDepartmentByName(String departmentName) {
        try {
            return entityManager.createNamedQuery(Department.FIND_DEPARTMENT_BY_NAME, Department.class)
                    .setParameter("departmentName", departmentName).getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            return null;
        }

    }

    public List<Department> getAllDepartments() {
        return entityManager.createNamedQuery(Department.GET_ALL_DEPARTMENTS, Department.class).getResultList();
    }

    public Department findDepartmentById(Long id) {
        try {
            return entityManager.createNamedQuery(Department.FIND_DEPARTMENT_BY_ID, Department.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            return null;
        }
    }

//    Staff ---------------------------------------------
    public Staff findStaffById(Long Id) {
        try {
            return entityManager.createNamedQuery(Staff.FIND_STAFF_BY_ID, Staff.class)
                    .setParameter("id", Id).getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            return null;
        }
    }

    public Staff findStaffByEmail(String email) {
        try {
            return entityManager.createNamedQuery(Staff.FIND_STAFF_BY_EMAIL, Staff.class)
                    .setParameter("email", email).getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            return null;
        }
    }

    public List<Staff> getAllStaffs() {
        return entityManager.createNamedQuery(Staff.FIND_ALL_STAFFS, Staff.class).getResultList();
    }
    
    public List<Staff> getAllStaffsWithAge(Long age){
        return entityManager.createNamedQuery(Staff.FIND_STAFF_WITH_AGES, Staff.class)
                .setParameter("age", age).getResultList();
    }
    
    public List<Staff> getAllStaffsByYearsWorks(Long yearsWorked){
        return entityManager.createNamedQuery(Staff.FIND_STAFF_WITH_WORK_YEARS, Staff.class)
                .setParameter("yearsWorked", yearsWorked).getResultList();
    }

    public List<Staff> findStaffByName(String name) { //Collection maybe
        return entityManager.createNamedQuery(Staff.FIND_STAFF_BY_NAME, Staff.class)
                .setParameter("name", "%" + name + "%").getResultList();
    }

    public List<Staff> getStaffByDepartment() {
        return entityManager.createNamedQuery(Staff.ALL_STAFFS_BY_DEPARTMENT, Staff.class)
                .setParameter("departmentName", mySession.getDepartmentName()).getResultList();
    }

    public List countStaffByEmail(String email) {
        List resultList = entityManager.createNativeQuery("select count (id) from Staff where exists (select id from Staff where email = ?)")
                .setParameter(1, email).getResultList();

        return resultList;
    }

    public List countStaffByEmailNamedQuery(String email) {
        return entityManager.createNamedQuery(Staff.COUNT_STAFF_BY_EMAIL, Staff.class)
                .setParameter("email", email).getResultList();
    }
    
    public List countStaff(String email, Long id){
        return entityManager.createNativeQuery("select count (id) from Staff where exists (select id from Staff where email = ?1 and id = ?2)")
                .setParameter(1, email).setParameter(2, id).getResultList();
    }
    
    public List countStaffByDepartment(){
        return entityManager.createNamedQuery(Staff.COUNT_STAFF_BY_DEPARTMENT, Staff.class)
                .setParameter("departmentName", mySession.getDepartmentName()).getResultList();
    }
    
    public List countAllStaffs(){
        return entityManager.createNamedQuery(Staff.COUNT_ALL_STAFFS, Staff.class).getResultList();
    }
    
//    HR---------------------------------------------------------------
    
    public Hr findHrByUserName(String username){
        return entityManager.createNamedQuery(Hr.FIND_HR_BY_USERNAME, Hr.class)
                .setParameter("username", username).getSingleResult();
    }
    
    public List countHrByUsername(String username){
        return entityManager.createNamedQuery(Hr.COUNT_HR_BY_USERNAME, Hr.class)
                .setParameter("username", username).getResultList();
    }
    
    public boolean authenticateUser(String username, String plainTextPassword){
        Hr hr = findHrByUserName(username);
        if(hr != null){
            return securityUtil.passwordsMatch(hr.getPassword(), hr.getSalt(), plainTextPassword );
        }
        return false;
    }
    
    

}
