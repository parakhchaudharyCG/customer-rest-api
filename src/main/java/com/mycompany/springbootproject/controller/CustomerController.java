package com.mycompany.springbootproject.controller;

import com.mycompany.springbootproject.model.Customer;
import com.mycompany.springbootproject.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//RESTful APIs
@Slf4j
@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getall")
    public List<Customer> getCustomers() {
        log.info("INSIDE getCustomers!!");
        return customerService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        log.info("Inside Customer %s", customer);
        Customer cust = customerService.addCustomer(customer);
        return new ResponseEntity<>(cust, HttpStatus.CREATED);
    }

    @GetMapping("/getbyid/{id}")
    public Customer fetchById(@PathVariable int id) {
        return customerService.fetchById(id);
    }


    @PutMapping("/update/{id}")
    public void update(@PathVariable int id,@Valid @RequestBody Customer customer){
        log.info("Updating a customer!!");
        customerService.udpateCustomer(id, customer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        log.info("Deleting a customer with id %d", id);
        customerService.deleteCustomer(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
