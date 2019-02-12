package com.hackerrank.sample.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackerrank.sample.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {
@Autowired
CustomerServiceImpl customerServiceImpl;

@Test
public void testUpdate() {
	
    Long id = new Long(1);

    Customer entity = new Customer(id, "customerName", id, "address", "gender");
    customerServiceImpl.createCustomer(entity);
    Assert.assertNotNull("failure - expected not null", entity);

    String updatedText = " test";
    entity.setAddress(updatedText);
    Customer updatedEntity = customerServiceImpl.updateCustomer(entity,id);

    Assert.assertNotNull("failure - expected not null", updatedEntity);

    

}
}
