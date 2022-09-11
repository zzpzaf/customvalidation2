package com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Range;

import com.zzpzaf.se.devxperiences.posts.customvalidation.Validators.DBMasterDetails;

//import com.zzpzaf.se.devxperiences.posts.customvalidation.Validators.OneOfIntegers;


@DBMasterDetails.List({ 
    @DBMasterDetails( 
    fieldName1 = "itemVendorId", 
    fieldName2 = "itemCategoryId", 
    message = "Invalid itemVendorId and/or itemCategoryId!" )
    }) 
public class ItemDTO {
    
    @NotNull(message = "The item Name is required!")
    private String itemName;

    private int itemCategoryId;

    //@OneOfIntegers(Values={2, 17, 33, 5, 28}, message = "Invalid Vendor ID. It should be one of: {Values}")
    private int itemVendorId;

    @Range(min=2020, max=2022, message = "The Item Year of Model should be between 2020 and 2022!")
    private int itemModelYear;
    
    @Positive
    @Digits(integer=6, fraction=2, message = "The Item Price should be a number with integer part of up to 6 digits and fractional part of up to 2 decimals!" )
    private float itemListPrice;

   
    public ItemDTO(String itemName, int itemCategoryId, int itemVendorId, int itemModelYear, float itemListPrice) {

        this.itemName = itemName;
        this.itemCategoryId = itemCategoryId;
        this.itemVendorId = itemVendorId;
        this.itemModelYear = itemModelYear;
        this.itemListPrice = itemListPrice;
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


    @Override
    public String toString() {
        return "ItemDTO [itemCategoryId=" + itemCategoryId + ", itemListPrice=" + itemListPrice + ", itemModelYear="
                + itemModelYear + ", itemName=" + itemName + ", itemVendorId=" + itemVendorId + "]";
    }

   

}
