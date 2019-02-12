package com.hackerrank.sample.controllertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackerrank.sample.model.Sku;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkuTest {
	@MockBean
	private Sku item;
	@Test
	public void allParametersAreMandatoryTest() {
		new Sku(1,"test","test",1,1,4);
	}

}
