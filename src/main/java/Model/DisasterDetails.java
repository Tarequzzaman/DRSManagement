/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tarequzzamankhan
 */


public class DisasterDetails {
    private int disasterId;
    private String disasterTitle;
    private String detail;
    private String phone;
    private String unit;
    private String houseNumber;
    private String suburb;
    private String state;

    public DisasterDetails(int disasterId, String disasterTitle, String detail, String phone, String unit, String houseNumber, String suburb, String state) {
        this.disasterId = disasterId;
        this.disasterTitle = disasterTitle;
        this.detail = detail;
        this.phone = phone;
        this.unit = unit;
        this.houseNumber = houseNumber;
        this.suburb = suburb;
        this.state = state;
    }

    public int getDisasterId() {
        return disasterId;
    }

    public String getDisasterTitle() {
        return disasterTitle;
    }

    public String getDetail() {
        return detail;
    }

    public String getPhone() {
        return phone;
    }

    public String getUnit() {
        return unit;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getState() {
        return state;
    }

    public String getLocation() {
        return toString();
    }

    @Override
    public String toString() {
        return unit + " " + houseNumber + ", " + suburb;
    }
}

