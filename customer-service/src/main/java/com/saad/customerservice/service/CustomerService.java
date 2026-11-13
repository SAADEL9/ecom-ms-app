package com.saad.customerservice.service;

import com.saad.customerservice.entities.Customer;
import com.saad.customerservice.repository.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @McpTool(description = "get all customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    @McpTool(description = "get customer by Id")
    public Customer findCustomerById(@McpToolParam(description = "the customer id") Long id)
    {
        return customerRepository.findById(id).orElseThrow(()->new RuntimeException("Custumer not found"));
    }
    @McpTool(description = "create customer")
    public Customer saveCustomer(@McpToolParam(description = "the customer to save") Customer customer)
    {
        return customerRepository.save(customer);
    }
}
