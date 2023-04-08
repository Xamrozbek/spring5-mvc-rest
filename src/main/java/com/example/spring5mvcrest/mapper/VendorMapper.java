package com.example.spring5mvcrest.mapper;

import com.example.spring5mvcrest.api.v1.model.VendorDTO;
import com.example.spring5mvcrest.domain.Vendor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);

    VendorDTO vendorToVendorDTO(Vendor vendor);

}
