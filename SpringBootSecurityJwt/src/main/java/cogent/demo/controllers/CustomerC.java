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

import cogent.demo.models.Customer;
import cogent.demo.security.services.CustomerService;

@CrossOrigin
@RestController
@RequestMapping(value = "/Customer")
public class CustomerC {
	
	@Autowired
	CustomerService emp;
	
	
	
			@GetMapping(value = { "/findID" }, 
			produces = "application/json")
			public ResponseEntity<Customer> findCustomer(@RequestParam("id") int id) throws CustomerAppException {
				Customer e = emp.find(id);
				if (e == null) {
					throw new CustomerAppException(" record Not Found" );
				}
				return new ResponseEntity<>(e, HttpStatus.OK);
			}
			
			
			@GetMapping(value = { "/findbystate" }, 
			produces = "application/json")
			public ResponseEntity<List<Customer>>findCustomerbystate(@RequestParam("id") String id) throws CustomerAppException {
				List<Customer> e = emp.findallbystates(id);
				if (e == null) {
					throw new CustomerAppException(" record Not Found" );
				}
				return new ResponseEntity<>(e, HttpStatus.OK);
			}
			
			
			@GetMapping(value = { "/findall" }, 
			produces = "application/json")
			public ResponseEntity<List<Customer>>findall() throws CustomerAppException {
				List<Customer> e = emp.findalls();
				if (e == null) {
					throw new CustomerAppException(" record Not Found" );
				}
				return new ResponseEntity<>(e, HttpStatus.OK);
			}
			

			
			
			@PostMapping("/add")
			public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws CustomerAppException
			{
				Customer pro = null;		
				pro = emp.insert(customer);
				 
				return new ResponseEntity<>(pro, HttpStatus.OK); 
			}
			
			@GetMapping(value = { "/findcustomer" }, 
			produces = "application/json")
			public Customer findCustomer2(@RequestParam("id") int id) throws CustomerAppException {
				Customer e = emp.find(id);
				if (e == null) {
					throw new CustomerAppException(" record Not Found" );
				}
				return e;
			}
			
			
	

	

}
