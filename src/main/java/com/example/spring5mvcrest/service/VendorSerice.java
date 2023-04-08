package com.example.spring5mvcrest.service;

import com.example.spring5mvcrest.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorSerice {
    List<VendorDTO> getAllVendors();

    VendorDTO getVendorByName(String name);

    VendorDTO createVendor(VendorDTO vendorDTO);

    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

    void deleteVendor(Long id);

}
