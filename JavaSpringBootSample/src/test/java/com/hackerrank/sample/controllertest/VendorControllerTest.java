
package com.hackerrank.sample.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.hackerrank.sample.model.Vendor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorControllerTest {  

	@Autowired
	private WebApplicationContext context;
	private Vendor Vendor;
	private MockMvc mvc;
	private String path = "/vendors";   

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	// view Vendors
	@Test
	public void getVendorTest() throws Exception {

		ResultActions resultActions = mvc.perform(get(path));
		MockHttpServletResponse mockResponse = resultActions.andReturn().getResponse();

		if (mockResponse.getStatus() == 200) {
			resultActions.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$[0].vendorId").isNumber())
					.andExpect(jsonPath("$[0].vendorName").isString())
					.andExpect(jsonPath("$[0].contactNumber").isNumber());
		} else {

			resultActions.andExpect(status().isNotFound());
		}

	}

	@Test
	public void getVendorInvalidTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(path + "/111").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(content().string("No Vendor with given id found."));
	}

	@Test
	public void addVendorTest() throws Exception {

		Vendor = new Vendor();
		Vendor.setVendorId(Long.valueOf(123));
		Vendor.setVendorContactNo(123232);
		Vendor.setVendorName("test");
		Vendor.setVendorAddress("testing");
		Vendor.setVendorEmail("test@email");
		Vendor.setVendorUsername("testUser");

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post(path).content(toJson(Vendor))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.vendorId").isNumber()).andReturn();

		JSONObject json = new JSONObject(result.getResponse().getContentAsString());

		mvc.perform(MockMvcRequestBuilders.get(path + "/" + json.get("vendorId")).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.vendorId").value(json.get("vendorId")))
				.andExpect(jsonPath("$.vendorName").value("test"));

		mvc.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$[0].vendorId").isNumber())
				.andExpect(jsonPath("$[0].vendorName").isString());

	
	}

	@Test
	public void updateVendorTest() throws Exception {

		Vendor = new Vendor();
		Vendor.setVendorId(Long.valueOf(123));
		Vendor.setVendorContactNo(123232);
		Vendor.setVendorName("test");
		Vendor.setVendorAddress("testing");
		Vendor.setVendorEmail("test@email");
		Vendor.setVendorUsername("testUser");

		// update Vendor
		mvc.perform(MockMvcRequestBuilders.put(path + "/123").contentType(MediaType.APPLICATION_JSON)
				.content(toJson(Vendor)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		mvc.perform(MockMvcRequestBuilders.put(path + "/125").contentType(MediaType.APPLICATION_JSON)
				.content(toJson(Vendor)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(content().string("Vendor with the id not exists."));

		mvc.perform(MockMvcRequestBuilders.delete(path + "/127").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(content().string("Vendor with the id not exists."));
	}

	@Test
	public void deleteAllVendorTest() throws Exception {
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