package com.fyp.quickrepair.Model;

public class Values {
    String SEARCH_CATEGORY_TAG;
    String RENT_CATEGORY,OTHER_CATEGORY,OIL_CATEGORY,MECHANIC_CATEGORY,CARWASH_CATEGORY;

    public String getSEARCH_CATEGORY_TAG() {
        return SEARCH_CATEGORY_TAG;
    }

    public String getRENT_CATEGORY() {
        return RENT_CATEGORY;
    }

    public String getOTHER_CATEGORY() {
        return OTHER_CATEGORY;
    }

    public String getOIL_CATEGORY() {
        return OIL_CATEGORY;
    }

    public String getMECHANIC_CATEGORY() {
        return MECHANIC_CATEGORY;
    }

    public String getCARWASH_CATEGORY() {
        return CARWASH_CATEGORY;
    }

    public Values() {

        this.SEARCH_CATEGORY_TAG = "CATEGORY";
        this.RENT_CATEGORY = "Rent A Car";
        this.OTHER_CATEGORY = "Others";
        this.OIL_CATEGORY = "Oil Change";
        this.MECHANIC_CATEGORY = "Mechanic";
        this.CARWASH_CATEGORY = "Car Wash";
    }
}
