package com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "itemUUID" })
public class Item {
    
    private int itemId;
    private String itemName;
    private int itemCategoryId;
    private int itemVendorId;
    private int itemModelYear;
    private float itemListPrice;
    private String itemUUID; 

    public Item() {};

    public Item(int itemId, String itemName, int itemCategoryId, int itemVendorId, int itemModelYear, float itemListPrice, String itemUUID) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCategoryId = itemCategoryId;
        this.itemVendorId = itemVendorId;
        this.itemModelYear = itemModelYear;
        this.itemListPrice = itemListPrice;
        this.itemUUID = itemUUID;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(int itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public int getItemVendorId() {
        return itemVendorId;
    }

    public void setItemVendorId(int itemVendorId) {
        this.itemVendorId = itemVendorId;
    }

    public int getItemModelYear() {
        return itemModelYear;
    }

    public void setItemModelYear(int itemModelYear) {
        this.itemModelYear = itemModelYear;
    }

    public float getItemListPrice() {
        return itemListPrice;
    }

    public void setItemListPrice(float itemListPrice) {
        this.itemListPrice = itemListPrice;
    }


    public String getitemUUID() {
        return itemUUID;
    }

    public void setitemUUID(String itemUUID) {
        this.itemUUID = itemUUID;
    }

    @Override
    public String toString() {
        return "Item [itemCategoryId=" + itemCategoryId + ", itemId=" + itemId + ", itemListPrice=" + itemListPrice
                + ", itemModelYear=" + itemModelYear + ", itemName=" + itemName + ", itemVendorId=" + itemVendorId
                + ", itemUUID=" + itemUUID + "]";
    }








    

}
