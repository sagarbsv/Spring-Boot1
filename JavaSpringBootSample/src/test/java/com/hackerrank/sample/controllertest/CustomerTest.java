package com.hackerrank.sample.controllertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackerrank.sample.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTest {
	@MockBean
	private Customer customer;
	
	@Test
	public void allParametersAreMandatoryTest() {
		new Customer(1, "test", null, "test", "gender");
	}

}
