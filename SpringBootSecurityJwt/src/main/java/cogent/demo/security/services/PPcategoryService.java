package cogent.demo.security.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import cogent.demo.models.ProductCategory;




@Service
public class PPcategoryService {
	
	@Autowired
	PCategoryRepo pro;
	
	@GetMapping
	public List<ProductCategory> findall() {
		List<ProductCategory> oe = pro.findAll();
		return oe;

	}
	
	
	@GetMapping
	public ProductCategory findbyId(int id) {
		ProductCategory e = null;
		Optional<ProductCategory> oe = pro.findById(id);
		if (oe.isPresent()) {
			e = oe.get();
		}
		return e;
	}
}
