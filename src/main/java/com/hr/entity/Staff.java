package com.hr.entity;

import java.sql.Date;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import javax.persistence.Convert;
import javax.persistence.PrePersist;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

/**
 *
 * @author OLUSEUN DAMILOLA
 */
@Entity
@NamedQuery(name = Staff.FIND_STAFF_BY_ID, query = "select s from Staff s where s.id = :id")
@NamedQuery(name = Staff.FIND_STAFF_BY_EMAIL, query = "select s from Staff s where s.email = :email")
@NamedQuery(name = Staff.FIND_ALL_STAFFS, query = "select s from Staff s order by s.salary")
@NamedQuery(name = Staff.FIND_STAFF_BY_NAME, query = "select s from Staff s where s.fullName like :name")
@NamedQuery(name = Staff.ALL_STAFFS_BY_DEPARTMENT, query = "select s from Staff s where s.department_Allocated.departmentName = :departmentName")
@NamedQuery(name = Staff.COUNT_STAFF_BY_EMAIL, query = "select count(s) from Staff s where s.email = :email")
@NamedQuery(name = Staff.COUNT_STAFF_BY_DEPARTMENT, query = "select count(s) from Staff s where s.department_Allocated.departmentName = :departmentName")
@NamedQuery(name = Staff.COUNT_ALL_STAFFS, query = "select count(s) from Staff s")
@NamedQuery(name = Staff.GET_STAFF_AGE, query = "select count(s) from Staff s")
@NamedQuery(name = Staff.FIND_STAFF_WITH_AGES, query = "select s from Staff s where s.age = :age")
@NamedQuery(name = Staff.FIND_STAFF_WITH_WORK_YEARS, query = "select s from Staff s where s.yearsWorked > :yearsWorked")
public class Staff extends AbstractEntity {

    public static final String FIND_STAFF_BY_ID = "Staff.findStaffById";
    public static final String FIND_STAFF_BY_EMAIL = "Staff.findStaffByEmail";
    public static final String FIND_ALL_STAFFS = "Staff.findStaffAllStaffs";
    public static final String FIND_STAFF_BY_NAME = "Staff.findStaffByName";
    public static final String ALL_STAFFS_BY_DEPARTMENT = "Staff.findStaffByDeparments";
    public static final String COUNT_STAFF_BY_EMAIL = "Staff.countStaffByEmail";
    public static final String COUNT_STAFF_BY_DEPARTMENT = "Staff.countStaffByDepartment";
    public static final String COUNT_ALL_STAFFS = "Staff.countAllStaffs";
    public static final String GET_STAFF_AGE = "Staff.getStaffAge";
    public static final String FIND_STAFF_WITH_AGES = "Staff.findWithAges";
    public static final String FIND_STAFF_WITH_WORK_YEARS = "Staff.WithWorkYears";
    

    //Create a Staff --done
    //Update a Staff  --done
    //Delete a Staff
    //Find a staff my Id --done
    //Find all Staffs --doe
    //Find all Staffs by department --done
    //Search for a Staff
    
    @NotEmpty(message = "Please enter name for the new staff")
    @Size(min = 2, max = 50, message = "Name entered is too long or short")
    private String fullName;

    @Column(length = 100)
    @NotEmpty(message = "Email must be set")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @Column(length = 20)
    @NotEmpty(message = "Phone number most be set")
    private Long phone;

    @Column(length = 100)
    @NotNull
    private String homeAddress;

    @NotNull
    private Long salary;

//    @Convert(converter = LocalDateAttributeConverter.class)
    @NotNull
    @Past
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private Date dateOfBirth;
    
    private int age;
    private int yearsWorked;

    @NotNull
    @JsonbDateFormat("yyyy-MM-dd")
    private Date dateEmployed;

    @ManyToOne
    @JoinColumn(name = "Department_Id")
//    @NotNull
    private Department department_Allocated; //MANY STAFFS CAN BELONG TO ONE DEPARTMENT

    
    
    public int getYearsWorked() {
        return yearsWorked;
    }

    public void setYearsWorked(int yearsWorked) {
        this.yearsWorked = yearsWorked;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateEmployed() {
        return dateEmployed;
    }

    public void setDateEmployed(Date dateEmployed) {
        this.dateEmployed = dateEmployed;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Department getDepartment_Allocated() {
        return department_Allocated;
    }

    public void setDepartment_Allocated(Department department_Allocated) {
        this.department_Allocated = department_Allocated;
    }

}
