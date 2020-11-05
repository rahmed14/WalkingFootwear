package cogent.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import com.example.demo.exception.CustomerAppException;

import cogent.demo.models.ProductCategory;
import cogent.demo.security.services.PPcategoryService;


@CrossOrigin
@RestController
@RequestMapping(value = "/Category")
public class ProductCategoryC {
	
	@Autowired
	PPcategoryService serv;
	
	@GetMapping(value = { "/findID" }, 
	produces = "application/json")
	public ResponseEntity<Object> findProduct(@RequestParam("id") int id) throws CustomerAppException {
		ProductCategory e = serv.findbyId(id);
		if (e == null) {
			throw new CustomerAppException(" record Not Found" );
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	
	//find all the products
	@GetMapping(value = { "/findall" }, 
	produces = "application/json")
	public List<ProductCategory> findallproducts() throws CustomerAppException {
		List<ProductCategory> e = serv.findall();
		
		if (e == null) {
			throw new CustomerAppException(" record Not Found" );
		}
		return e;
	}
	
	

}
