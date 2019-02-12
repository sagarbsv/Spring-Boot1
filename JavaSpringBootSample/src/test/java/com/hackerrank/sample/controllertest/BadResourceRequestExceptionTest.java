package com.hackerrank.sample.controllertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackerrank.sample.exception.BadResourceRequestException;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BadResourceRequestExceptionTest {
	@Test(expected = BadResourceRequestException.class) 
	public void ExceptionTest() { 
	   throw  new BadResourceRequestException("testing"); 
	}

}
