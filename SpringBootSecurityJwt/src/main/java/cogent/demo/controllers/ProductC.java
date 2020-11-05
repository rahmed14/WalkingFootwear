package cogent.demo.controllers;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;



import com.example.demo.exception.CustomerAppException;

import cogent.demo.models.Customer;
import cogent.demo.models.Order;
import cogent.demo.models.Product;
import cogent.demo.models.ProductCategory;
import cogent.demo.security.services.CustomerService;
import cogent.demo.security.services.OrderService;
import cogent.demo.security.services.ProductRepo;
import cogent.demo.security.services.ProductService;
import cogent.demo.security.services.StripeService;


@CrossOrigin
@RestController
@RequestMapping(value = "/products")
public class ProductC {
	
	@Autowired
	ProductService serv;

	@Autowired
	OrderService oserv;
	
	@Autowired
	CustomerC cust;
	
	@Autowired
	CustomerService cserve;
	
	@Autowired
	ProductRepo repo;
	
	
	
	
	@Autowired
	private StripeService stripeService;
	
	@Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
		
	@GetMapping(value = { "/{id}" }, 
	produces = "application/json")
	public ResponseEntity<Object> findProfuct(@PathVariable(value = "id") int id) throws CustomerAppException {
		System.out.println("IT IS COMING HERE");
		Product e = serv.findbyId(id);
		if (e == null) {
			throw new CustomerAppException(" record Not Found" );
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	

	
	
	
	//find all the products by category id
	@GetMapping("/category/{id}")
	public ResponseEntity<List<Product>> getCategoryById(@PathVariable(value = "id") ProductCategory categoryId) {
	    List<Product> category = serv.findbycategoryID(categoryId);
	    if (category == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(category);
	}
	
	
	
	@GetMapping("/categoryID/{id}")
	public ResponseEntity<List<Product>> getCategoryById2(@PathVariable(value = "id") int categoryId) {
	    List<Product> category = repo.listProductByID(categoryId);
	    if (category == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(category);
	}
	
	
	//find all the products by category id
	//this is using page object
	@GetMapping("/categorybyID2/{id}")
	public ResponseEntity<Page<Product>> getCategoryById3(@PathVariable(value = "id") int categoryId) {
//		Sort sort = new Sort(new Sort.Order(Direction.ASC, "lastName"));
//		Pageable pageable = new PageRequest(0, 5, sort);
		
		Pageable sortedByPriceDesc = 
				  PageRequest.of(0, 1, Sort.by("unitPrice").descending());

	    Page<Product> category = repo.findByCategoryId(categoryId,sortedByPriceDesc);
	    if (category == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(category);
	}
	

	
	//manager/admin can view stock
	@GetMapping(value = { "/viewstock" }, 
	produces = "application/json")
	public ResponseEntity<Integer> StockView(@RequestParam("id") int id) throws CustomerAppException {
		Product e = serv.findbyId(id);
		int total=e.getUnitsInStock();
		if (total == 0) {
			throw new CustomerAppException(" No profucts available" );
		}
		return new ResponseEntity<>(total, HttpStatus.OK);
	}
	
	
	
	
	
	//manager can view stock AND order new products
	@GetMapping(value = { "/vieworder" }, 
	produces = "application/json")
	public ResponseEntity<Product> newrequest(@RequestParam("id") int id) throws CustomerAppException {
		Product e = serv.findbyId(id);
		
		int total=e.getUnitsInStock();
		if (total == 0) {
			e.setUnitsInStock(10);
			addProduct(e);
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	
	@GetMapping(value = { "/Search" }, 
	produces = "application/json")
	public List<Product> findProducts(@RequestParam("name") String name) throws CustomerAppException {
		List<Product> e = serv.findName(name);
		
		if (e == null) {
			throw new CustomerAppException(" record Not Found" );
		}
		return e;
	}
	
	
	//find all the products
	@GetMapping(value = { "/findall" }, 
	produces = "application/json")
	public List<Product> findallproducts() throws CustomerAppException {
		List<Product> e = serv.findall();
		
		if (e == null) {
			throw new CustomerAppException(" record Not Found" );
		}
		return e;
	}
	
	
	
	@PostMapping(value = { "/buy/product" }, produces = "application/json", consumes  = "application/json")
	@Transactional
	public ResponseEntity<Object> buyProduct(@RequestBody Product product) throws CustomerAppException
	{
		Order order = new Order();	
		Customer customer = new Customer();
		
		
		order.setOrderUnitPrice(product.getUnitPrice());
		order.setOrderQuantity(1);
		
		order.setOrderTotal(product.getUnitPrice() * order.getOrderQuantity());
		

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        order.setOrderDate(ts);
        order.setOrderShippedDate(ts);
        order.setOrderShppingAddress(customer.getCustomerAddress());
        order.setOrderDiscount(0.0);
//        order.setCustomerid(1);
                        
        int randomNum = ThreadLocalRandom.current().nextInt(5, 20 + 1);
        String hello=String.valueOf(randomNum);
        order.setOrderTrackingNumber(hello);
      
        oserv.insert(order);
        
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	
	
	@RequestMapping("/checkout")
    public String home(Model model) {
        model.addAttribute("amount", 50 * 100); // In cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        return "index";
    }
	
	
	

	
	@PostMapping(value = { "/buy/product2" }, produces = "application/json", consumes  = "application/json")
	@Transactional
	public ResponseEntity<Object> buyProduct2(@RequestBody Product product) throws CustomerAppException
	{
		Order order = new Order();
		
		String [ ] add= {"2344 lilburn road", "3455 water road", "4566 duluth hw", "567 lawrenceville", "245 arcedo circle", 
				"456 bever ruin", "498 indian trail"};
		

		

		order.setOrderUnitPrice(product.getUnitPrice());
		order.setOrderQuantity(1);
		
		order.setOrderTotal(product.getUnitPrice() * order.getOrderQuantity());
		

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        order.setOrderDate(ts);
        order.setOrderShippedDate(ts);
        
        double a = Math.random() * 6;
        int value = (int) a;
        
        order.setOrderShppingAddress(add[value]);
        order.setOrderDiscount(0.0);
                        
        int randomNum = ThreadLocalRandom.current().nextInt(5, 20 + 1);
        String hello=String.valueOf(randomNum);
        order.setOrderTrackingNumber(hello);
      
        oserv.insert(order);
        
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	
	
	
	
	//AddProduct

	@PostMapping(value = { "/add/product" }, produces = "application/json", consumes  = "application/json")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws CustomerAppException
	{
		Product pro = null;		
		pro = serv.insert(product);
		 
		return new ResponseEntity<>(pro, HttpStatus.OK); 
	}
	
	
	//update product
	@PutMapping("/update/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws CustomerAppException
	{
		Product pro = null;
		Optional<Product> findproduct= serv.findbyId2(product.getId());
		
		if(!findproduct.isPresent()){
			throw new CustomerAppException("Product not found");
		}
		else {
//			serv.delete(findproduct);
		}
		
		pro = serv.insert(product);
		return new ResponseEntity<>(pro, HttpStatus.CREATED); 
	}
	
	
	
	//delete 
	@DeleteMapping("/delete/product")
	public ResponseEntity<Product> deleteProduct(@RequestBody Product product) throws CustomerAppException
	{
		Product pro = null;
		Optional<Product> findproduct= serv.findbyId2(product.getId());
		
		if(!findproduct.isPresent()){
			throw new CustomerAppException("Product not found");
		}
		else {
			serv.delete(product);
		}

		return new ResponseEntity<>(pro, HttpStatus.OK); 
	}
	
	
//	private String getUserNameFromLoggedInUser()
//	{
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = authentication.getName();
//		
//		return currentPrincipalName;
//	}
	


}
