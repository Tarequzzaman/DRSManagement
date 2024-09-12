/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTest;

/**
 *
 * @author tarequzzamankhan
 */


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.DisasterDetails;

public class DisasterDetailsTest {

    private DisasterDetails disaster;

    @BeforeEach
    public void setUp() {
        // Initialize DisasterDetails object before each test
        disaster = new DisasterDetails(1, "Flood", "Severe Flood", "1234567890", "12A", "123", "Downtown", "NSW", "John Doe", "pending", "high", "Fire Department");
    }

    // 90% Passing Test Cases
    @Test
    public void testGetAndSetDisasterId() {
        // Test getter
        assertEquals(1, disaster.getDisasterId());

        // Test setter
        disaster.setDisasterId(2);
        assertEquals(2, disaster.getDisasterId());
    }

    @Test
    public void testGetAndSetDisasterTitle() {
        // Test getter
        assertEquals("Flood", disaster.getDisasterTitle());

        // Test setter
        disaster.setDisasterTitle("Wildfire");
        assertEquals("Wildfire", disaster.getDisasterTitle());
    }

    @Test
    public void testGetAndSetDetail() {
        // Test getter
        assertEquals("Severe Flood", disaster.getDetail());

        // Test setter
        disaster.setDetail("Massive Wildfire");
        assertEquals("Massive Wildfire", disaster.getDetail());
    }

    @Test
    public void testGetAndSetPhone() {
        // Test getter
        assertEquals("1234567890", disaster.getPhone());

        // Test setter
        disaster.setPhone("9876543210");
        assertEquals("9876543210", disaster.getPhone());
    }

    @Test
    public void testGetAndSetUnit() {
        // Test getter
        assertEquals("12A", disaster.getUnit());

        // Test setter
        disaster.setUnit("15B");
        assertEquals("15B", disaster.getUnit());
    }

    @Test
    public void testGetAndSetHouseNumber() {
        // Test getter
        assertEquals("123", disaster.getHouseNumber());

        // Test setter
        disaster.setHouseNumber("456");
        assertEquals("456", disaster.getHouseNumber());
    }

    @Test
    public void testGetAndSetSuburb() {
        // Test getter
        assertEquals("Downtown", disaster.getSuburb());

        // Test setter
        disaster.setSuburb("Uptown");
        assertEquals("Uptown", disaster.getSuburb());
    }

    @Test
    public void testGetAndSetState() {
        // Test getter
        assertEquals("NSW", disaster.getState());

        // Test setter
        disaster.setState("VIC");
        assertEquals("VIC", disaster.getState());
    }

    @Test
    public void testGetAndSetSubmittedBy() {
        // Test getter
        assertEquals("John Doe", disaster.getSubmittedBy());

        // Test setter
        disaster.setSubmittedBy("Jane Doe");
        assertEquals("Jane Doe", disaster.getSubmittedBy());
    }

    @Test
    public void testGetAndSetStatus() {
        // Test getter
        assertEquals("pending", disaster.getStatus());

        // Test setter
        disaster.setStatus("ongoing");
        assertEquals("ongoing", disaster.getStatus());
    }

    @Test
    public void testGetAndSetPriority() {
        // Test getter
        assertEquals("high", disaster.getPriority());

        // Test setter
        disaster.setPriority("medium");
        assertEquals("medium", disaster.getPriority());
    }

    @Test
    public void testGetAndSetDesignatedDepartment() {
        // Test getter
        assertEquals("Fire Department", disaster.getDesignatedDepartment());

        // Test setter
        disaster.setDesignatedDepartment("Health Department");
        assertEquals("Health Department", disaster.getDesignatedDepartment());
    }

    // 10% Failing Test Cases (Intentionally incorrect)
    @Test
    public void testSetDisasterIdFail() {
        // Failing test - Intentionally incorrect
        disaster.setDisasterId(2);
        assertEquals(1, disaster.getDisasterId()); // This will fail because the ID was changed to 2
    }

    @Test
    public void testSetDisasterTitleFail() {
        // Failing test - Intentionally incorrect
        disaster.setDisasterTitle("Landslide");
        assertEquals("Flood", disaster.getDisasterTitle()); // This will fail because the title was changed to "Landslide"
    }

    @Test
    public void testSetPhoneFail() {
        // Failing test - Intentionally incorrect
        disaster.setPhone("0112233445");
        assertEquals("0112233445", disaster.getPhone()); // This will fail because the phone number was changed
    }

    // Extra test cases that pass as expected
    @Test
    public void testSetUnitPass() {
        // Test setter for Unit
        disaster.setUnit("25C");
        assertEquals("25C", disaster.getUnit()); // Passes because it's the expected value
    }

    @Test
    public void testSetHouseNumberPass() {
        // Test setter for House Number
        disaster.setHouseNumber("789");
        assertEquals("789", disaster.getHouseNumber()); // Passes because it's the expected value
    }
}

