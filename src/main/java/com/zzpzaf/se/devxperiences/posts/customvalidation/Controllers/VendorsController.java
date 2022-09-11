package com.zzpzaf.se.devxperiences.posts.customvalidation.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects.Vendor;
import com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects.VendorDTO;
import com.zzpzaf.se.devxperiences.posts.customvalidation.Repositories.VendorsRepo;


@RestController
public class VendorsController {
    
    @Autowired
    VendorsRepo vendorsRepo;

    @GetMapping(value = "/vendors")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        
        List<Vendor> returnVendorsList = new ArrayList<>();

        try {
            returnVendorsList = vendorsRepo.getAllVendors();
            if (returnVendorsList.size() > 0) {
                return new ResponseEntity<>(returnVendorsList, HttpStatus.OK); 
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }

    @GetMapping("/vendors/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable("id") Long id) {

        Vendor vendor;
        vendor = vendorsRepo.getVendorById(id);
        if (vendor != null) {
            return new ResponseEntity<>(vendor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/vendors")
    public ResponseEntity<Vendor> createVendor(@Valid @RequestBody VendorDTO dto) {

        Vendor vendor = vendorsRepo.createVendor(dto);
        if (vendor != null) {
            return new ResponseEntity<>(vendor, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @PutMapping("/vendors/{id}")
    public ResponseEntity<Vendor> updateVendorById(@PathVariable("id") Long id, @RequestBody VendorDTO dto) {
        
        Vendor vendor = vendorsRepo.updateVendorById(id, dto);
        if (vendor != null) {
            return new ResponseEntity<>(vendor, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }



    @DeleteMapping("/vendors/{id}")
    public ResponseEntity<Vendor> deleteVendorById(@PathVariable("id") Long id) {

        Vendor vendor = vendorsRepo.deleteVendorById(id);
        if (vendor != null ) {
            return new ResponseEntity<>(vendor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
