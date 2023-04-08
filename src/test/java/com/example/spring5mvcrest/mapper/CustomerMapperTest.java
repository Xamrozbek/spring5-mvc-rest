package com.example.spring5mvcrest.mapper;

import com.example.spring5mvcrest.api.v1.model.CustomerDTO;
import com.example.spring5mvcrest.domain.Customer;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;

import static org.junit.jupiter.api.Assertions.*;

@Mapper
class CustomerMapperTest {

    private static final String LAST_NAME = "Joe";
    private static final Long ID = 1L;


    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    void customerDTOToCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(LAST_NAME);

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        assertEquals(customer.getLastname(), customerDTO.getLastname());
        assertNotNull(customerDTO);
    }

    @Test
    void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setLastname(LAST_NAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(customer.getLastname(), customerDTO.getLastname());
        assertNotNull(customerDTO);
    }
}