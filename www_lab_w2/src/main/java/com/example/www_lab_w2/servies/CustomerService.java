package com.example.www_lab_w2.servies;

import com.example.www_lab_w2.entity.Customer;
import com.example.www_lab_w2.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public void addCustomer(Customer customer){
        customerRepository.addCustomer(customer);
    }

    public List<Customer> getAll(){
        return customerRepository.getAll();
    }

    public Optional<Customer> findById(long id){
        return customerRepository.findById(id);
    }

    public boolean updateCustomer(long id, Customer customer){
        Optional<Customer> rs = customerRepository.findById(id);
        if(rs.isEmpty()){
            return false;
        }
        Customer existingCustomer = rs.get();
        if(customer.getAddress() != null){
            existingCustomer.setAddress(customer.getAddress());
        }
        if(customer.getEmail() != null){
            existingCustomer.setEmail(customer.getEmail());
        }
        if(customer.getPhone() != null){
            existingCustomer.setPhone(customer.getPhone());
        }
        if(customer.getCustName() != null){
            existingCustomer.setCustName(customer.getCustName());
        }
        customerRepository.updateCustomer(existingCustomer);
        return true;
    }
}
