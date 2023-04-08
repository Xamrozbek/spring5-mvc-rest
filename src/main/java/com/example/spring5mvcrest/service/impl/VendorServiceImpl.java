package com.example.spring5mvcrest.service.impl;

import com.example.spring5mvcrest.api.v1.model.VendorDTO;
import com.example.spring5mvcrest.mapper.VendorMapper;
import com.example.spring5mvcrest.repositories.VendorRepository;
import com.example.spring5mvcrest.service.VendorSerice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorSerice {

    private final VendorRepository vendorRepository;

    private final VendorMapper vendorMapper;

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll().stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl("/api/v1/vendors/" + vendor.getId());
                    return vendorDTO;
                })
                .toList();
    }

    @Override
    public VendorDTO getVendorByName(String name) {
        return vendorRepository.findByName(name)
                .map(vendor -> {
                            VendorDTO vendorDTO = new VendorDTO();
                            vendorDTO.setVendorUrl("/api/v1/vendors/" + vendor.getId());
                            return vendorDTO;
                        }
                )
                .orElse(null);
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        var vendor = vendorRepository.save(vendorMapper.vendorDTOToVendor(vendorDTO));
        var saveVender = vendorMapper.vendorToVendorDTO(vendor);
        saveVender.setVendorUrl("/api/v1/vendors/" + vendor.getId());
        return saveVender;
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        var vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);
        var updateVendor = vendorRepository.save(vendor);
        var updateVendorDTO = vendorMapper.vendorToVendorDTO(updateVendor);
        updateVendorDTO.setVendorUrl("/api/v1/vendors/" + id);
        return updateVendorDTO;
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}
