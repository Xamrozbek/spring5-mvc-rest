package com.example.spring5mvcrest.controller;

import com.example.spring5mvcrest.api.v1.model.VendorDTO;
import com.example.spring5mvcrest.service.VendorSerice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/api/v1/vendors")
@RequiredArgsConstructor
public class VenderController {

    private final VendorSerice vendorSerice;

    @GetMapping
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        return new ResponseEntity<>(vendorSerice.getAllVendors(), OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<VendorDTO> getByName(@PathVariable String name) {
        return new ResponseEntity<>(vendorSerice.getVendorByName(name), OK);
    }

    @PostMapping
    public ResponseEntity<VendorDTO> createVendor(@RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorSerice.createVendor(vendorDTO), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorSerice.updateVendor(id, vendorDTO), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorSerice.deleteVendor(id);
        return new ResponseEntity<>(OK);
    }
}
