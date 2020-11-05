package cogent.demo.models;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@EnableAutoConfiguration
@Entity
@Table(name="Employees")
public class Employee {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EMP_ID")
	private int empId;
	
	@Column(name = "EMP_BIRTH_DATE")
	private Date empBirthday;
	
	@Column(name = "EMP_FIRST_NAME")
	private String empFirstName;
	
	@Column(name = "EMP_LAST_NAME")
	private String empLastName;
	
	@Column(name="EMP_GENDER")
	private String gender;
	
	@Column(name = "EMP_HIRE_DATE")
	private Date empHireDate;
	


//    @JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="DEPT_ID", referencedColumnName="DEPT_ID",  updatable=false)
	private Department deparment;

    
//    @JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="role", referencedColumnName="id",  updatable=false)
	private Role role;
	
    
    public int getEmpId() {
		return empId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public Date getEmpBirthday() {
		return empBirthday;
	}

	public void setEmpBirthday(Date empBirthday) {
		this.empBirthday = empBirthday;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getEmpHireDate() {
		return empHireDate;
	}

	public void setEmpHireDate(Date empHireDate) {
		this.empHireDate = empHireDate;
	}

	public Department getDeparment() {
		return deparment;
	}

	public void setDeparment(Department deparment) {
		this.deparment = deparment;
	}

}
