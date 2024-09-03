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
    private String submittedBy;
    private String status;
    private String priority;
    private String designatedDepartment;

    public DisasterDetails(int disasterId, String disasterTitle, String detail, String phone, String unit, String houseNumber, String suburb, String state, String submittedBy, String status, String priority, String designatedDepartment) {
        this.disasterId = disasterId;
        this.disasterTitle = disasterTitle;
        this.detail = detail;
        this.phone = phone;
        this.unit = unit;
        this.houseNumber = houseNumber;
        this.suburb = suburb;
        this.state = state;
        this.submittedBy = submittedBy;
        this.status = status;
        this.priority = priority;
        this.designatedDepartment = designatedDepartment;
    }

    public int getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(int disasterId) {
        this.disasterId = disasterId;
    }

    public String getDisasterTitle() {
        return disasterTitle;
    }

    public void setDisasterTitle(String disasterTitle) {
        this.disasterTitle = disasterTitle;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDesignatedDepartment() {
        return designatedDepartment;
    }

    public void setDesignatedDepartment(String designatedDepartment) {
        this.designatedDepartment = designatedDepartment;
    }

    // Getters and setters for all fields...

    public String getLocation() {
        return unit + " " + houseNumber + ", " + suburb;
    }
    
    
    public String toCSVString() {
        return String.join(",", 
            String.valueOf(disasterId),
            disasterTitle,
            detail,
            phone,
            unit,
            houseNumber,
            suburb,
            state,
            submittedBy,
            status,
            priority,
            designatedDepartment
        );
    }
}
