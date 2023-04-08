package com.example.spring5mvcrest.mapper;

import com.example.spring5mvcrest.api.v1.model.VendorDTO;
import com.example.spring5mvcrest.domain.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;

import static org.junit.jupiter.api.Assertions.*;

@Mapper
class VendorMapperTest {

    private static final String NAME = "Joe";
    private static final Long ID = 1L;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;


    @Test
    void vendorDTOToVendor() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);
        vendor.setVendorUrl("");

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    void vendorToVendorDTO() {
        VendorDTO vendorDTO = new VendorDTO();

        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl("");

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), vendor.getName());
        assertEquals(vendorDTO.getVendorUrl(), vendor.getVendorUrl());
    }
}