package com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "vendorUUID" })
public class Vendor {
    
    private int vendorId;  
    private String vendorName;
    private String vendorUUID;
    
    public Vendor() {}; 
       
    public Vendor(int vendorId, String vendorName) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorUUID() {
        return vendorUUID;
    }

    public void setVendorUUID(String vendorUUID) {
        this.vendorUUID = vendorUUID;
    }

    @Override
    public String toString() {
        return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorUUID=" + vendorUUID + "]";
    }




}
