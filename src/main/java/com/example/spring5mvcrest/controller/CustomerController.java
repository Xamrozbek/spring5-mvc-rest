package com.example.spring5mvcrest.controller;


import com.example.spring5mvcrest.api.v1.model.CustomerDTO;

import com.example.spring5mvcrest.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
@Tag(name = "customerController", description = "Customer API")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "Get all customers")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = CustomerDTO.class)))
    @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search customer")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = CustomerDTO.class)))
    @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), OK);
    }

    @PostMapping
    @Operation(summary = "Create customer")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = CustomerDTO.class)))
    @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    private ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Customer update")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = CustomerDTO.class)))
    @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDTO), OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = CustomerDTO.class)))
    @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(OK);
    }
}
