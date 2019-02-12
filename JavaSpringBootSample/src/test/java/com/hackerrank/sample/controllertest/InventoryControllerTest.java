
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
import com.hackerrank.sample.model.Sku;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryControllerTest {  

	@Autowired
	private WebApplicationContext context;
	private Sku item;
	private MockMvc mvc;
	private String path = "/items";   

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	// view items
	@Test
	public void getitemTest() throws Exception {

		ResultActions resultActions = mvc.perform(get(path));
		MockHttpServletResponse mockResponse = resultActions.andReturn().getResponse();

		if (mockResponse.getStatus() == 200) {
			resultActions.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$[0].skuId").isNumber())
					.andExpect(jsonPath("$[0].productName").isString());
		} else {

			resultActions.andExpect(status().isNotFound());
		}

	}

	@Test
	public void getitemInvalidTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(path + "/111").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(content().string("No Item with given id found."));
	}

	@Test
	public void additemTest() throws Exception {

		item = new Sku();
		item.setSkuId(Long.valueOf(123));
		item.setProductName("testItem");
		item.setProductLabel("testLabel");
		item.setPrice(45);
		item.setMinQtyReq(3);
		item.setInventoryOnHand(4);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post(path).content(toJson(item))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.skuId").isNumber()).andReturn();

		JSONObject json = new JSONObject(result.getResponse().getContentAsString());

		mvc.perform(MockMvcRequestBuilders.get(path + "/" + json.get("skuId")).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.skuId").value(json.get("skuId")))
				.andExpect(jsonPath("$.productName").value("testItem"));

		mvc.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$[0].skuId").isNumber())
				.andExpect(jsonPath("$[0].productName").isString());

	
	}

	@Test
	public void updateitemTest() throws Exception {
		item = new Sku();
		item.setSkuId(Long.valueOf(123));
		item.setProductName("testItem");
		item.setProductLabel("testLabel");
		item.setPrice(45);
		item.setMinQtyReq(3);
		item.setInventoryOnHand(4);
		// update item
		mvc.perform(MockMvcRequestBuilders.put(path + "/123").contentType(MediaType.APPLICATION_JSON)
				.content(toJson(item)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		mvc.perform(MockMvcRequestBuilders.put(path + "/125").contentType(MediaType.APPLICATION_JSON)
				.content(toJson(item)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(content().string("Item with the id not exists."));

		mvc.perform(MockMvcRequestBuilders.delete(path + "/127").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(content().string("Item with the id not exists."));
	}

	@Test
	public void deleteAllitemTest() throws Exception {
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