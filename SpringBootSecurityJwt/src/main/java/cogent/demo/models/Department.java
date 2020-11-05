package cogent.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.fasterxml.jackson.annotation.JsonBackReference;

@EnableAutoConfiguration
@Entity
@Table(name = "Departments")
public class Department{
	
	@Id
	@Column(name = "DEPT_ID")
	
	private String deptId;
	
	@Column(name = "DEPT_NAME") 
	private String deptName;
	
	
	@JsonBackReference
	@OneToMany(mappedBy="deparment", cascade = CascadeType.ALL)
	private List<Employee> employees;
	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

//	public List<Employee> getEmployees() {
//		return employees;
//	}
//
//	public void setEmployees(List<Employee> employees) {
//		this.employees = employees;
//	}


	

}