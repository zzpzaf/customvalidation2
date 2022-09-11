package com.zzpzaf.se.devxperiences.posts.customvalidation.Repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects.Vendor;
import com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects.VendorDTO;


@Repository
public class VendorsRepo {
   
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String VENDORS_TABLE = "vendors";
    private final String VENDORCATEGORIES_TABLE = "vendorcategories";

    public List<Vendor> getAllVendors() {

        List<Vendor> vendorsList = new ArrayList<>();
        try {
            vendorsList = jdbcTemplate.query("SELECT * FROM " + VENDORS_TABLE, 
            BeanPropertyRowMapper.newInstance(Vendor.class));
        } catch (Exception e) {
            return null;
        }
        return vendorsList;
    }

    public Vendor getVendorById(Long id) {

        Vendor vendor = new Vendor();
        try {
            vendor = jdbcTemplate.queryForObject("SELECT * FROM " + VENDORS_TABLE + " WHERE vendorId=?",
                   BeanPropertyRowMapper.newInstance(Vendor.class), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        return vendor;
    }


    public Vendor createVendor(VendorDTO dto) {

        Vendor vendor = new Vendor();

        vendor.setVendorName(dto.getVendorName());
        vendor.setVendorUUID(UUID.randomUUID().toString());

        int status = 0;
        try {
            status = jdbcTemplate.update("INSERT INTO " + VENDORS_TABLE + " (" +
                "vendorName, " +
                "vendorUUID" +
                ")" +
                " VALUES(?,?)",
                new Object[]{vendor.getVendorName(),
                             vendor.getVendorUUID(),
                });            

        } catch (Exception e) {
            return null;
        }

        Vendor createdVendor = new Vendor();
        if(status != 0){
            createdVendor = getVendorByUUID(vendor.getVendorUUID());
        }
        return createdVendor;

    }



    public Vendor updateVendorById(Long id, VendorDTO dto) {
        
        Vendor vendor = null;

        vendor = getVendorById(id);
        vendor.setVendorName(dto.getVendorName());

        try {
            jdbcTemplate.update("UPDATE " + VENDORS_TABLE + " SET " + 
                "vendorName=? " +
                "WHERE vendorId=?",
                new Object[]{vendor.getVendorName(),
                    vendor.getVendorId()
                });   
            vendor = getVendorById(id);
        } catch (Exception e) {
            return null;
        }

        return vendor;

    }





    public Vendor deleteVendorById(Long id) {
        
        Vendor vendor = null;
        try {
            vendor = getVendorById(id);
            jdbcTemplate.update("DELETE FROM " + VENDORS_TABLE + " WHERE vendorId=?", id);
        } catch (Exception e) {
            return null;
        }
        return vendor;

    }


    private Vendor getVendorByUUID(String uuid) {

        Vendor vendor = new Vendor();
        try {
            vendor = jdbcTemplate.queryForObject("SELECT * FROM " + VENDORS_TABLE + " WHERE vendorUUID=?",
                   BeanPropertyRowMapper.newInstance(Vendor.class), uuid);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        return vendor;
    }


    public List<Integer> getVendorAllowedCategories(Integer vendorId) throws SQLException {
        

        List<Integer> categoriesIdsList = new ArrayList<>();
        //List<Map<String, Object>> rows;
        
        String queryString;



        queryString = "SELECT CategoryId FROM " + 
                      VENDORCATEGORIES_TABLE + 
                      " WHERE vendorId = ?" + 
                      " AND isAllowed = 1";
        //System.out.println(vendorId + "  " + VENDORCATEGORIES_TABLE);
        try {
            categoriesIdsList = (List<Integer>) jdbcTemplate.queryForList(queryString, Integer.class, vendorId);
            //System.out.println(categoriesIdsList);
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }

        return categoriesIdsList;
    }

    public boolean isVendorCategoryAllowed(Integer vendorId, Integer categoryId) throws SQLException {
        return getVendorAllowedCategories(vendorId).contains(categoryId);
    }


}
