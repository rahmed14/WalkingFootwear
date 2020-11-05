package cogent.demo.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import cogent.demo.models.Order;




@Service
public class OrderService {
	
	@Autowired
	OrderRepo ord;
	
	
	
	@GetMapping
	public Order findbyId(int id) {
		Order e = null;
		Optional<Order> oe = ord.findById(id);
		if (oe.isPresent()) {
			e = oe.get();
		}
		return e;
	}
	
	
	public Order insert(Order order){
		Order order2=ord.save(order);
		return order2;
	}
	
	

	

	
	
	
	
	
}
