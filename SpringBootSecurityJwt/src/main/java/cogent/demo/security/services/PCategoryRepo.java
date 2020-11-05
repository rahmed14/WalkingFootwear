package cogent.demo.security.services;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.demo.models.ProductCategory;



@CrossOrigin
public interface PCategoryRepo extends JpaRepository<ProductCategory, Integer> {
	
//    List<Product> findByCategory(@Param("id")int id);


}
