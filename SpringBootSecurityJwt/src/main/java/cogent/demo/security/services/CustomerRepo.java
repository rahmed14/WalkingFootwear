package cogent.demo.security.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.demo.models.Customer;



@CrossOrigin
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	List<Customer> findByCustomerFName(String name);
//	public List<Employee> findByEmpFirstName(String name);

	@Query("SELECT e FROM Customer e WHERE e.customerState LIKE :word")
	public List<Customer> listCustomerByState(@Param("word") String word);
}
