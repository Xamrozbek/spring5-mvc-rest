package com.example.spring5mvcrest.controller;

import com.example.spring5mvcrest.api.v1.model.VendorDTO;
import com.example.spring5mvcrest.service.VendorSerice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "vendorController", description = "Vendor API")
public class VendorController {

    private final VendorSerice vendorSerice;

    @GetMapping
    @Operation(summary = "Get All Vendor")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = VendorDTO.class)))
    @ApiResponse(responseCode = "404", description = "Vendor not found", content = @Content)
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        return new ResponseEntity<>(vendorSerice.getAllVendors(), OK);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Search by seller name")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = VendorDTO.class)))
    @ApiResponse(responseCode = "404", description = "Vendor not found", content = @Content)
    public ResponseEntity<VendorDTO> getByName(@PathVariable String name) {
        return new ResponseEntity<>(vendorSerice.getVendorByName(name), OK);
    }

    @PostMapping
    @Operation(summary = "Create vendor")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendorDTO.class)))
    @ApiResponse(responseCode = "404", description = "Vendor not found", content = @Content)
    public ResponseEntity<VendorDTO> createVendor(@RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorSerice.createVendor(vendorDTO), CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vendor")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = VendorDTO.class)))
    @ApiResponse(responseCode = "404", description = "Vendor not found", content = @Content)
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorSerice.updateVendor(id, vendorDTO), OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vendor")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = VendorDTO.class)))
    @ApiResponse(responseCode = "404", description = "Vendor not found", content = @Content)
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorSerice.deleteVendor(id);
        return new ResponseEntity<>(OK);
    }
}
