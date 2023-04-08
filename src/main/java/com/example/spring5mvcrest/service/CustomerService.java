package com.example.spring5mvcrest.service;

import com.example.spring5mvcrest.api.v1.model.CustomerDTO;


import java.util.List;

public interface CustomerService {


    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);
}
