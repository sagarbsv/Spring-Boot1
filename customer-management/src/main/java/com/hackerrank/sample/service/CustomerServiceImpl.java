package com.hackerrank.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.model.Customer;
import com.hackerrank.sample.repository.CustomerRepository;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void deleteAllCustomers() {
        customerRepository.deleteAllInBatch();
    }

    @Override
    public void deleteCustomerById(Long id) {  
                 Customer existingCustomer = customerRepository.findOne(id);
                 if (existingCustomer == null) {
             throw new NoSuchResourceFoundException("Customer with the id not exists."); 
         }
        customerRepository.delete(id);
    }

    @Override
    public Customer createCustomer(Customer Customer)  {
        Customer existingCustomer = customerRepository.findOne(Customer.getCustomerId());  

        if (existingCustomer!= null) {
            throw new BadResourceRequestException("Customer with same id exists.");
        }

        Customer savedCustomer= customerRepository.save(Customer);
        return savedCustomer;
    }

    @Override
    public Customer updateCustomer(Customer Customer,Long id)  {
        Customer existingCustomer = customerRepository.findOne(id);
     
        if (existingCustomer == null) {
            throw new NoSuchResourceFoundException("Customer with the id not exists.");
        }
       
        Customer savedCustomer= customerRepository.save(Customer);
        return savedCustomer;
    }
    
    @Override
    public Customer getCustomerById(Long id) { 
        Customer Customer = customerRepository.findOne(id);

        if (Customer == null) {
            throw new NoSuchResourceFoundException("No Customer with given id found.");
        }

        return Customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
                List<Customer>  customerList =customerRepository.findAll();
                 if (customerList.isEmpty()) {
             throw new NoSuchResourceFoundException("No Customer with given id found.");
         }
                 
                 return customerList;

    }
}
