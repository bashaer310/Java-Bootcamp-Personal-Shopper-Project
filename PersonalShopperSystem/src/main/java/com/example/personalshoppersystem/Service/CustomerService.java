package com.example.personalshoppersystem.Service;

import com.example.personalshoppersystem.API.ApiException;
import com.example.personalshoppersystem.Model.Customer;
import com.example.personalshoppersystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }


    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }


    public void updateCustomer(Integer id , Customer customer){
        Customer customer1 = customerRepository.findCustomerById(id);

        if (customer1 == null){
            throw new ApiException("Customer Id not found");
        }

        customer1.setName(customer.getName());
        customer1.setEmail(customer.getEmail());
        customer1.setPassword(customer.getPassword());
        customer1.setUsername(customer.getUsername());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customer1.setGender(customer.getGender());
        customer1.setCity(customer.getCity());
        customer1.setAddress(customer.getAddress());
        customer1.setAge(customer.getAge());

        customerRepository.save(customer1);
    }


    public void deleteCustomer(Integer id){
        Customer customer = customerRepository.findCustomerById(id);

        if (customer == null){
            throw new ApiException("Customer Id not found");
        }

        customerRepository.delete(customer);
    }


    public Customer getCustomerById(Integer id){
        Customer customer = customerRepository.findCustomerById(id);

        if (customer == null){
            throw new ApiException("Customer Id not found");
        }

        return customer;
    }


    public Customer getCustomerByUsername(String username){
        Customer customer = customerRepository.findCustomerByUsername(username);

        if (customer == null){
            throw new ApiException("Customer username not found");
        }

//        Customer customer1 = customer;
//        customer1.setName(customer.getName());
//        customer1.setUsername(customer.getUsername());
//        customer1.setPhoneNumber(customer.getPhoneNumber());
//        customer1.setGender(customer.getGender());
//        customer1.setCity(customer.getCity());
//        customer1.setAddress(customer.getAddress());
//        customer1.setDateOfBirth(customer.getDateOfBirth());
//        customerRepository.save(customer1);

        return customer;
    }

}
