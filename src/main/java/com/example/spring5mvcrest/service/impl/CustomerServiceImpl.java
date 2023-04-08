package com.example.spring5mvcrest.service.impl;


import com.example.spring5mvcrest.api.v1.model.CustomerDTO;
import com.example.spring5mvcrest.mapper.CustomerMapper;
import com.example.spring5mvcrest.repositories.CustomerRepository;

import com.example.spring5mvcrest.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .toList();
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + id);
                    return customerDTO;
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        var customer = customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));
        var savedCustomerDto = customerMapper.customerToCustomerDTO(customer);
        savedCustomerDto.setCustomerUrl("/api/v1/customers/" + customer.getId());
        return savedCustomerDto;
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        var customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        var updatedCustomer = customerRepository.save(customer);
        var updatedCustomerDTO = customerMapper.customerToCustomerDTO(updatedCustomer);
        updatedCustomerDTO.setCustomerUrl("/api/v1/customers/" + id);
        return updatedCustomerDTO;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
