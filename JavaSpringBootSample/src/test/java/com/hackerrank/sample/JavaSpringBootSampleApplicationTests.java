package com.hackerrank.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JavaSpringBootSampleApplication.class)
public class JavaSpringBootSampleApplicationTests {

	@Test
	public void contextLoads() {
	}
	 @Test
	   public void main() {
		 JavaSpringBootSampleApplication.main(new String[] {});
	   }
}

