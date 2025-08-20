package org.example.sbecommerce.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.sbecommerce.model.Address;
import org.example.sbecommerce.model.User;
import org.example.sbecommerce.payload.AddressDTO;
import org.example.sbecommerce.repositories.AddressRepository;
import org.example.sbecommerce.service.AddressService;
import org.example.sbecommerce.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Address APIs", description = "APIs for managing user addresses")
public class AddressController {

    @Autowired
    AuthUtil authUtil;

    @Autowired
    AddressService addressService;

    @Operation(summary = "Create address", description = "Create a new address for the logged-in user")
    @ApiResponse(responseCode = "201", description = "Address created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid address data")
    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        User user = authUtil.loggedInUser();
        AddressDTO savedAddressDTO = addressService.createAddress(addressDTO, user);
        return new ResponseEntity<>(savedAddressDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all addresses", description = "Retrieve all addresses (typically admin use)")
    @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully")
    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddresses(){
        List<AddressDTO> addressList = addressService.getAddresses();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @Operation(summary = "Get address by ID", description = "Retrieve a specific address by its ID")
    @ApiResponse(responseCode = "200", description = "Address retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Address not found")
    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long addressId){
        AddressDTO addressDTO = addressService.getAddressesById(addressId);
        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get current user's addresses", description = "Retrieve all addresses for the logged-in user")
    @ApiResponse(responseCode = "200", description = "User addresses retrieved successfully")
    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddresses(){
        User user = authUtil.loggedInUser();
        List<AddressDTO> addressList = addressService.getUserAddresses(user);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @Operation(summary = "Update address", description = "Update an existing address by ID")
    @ApiResponse(responseCode = "200", description = "Address updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid address data")
    @ApiResponse(responseCode = "404", description = "Address not found")
    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long addressId,
                                                    @RequestBody AddressDTO addressDTO){
        AddressDTO updatedAddress = addressService.updateAddress(addressId, addressDTO);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @Operation(summary = "Delete address", description = "Delete an address by ID")
    @ApiResponse(responseCode = "200", description = "Address deleted successfully")
    @ApiResponse(responseCode = "404", description = "Address not found")
    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> updateAddress(@PathVariable Long addressId){
        String status = addressService.deleteAddress(addressId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
