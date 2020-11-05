package cogent.demo.security.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.demo.models.Employee;
import cogent.demo.models.Role;



@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repo;

	public List<Employee> all() {
		List<Employee> empList = repo.findAll();
		return empList;
	}
	
	public Employee find(int id) {
		Employee e = null;
		Optional<Employee> oe = repo.findById(id);
		if (oe.isPresent()) {
			e = oe.get();
		}
		return e;
	}
	
	public List<Employee> findbyname(String name) {
		List<Employee> e = null;
		List<Employee> oe= repo.findByEmpFirstName(name);
		if (oe!=null) {
			e = oe;
		}
		return e;
	}
	
	
	public List<Employee> findbyname2(String name) {
		List<Employee> employees= repo.listEmployeeByName(name);
		return employees;
		
	}
	
	
	
	public Employee insert(Employee emp){
	
		Employee order2=repo.save(emp);	
		return order2;
	}
	
	//for manager to use this method
	public Employee insert2(Employee emp){
		
		Employee order2 = null;
		Role role= emp.getRole();
		int power=role.getId();
		if(power==3 || power==4 || power==5) {
			 order2=repo.save(emp);

		}
		return order2;
	}
	

	
	
	
	
	
	
}
