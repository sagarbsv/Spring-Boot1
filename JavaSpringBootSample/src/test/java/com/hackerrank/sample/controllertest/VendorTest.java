package com.hackerrank.sample.controllertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackerrank.sample.model.Vendor;
@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorTest {
@MockBean
Vendor vendoer;
	@Test
	public void test() {
		new Vendor(1, "test", 233232, "test@ert", "vendoruser", "sky");
	}

}
