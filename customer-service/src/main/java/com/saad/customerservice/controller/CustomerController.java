package com.saad.customerservice.controller;

import com.saad.customerservice.entities.Customer;
import com.saad.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customers")
public List<Customer> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }
@GetMapping("customers/{id}")
    public Customer findCustomerById(@PathVariable Long id)
{
    return customerService.findCustomerById(id);
}
@PostMapping("/customers")
public Customer saveCustomer(@RequestBody Customer customer)
{
    return customerService.saveCustomer(customer);
}




}
