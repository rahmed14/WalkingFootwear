package cogent.demo.security.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import cogent.demo.models.Product;
import cogent.demo.models.ProductCategory;



@CrossOrigin
public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	
//	public List<Product> findByName(String name);
	
	@Query("SELECT e FROM Product e WHERE e.name LIKE %:word%")
	public List<Product> listProductByName(@Param("word") String word);
	
	public List<Product> findByCategory(ProductCategory cid);

	
	//SELECT * FROM walk.product where category_id=1;
	//find my category id
	
	@Query("SELECT e FROM Product e WHERE e.category.id = :id")
	public List<Product> listProductByID(@Param("id") int id);
	
	Page<Product> findByCategoryId(@RequestParam("id") int id, Pageable pageable);

	Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
	




}
