/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hr.service;

import com.hr.entity.Department;
import com.hr.entity.Hr;
import com.hr.entity.Staff;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author OLUSEUN DAMILOLA
 */
@DataSourceDefinition(
        name = "java:app/hr/MyDS",
        className = "org.sqlite.SQLiteDataSource",
        url = "jdbc:sqlite:C:\\hrsqlite\\sqlite-tools-win32-x86-3400100\\hr.db"
)
@Stateless
public class PersistenceService {
//sqlite3

    @Inject
    private MySession mySession;

    @Inject
    private QueryService queryService;

    @PersistenceContext
    EntityManager entityManager;
    
    @Inject
    private SecurityUtil securityUtil;

    /**
     *
     * @param department
     * @return
     */
    public Department saveDepartment(Department department) {
        if (department.getId() == null) {                    //If it's a new department persist it, Else merge it
            entityManager.persist(department);
        } else {
            entityManager.merge(department);
        }

        return department;
    }
    
    public Hr saveHr(Hr hr){
        
        if("admin".equals(hr.getRole())){
            hr.setPermission(1);
        }else{
            hr.setPermission(0);
        }
        
        
//        List list = queryService.countHrByUsername(hr.getUserName());
//        Integer count = (Integer) list.get(0);
        
        Map<String, String> credentialMap = securityUtil.hashPassword(hr.getPassword());
        if(hr.getId() == null){ //count == 0
            hr.setPassword(credentialMap.get("hashedPassword"));
            hr.setSalt(credentialMap.get("salt"));
            entityManager.persist(hr);
        }
        return hr;
    }

   
    public Staff saveStaff(Staff staff) {
        String departmentName = mySession.getDepartmentName();
        Department department = queryService.findDepartmentByName(departmentName);
        
        Date dateOfBirth = staff.getDateOfBirth();
        LocalDate toLocalDate = dateOfBirth.toLocalDate();
        LocalDate currentDate = LocalDate.now();
        
        Period period = Period.between(toLocalDate, currentDate);
        staff.setAge( period.getYears() );
        
        Date dateEmployed = staff.getDateEmployed();
        LocalDate dayEmployedToLocalDate = dateEmployed.toLocalDate();
        Period periodWorked = Period.between(dayEmployedToLocalDate, currentDate);
        staff.setYearsWorked( periodWorked.getYears() );
        
        
        
        List list = queryService.countStaffByEmail(staff.getEmail());
        Integer count = (Integer) list.get(0);
        

        if (staff.getId() == null && count == 0 && department != null) {                          //If it's a new department persist it, Else merge it
            staff.setDepartment_Allocated(department);
            entityManager.persist(staff);
        } else {
            
        }

        return staff;
    }
    
    public Staff updateStaff(Staff staff){
        List list = queryService.countStaff(staff.getEmail(), staff.getId());
        Integer count = (Integer) list.get(0);
        
        if( staff.getId() != null && count == 1 ){
            entityManager.merge(staff);
            return staff;
        }
        return null;
    }
    
    public Staff updateStaffEmail(Long id, String email){
        List list = queryService.countStaffByEmail(email);
        Integer count = (Integer) list.get(0);
        
        if(count == 0){
            Staff staff = queryService.findStaffById(id);
            if(staff != null){
                staff.setEmail(email);
                entityManager.merge(staff);
                return staff;
            }
        }
        return null;
    }
    
    public void updateDateAndTime(Staff staff){
        
        Date dateOfBirth = staff.getDateOfBirth();
        LocalDate toLocalDate = dateOfBirth.toLocalDate();
        LocalDate currentDate = LocalDate.now();
        
        Period period = Period.between(toLocalDate, currentDate);
        staff.setAge( period.getYears() );
        
        Date dateEmployed = staff.getDateEmployed();
        LocalDate dayEmployedToLocalDate = dateEmployed.toLocalDate();
        Period periodWorked = Period.between(dayEmployedToLocalDate, currentDate);
        staff.setYearsWorked( periodWorked.getYears() );
        
    }

}
