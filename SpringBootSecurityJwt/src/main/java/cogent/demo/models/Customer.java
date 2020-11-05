package cogent.demo.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
@Table(name="Customers")
public class Customer{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CUST_ID")
	private int customerId; 
	
	@Column(name = "USER_ID")
	private int userId;
	
	@Size(max = 50)
	@Column(name="CUST_FNAME")
	private String customerFName;
	
	@Size(max = 50)
	@Column(name="CUST_LNAME")
	private String customerLName;
	
	@Size(max = 50)
	@Column(name="CUST_ADDRESS")
	private String customerAddress;
	
	@Size(max = 15)
	@Column(name="CUST_CITY")
	private String customerCity;
	
	@Size(max = 15)
	@Column(name="CUST_STATE")
	private String customerState;
	
	@Size(max = 15)
	@Column(name="CUST_POSTAL_CODE")
	private String customerPostalCode;
	
	@Size(max = 15)
	@Column(name="CUST_COUNTRY")
	private String customerCountry;
	
	@Size(max = 15)
	@Column(name="CUST_PHONE")
	private String customerPhone;
	
	@Size(max = 15)
	@Column(name="CUST_Email")
	private String customerEmail;


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCustomerFName() {
		return customerFName;
	}

	public void setCustomerFName(String customerFName) {
		this.customerFName = customerFName;
	}

	public String getCustomerLName() {
		return customerLName;
	}

	public void setCustomerLName(String customerLName) {
		this.customerLName = customerLName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerState() {
		return customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public String getCustomerPostalCode() {
		return customerPostalCode;
	}

	public void setCustomerPostalCode(String customerPostalCode) {
		this.customerPostalCode = customerPostalCode;
	}

	public String getCustomerCountry() {
		return customerCountry;
	}

	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	
}
