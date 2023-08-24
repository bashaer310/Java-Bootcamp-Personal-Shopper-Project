package com.example.personalshoppersystem.Controller;

import com.example.personalshoppersystem.Model.Customer;
import com.example.personalshoppersystem.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getAllCustomers(){
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }


    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody @Valid Customer customer){
        customerService.addCustomer(customer);
        return ResponseEntity.status(200).body("Customer added!");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id,@RequestBody @Valid Customer customer){
        customerService.updateCustomer(id, customer);
        return ResponseEntity.status(200).body("Customer updated!");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body("Customer deleted!");
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity getCustomerById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(customerService.getCustomerById(id));
    }


    @GetMapping("/getByUsername/{username}")
    public ResponseEntity getCustomerByUsername(@PathVariable String username){
        return ResponseEntity.status(200).body(customerService.getCustomerByUsername(username));
    }

}
