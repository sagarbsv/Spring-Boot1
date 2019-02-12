
package com.hackerrank.sample.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.sample.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {

	@Autowired
	private WebApplicationContext context;
	private Customer customer;
	private MockMvc mvc;
	private String path = "/customers"; 

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	// view Customers
	@Test
	public void getCustomerTest() throws Exception {

		ResultActions resultActions = mvc.perform(get(path));
		MockHttpServletResponse mockResponse = resultActions.andReturn().getResponse();

		if (mockResponse.getStatus() == 200) {
			resultActions.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$[0].customerId").isNumber())
					.andExpect(jsonPath("$[0].customerName").isString())
					.andExpect(jsonPath("$[0].contactNumber").isNumber());
		} else {

			resultActions.andExpect(status().isNotFound());
		}

	}

	@Test
	public void getCustomerInvalidTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(path + "/111").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(content().string("No Customer with given id found."));
	}

	@Test
	public void addCustomerTest() throws Exception {

		customer = new Customer();
		customer.setCustomerId(Long.valueOf(1001));
		customer.setAddress("test");
		customer.setCustomerName("test");
		customer.setGender("M");
		customer.setContactNumber((long) 345678655); 

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post(path).content(toJson(customer))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.customerId").isNumber()).andReturn();

		JSONObject json = new JSONObject(result.getResponse().getContentAsString());

		mvc.perform(MockMvcRequestBuilders.get(path + "/" + json.get("customerId")).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.customerId").value(json.get("customerId")))
				.andExpect(jsonPath("$.customerName").value("test"));

		mvc.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$[0].customerId").isNumber())
				.andExpect(jsonPath("$[0].customerName").isString());


	}

	@Test
	public void updateCustomerTest() throws Exception {

		customer = new Customer();
		customer.setCustomerId(Long.valueOf(123));
		customer.setAddress("test");
		customer.setCustomerName("test");
		customer.setGender("M");
		customer.setContactNumber((long) 23232);

		// update customer
		mvc.perform(MockMvcRequestBuilders.put(path + "/123").contentType(MediaType.APPLICATION_JSON)
				.content(toJson(customer)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		mvc.perform(MockMvcRequestBuilders.put(path + "/125").contentType(MediaType.APPLICATION_JSON)
				.content(toJson(customer)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(content().string("Customer with the id not exists."));

		mvc.perform(MockMvcRequestBuilders.delete(path + "/127").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(content().string("Customer with the id not exists."));
	}

	@Test
	public void deleteAllCustomerTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete(path).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}



	private byte[] toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(r).getBytes();
	}
}