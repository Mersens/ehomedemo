package com.zzu.ehome.ehome.entity;

/**
 * Created by Mersens on 2016/10/31.
 */

public class HosptialBean {

    private String HospitalID;
    private String HospitalName;
    private String Description;
    private String City;

    public String getCity() {
        return City;
    }
    public void setCity(String city) {
        City = city;
    }

    public String getHospitalID() {
        return HospitalID;
    }

    public void setHospitalID(String hospitalID) {
        HospitalID = hospitalID;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
