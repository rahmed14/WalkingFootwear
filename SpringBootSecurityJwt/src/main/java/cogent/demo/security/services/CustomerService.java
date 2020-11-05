package cogent.demo.security.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.demo.models.Customer;




@Service
public class CustomerService {

	@Autowired
	CustomerRepo repo;
	
	
	private Logger logger = Logger.getLogger("EmployeeServiceAspect");

	
	
	public Customer insert(Customer customer) {
		Customer cust=repo.save(customer);
		logger.info(" loggin this after using insert method");
		return cust;

	}
	
	
	public List<Customer> all() {
		List<Customer> empList = repo.findAll();
		return empList;
	}
	
	public Customer find(int id) {
		Customer e = null;
		Optional<Customer> oe = repo.findById(id);
		if (oe.isPresent()) {
			e = oe.get();
		}
		return e;
	}
	
	public List<Customer> findallbystates(String state) {
		List<Customer> empList = repo.listCustomerByState(state);
		return empList;
	}
	
	
	public List<Customer> findalls() {
		List<Customer> empList = repo.findAll();
		return empList;
	}
	

	
}
