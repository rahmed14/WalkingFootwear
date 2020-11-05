package cogent.demo.security.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.demo.models.Employee;


@CrossOrigin
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public List<Employee> findByEmpFirstName(String name);
	
	
//
//	@Query("SELECT e FROM Employee e WHERE e.salary > :amount")
//	public List<Employee> listEmployeeBySalary(@Param("amount") double amount);
	
	
	@Query("SELECT e FROM Employee e WHERE e.empFirstName LIKE %:word%")
	public List<Employee> listEmployeeByName(@Param("word") String word);
	
	//empFirstName
	
}
