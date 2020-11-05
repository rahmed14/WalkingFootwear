package cogent.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@EnableAutoConfiguration
@Entity
@Table(name="OrderDetail")

public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="did")
	private int did;
	
	
  @ManyToOne @JsonManagedReference
    @JoinColumn(name = "id", nullable = false, insertable=false, updatable=false)
    private Product product;
  

  @ManyToOne @JsonManagedReference
    @JoinColumn(name = "ORDER_ID", nullable = false, insertable=false, updatable=false)
    private Order order;
  

  @Column(name="Price")
	private String Price;
  
  @Column(name="quantity")
	private int quantity;
	
  
	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}




}
