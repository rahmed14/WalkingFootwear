package cogent.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.exception.CustomerAppException;

import cogent.demo.models.Employee;
import cogent.demo.security.services.EmployeeService;


@CrossOrigin
@RestController
@RequestMapping(value = "/Employee")
public class EmployeeC {
	
	@Autowired
	EmployeeService emp;
	
	
	@GetMapping(value = { "/findID" }, 
	produces = "application/json")
	public ResponseEntity<Object> findEmp(@RequestParam("id") int id) throws CustomerAppException {
		Employee e = emp.find(id);
		if (e == null) {
			throw new CustomerAppException(" record Not Found" );
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = { "/findName" }, 
	produces = "application/json")
	public ResponseEntity<Object> findEmp2(@RequestParam("name") String name) throws CustomerAppException {
		List<Employee> e = emp.findbyname(name);
		if (e == null) {
			throw new CustomerAppException(" record Not Found" );
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = { "/add" }, 
	produces = "application/json",
	consumes = "application/json")
	public ResponseEntity<Employee> addEmpoyee(@RequestBody Employee employee) throws CustomerAppException{
		Employee pro = null;		
		pro = emp.insert(employee);
		 
		return new ResponseEntity<>(pro, HttpStatus.OK); 
	}
	
	//Only manager, admin, owner can add employee
	@PostMapping(value = { "/add2" }, 
	produces = "application/json",
	consumes = "application/json")
	public ResponseEntity<Employee> addEmpoyee2(@RequestBody Employee employee) throws CustomerAppException{
		Employee manager = null;		
		manager = emp.insert2(employee);
		
		if (manager == null) {
			throw new CustomerAppException(" Not authorized to add employee" );
		}
		
		 
		return new ResponseEntity<>(manager, HttpStatus.OK); 
	}
	
	
	
	
	
	

}
