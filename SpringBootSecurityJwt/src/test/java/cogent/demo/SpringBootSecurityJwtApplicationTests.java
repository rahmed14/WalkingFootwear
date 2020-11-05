package cogent.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import cogent.demo.controllers.CustomerC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;

//@WebMvcTest
@SpringBootTest
//@AutoConfigureMockMvc
class SpringBootSecurityJwtApplicationTests {

//	@LocalServerPort
//	private int port=9097;
	
//	@Autowired
//	private MockMvc mockMvc;
	
//	String controller="hello world";
	
	@Autowired
	CustomerC customer;
	
	
	@Test
	public void contextLoads() throws Exception {
//		assertThat(controller).isNotNull();
		System.out.println("STARTING the test");
		assertThat(customer.findCustomer(1)).isNotNull();
		System.out.println("after the test");
		
	}

//	@Test
//	public void shouldReturnDefaultMessage() throws Exception {
//		this.mockMvc.perform(get("/footwear/products/categoryID/1")).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("id")));
//	}
}


