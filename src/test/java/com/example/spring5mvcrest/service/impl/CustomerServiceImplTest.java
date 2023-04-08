package com.example.spring5mvcrest.service.impl;

import com.example.spring5mvcrest.domain.Customer;
import com.example.spring5mvcrest.mapper.CustomerMapper;
import com.example.spring5mvcrest.repositories.CustomerRepository;
import com.example.spring5mvcrest.service.CustomerService;
import jakarta.persistence.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.WeakHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    private static final Long ID = 1L;
    private static final String LAST_NAME = "Joe";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getAllCustomers() {
        List<Customer> customerList = List.of(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customerList);

        var result = customerService.getAllCustomers();

        assertEquals(3, result.size());
    }

    @Test
    void getCustomerById() {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setLastname(LAST_NAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        var dto = customerService.getCustomerById(ID);
        assertEquals(LAST_NAME, dto.getLastname());
        assertNotNull(dto);
    }
}