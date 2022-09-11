package com.zzpzaf.se.devxperiences.posts.customvalidation.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects.Item;
import com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects.ItemDTO;


@Repository
public class ItemsRepo {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String ITEMS_TABLE = "items";
   
    
    public List<Item> getAllItems() {

        List<Item> itemsList = new ArrayList<>();
        try {
            itemsList = jdbcTemplate.query("SELECT * FROM " + ITEMS_TABLE, 
            BeanPropertyRowMapper.newInstance(Item.class));
        } catch (Exception e) {
            return null;
        }
        return itemsList;
    }


    public List<Item> getAllItems(Long vendorId) {

        List<Item> itemsList = new ArrayList<>();
        try {
            itemsList = jdbcTemplate.query("SELECT * FROM " + ITEMS_TABLE + " WHERE itemVendorId=?", 
            BeanPropertyRowMapper.newInstance(Item.class), vendorId);
        } catch (Exception e) {
            return null;
        }
        return itemsList;
    }


    public Item getItemById(Long id) {

        Item item = new Item();
        try {
            item = jdbcTemplate.queryForObject("SELECT * FROM " + ITEMS_TABLE + " WHERE itemId=?",
                   BeanPropertyRowMapper.newInstance(Item.class), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        return item;
    }



    public Item createItem(ItemDTO dto) {

        Item item = new Item();

        item.setItemName(dto.getItemName());

        if (dto.getItemCategoryId() <= 0) {
            item.setItemCategoryId(0);
        } else {
            item.setItemCategoryId(dto.getItemCategoryId());
        }

        if (dto.getItemVendorId() <= 0) {
            item.setItemVendorId(0);
        } else {
            item.setItemVendorId(dto.getItemVendorId());
        }

        item.setItemModelYear(dto.getItemModelYear());
        item.setItemListPrice(dto.getItemListPrice());
        item.setitemUUID(UUID.randomUUID().toString());

        int status = 0;
        try {
            status = jdbcTemplate.update("INSERT INTO " + ITEMS_TABLE + " (" +
                "itemName, " +
                "itemCategoryId, " +
                "itemVendorId, " +
                "itemModelYear, " +
                "itemListPrice, " +
                "itemUUID" +
                ")" +
                " VALUES(?,?,?,?,?,?)",
                new Object[]{item.getItemName(),
                             item.getItemCategoryId(),
                             item.getItemVendorId(),
                             item.getItemModelYear(),
                             item.getItemListPrice(),
                             item.getitemUUID()
                });        
        } catch (Exception e) {
            return null;
        }
        
        Item createdItem = null;
        if(status != 0){
            createdItem = getItemByUUID(item.getitemUUID());
        }
        return createdItem;

    }


    public Item updateItemById(Long id, ItemDTO dto) {
        
        Item item = null;

        item = getItemById(id);

        if (!dto.getItemName().trim().equals("")) {
            item.setItemName(dto.getItemName().trim());
        } 

        if (dto.getItemCategoryId() > 0) {
            item.setItemCategoryId(dto.getItemCategoryId());
        }

        if (dto.getItemVendorId() > 0) {
           item.setItemVendorId(dto.getItemVendorId());
        }

        if (dto.getItemModelYear() > 0) {
            item.setItemModelYear(dto.getItemModelYear());
        }

        if (dto.getItemListPrice() > 0) {
           item.setItemListPrice(dto.getItemListPrice());
        }

        try {
            jdbcTemplate.update("UPDATE " + ITEMS_TABLE + " SET " + 
                "itemName=?, " +
                "itemCategoryId=?, " +
                "itemVendorId=?, " +
                "itemModelYear=?, " +
                "itemListPrice=? " +
                "WHERE itemId=?",
                new Object[]{item.getItemName(),
                    item.getItemCategoryId(),
                    item.getItemVendorId(),
                    item.getItemModelYear(),
                    item.getItemListPrice(),
                    item.getItemId()
                });   

            item = getItemById(id);
        } catch (Exception e) {
            return null;
        }

        return item;

    }

    public Item deleteItemById(Long id) {
        
        Item item = null;
        try {
            item = getItemById(id);
            jdbcTemplate.update("DELETE FROM " + ITEMS_TABLE + " WHERE itemId=?", id);
        } catch (Exception e) {
            return null;
        }
        return item;

    }

    

    private Item getItemByUUID(String uuid) {

        Item item = new Item();
        try {
            item = jdbcTemplate.queryForObject("SELECT * FROM " + ITEMS_TABLE + " WHERE itemUUID=?",
                   BeanPropertyRowMapper.newInstance(Item.class), uuid);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        return item;
    }






}
