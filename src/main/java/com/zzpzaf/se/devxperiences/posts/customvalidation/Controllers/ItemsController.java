package com.zzpzaf.se.devxperiences.posts.customvalidation.Controllers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects.Item;
import com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects.ItemDTO;
import com.zzpzaf.se.devxperiences.posts.customvalidation.Repositories.ItemsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ItemsController {

    @Autowired
    ItemsRepo itemsRepo;

    @GetMapping(value = "/items")
    public ResponseEntity<List<Item>> getAllItems(@RequestParam(name = "vendorId", required = false) Long vendorId) {
        
        List<Item> returnItemsList = new ArrayList<>();

        try {
            if (vendorId == null) {
                returnItemsList = itemsRepo.getAllItems();
            }else {
                returnItemsList = itemsRepo.getAllItems(vendorId);
            }
            if (returnItemsList.size() > 0) {
                return new ResponseEntity<>(returnItemsList, HttpStatus.OK); 
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) {

        Item item;
        item = itemsRepo.getItemById(id);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@Valid @RequestBody ItemDTO dto) {

        Item item = itemsRepo.createItem(dto);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

    }


    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItemById(@PathVariable("id") Long id, @RequestBody ItemDTO dto) {
        
        Item item = itemsRepo.updateItemById(id, dto);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }



    @DeleteMapping("/items/{id}")
    public ResponseEntity<Item> deleteItemById(@PathVariable("id") Long id) {

        Item item = itemsRepo.deleteItemById(id);
        if (item != null ) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }















    
}
