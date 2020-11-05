package cogent.demo.security.services;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.demo.models.Order;


@CrossOrigin
public interface OrderRepo extends JpaRepository<Order, Integer>{

//	Optional<Order> saveAll(OrderRepo order);

}
