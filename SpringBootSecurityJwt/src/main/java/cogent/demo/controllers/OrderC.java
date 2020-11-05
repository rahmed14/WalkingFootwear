package cogent.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.exception.CustomerAppException;

import cogent.demo.models.Order;
import cogent.demo.security.services.OrderService;


@CrossOrigin
@RestController
@RequestMapping(value = "/Order")
public class OrderC {

	@Autowired
	OrderService ord;
	
	
	@GetMapping(value = { "/findID" }, 
	produces = "application/json")
	public ResponseEntity<Object> findOrder(@RequestParam("id") int id) throws CustomerAppException {
		Order e = ord.findbyId(id);
		if (e == null) {
			throw new CustomerAppException(" record Not Found" );
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	
	
}
