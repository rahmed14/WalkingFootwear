package cogent.demo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@EnableAutoConfiguration
@Entity
@Table(name="Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ORDER_ID")
	private int orderId;
	
	@Column(name="ORDER_DATE")
	private Date orderDate;
	
	@Column(name="ORDER_SHIPPED_DATE")
	private Date orderShippedDate;
	
	@Column(name="ORDER_TRACKING_NUMBER")
	private String orderTrackingNumber;

	@Size(max = 60)
	@Column(name="ORDER_SHIP_ADDRESS")
	private String orderShppingAddress;
	
	@Column(name = "ORDER_UNIT_PRICE")
	private double orderUnitPrice;
	
	@Column(name = "ORDER_QUANTITY")
	private int orderQuantity;
	
	@Column(name = "ORDER_DISCOUNT")
	private double orderDiscount;
	
	@Column(name = "ORDER_TOTAL")
	private double orderTotal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "CUST_ID", referencedColumnName = "CUST_ID", insertable=false, updatable=false)
	private Customer customerid;
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderShippedDate() {
		return orderShippedDate;
	}

	public void setOrderShippedDate(Date orderShippedDate) {
		this.orderShippedDate = orderShippedDate;
	}

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

	public String getOrderShppingAddress() {
		return orderShppingAddress;
	}

	public void setOrderShppingAddress(String orderShppingAddress) {
		this.orderShppingAddress = orderShppingAddress;
	}

	public double getOrderUnitPrice() {
		return orderUnitPrice;
	}

	public void setOrderUnitPrice(double orderUnitPrice) {
		this.orderUnitPrice = orderUnitPrice;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public double getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(double orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Customer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Customer customerid) {
		this.customerid = customerid;
	}

	
	


}
