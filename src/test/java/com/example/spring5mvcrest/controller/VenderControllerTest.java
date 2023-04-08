package com.example.spring5mvcrest.controller;

import com.example.spring5mvcrest.api.v1.model.VendorDTO;
import com.example.spring5mvcrest.service.VendorSerice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VenderControllerTest extends AbstractRestControllerTest {

    private static final String NAME = "Joe";
    @Mock
    private VendorSerice vendorSerice;

    @InjectMocks
    private VenderController venderController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(venderController).build();
    }

    @Test
    void getAllVendors() {
        List<VendorDTO> vendorDTOList = List.of(new VendorDTO(), new VendorDTO(), new VendorDTO());

        when(vendorSerice.getAllVendors()).thenReturn(vendorDTOList);

        var result = vendorSerice.getAllVendors();

        assertEquals(3, result.size());
    }

    @Test
    void getByName() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl("/api/v1/vendors/1");

        when(vendorSerice.getVendorByName(anyString())).thenReturn(vendorDTO);

        var result = vendorSerice.getVendorByName(NAME);

        assertEquals(NAME, result.getName());
    }

    @Test
    void createVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl("/api/v1/vendors/1");

        VendorDTO returnVendor = new VendorDTO();
        returnVendor.setName(vendorDTO.getName());
        returnVendor.setVendorUrl(vendorDTO.getVendorUrl());

        when(vendorSerice.createVendor(any(VendorDTO.class))).thenReturn(returnVendor);

        mockMvc.perform(post("/api/v1/vendors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Joe")));

    }

    @Test
    void updateVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl("/api/v1/vendors/1");

        VendorDTO returnVendor = new VendorDTO();
        returnVendor.setName(vendorDTO.getName());
        returnVendor.setVendorUrl(vendorDTO.getVendorUrl());

        when(vendorSerice.updateVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnVendor);

        mockMvc.perform(put("/api/v1/vendors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Joe")));
    }

    @Test
    void deleteVendor() throws Exception {
        mockMvc.perform(delete("/api/v1/vendors/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorSerice).deleteVendor(anyLong());
    }
}