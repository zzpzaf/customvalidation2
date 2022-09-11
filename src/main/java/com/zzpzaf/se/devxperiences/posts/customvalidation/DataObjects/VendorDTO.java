package com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects;

import javax.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VendorDTO {
    
    @NonNull
    @NotBlank
    private String vendorName;


    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    
}
