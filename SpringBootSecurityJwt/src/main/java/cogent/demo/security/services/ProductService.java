package cogent.demo.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import cogent.demo.models.Product;
import cogent.demo.models.ProductCategory;



@Service
public class ProductService {
	
	@Autowired
	ProductRepo pro;
	
	
	
	@GetMapping
	public Product findbyId(int id) {
		Product e = null;
		Optional<Product> oe = pro.findById(id);
		if (oe.isPresent()) {
			e = oe.get();
		}
		return e;
	}
	
	
	@GetMapping
	public List<Product> findall() {
		List<Product> oe = pro.findAll();
		return oe;

	}
	
	
	
	@GetMapping
	public List<Product> findName(String Name) {
		List<Product> oe = pro.listProductByName(Name);
		return oe;

	}
	
	//finds products by category ID
	
	public List<Product> findbycategoryID(ProductCategory cid) {
		List<Product> oe = pro.findByCategory(cid);
		return oe;

	}
	

	
	
	
	public Product insert(Product product){
		Product info = pro.save(product);		
		return info;
	}
	
	
	@GetMapping
	public Optional<Product>  findbyId2(int id) {
		 Optional<Product> found = pro.findById(id);
		return found;	
	}
	
	
	public void delete(Product product){
		pro.delete(product);	
		
	}
	

	
	
	
	
	
	
	

}
