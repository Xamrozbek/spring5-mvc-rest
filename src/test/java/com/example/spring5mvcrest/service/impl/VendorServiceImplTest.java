package com.example.spring5mvcrest.service.impl;

import com.example.spring5mvcrest.domain.Vendor;
import com.example.spring5mvcrest.mapper.VendorMapper;
import com.example.spring5mvcrest.repositories.VendorRepository;
import com.example.spring5mvcrest.service.VendorSerice;
import jakarta.persistence.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VendorServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "Joe";
    private static final String VENDOR_URL = "";
    @Mock
    VendorRepository vendorRepository;

    VendorSerice vendorSerice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        vendorSerice = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void getAllVendors() {
        List<Vendor> vendorList = List.of(new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendorList);

        var result = vendorSerice.getAllVendors();

        assertEquals(2, result.size());
    }

    @Test
    void getVendorByName() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);
        vendor.setVendorUrl(VENDOR_URL);

    }
}