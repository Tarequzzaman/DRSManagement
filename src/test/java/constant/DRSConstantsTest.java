/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constant;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import constant.AdminViewConstants;
import constant.ReportDisaster;
import constant.Responder;
import constant.UsersConstant;

/**
 *
 * @author tarequzzamankhan
 */



public class DRSConstantsTest {

    // Test for AdminViewConstants
    @Test
    public void testAssignedDepartments() {
        String[] expectedDepartments = {"Fire Department", "Flood Department", "Health Department", "Desert Department"};
        assertArrayEquals(expectedDepartments, AdminViewConstants.ASSIGNED_DEPARTMENTS);
    }

    @Test
    public void testPriorityLevels() {
        String[] expectedPriorityLevels = {"High", "Medium", "Low"};
        assertArrayEquals(expectedPriorityLevels, AdminViewConstants.PRIORITY_LEVELS);
    }

    // Test for ReportDisaster
    @Test
    public void testDisasterTypes() {
        String[] expectedDisasterTypes = {"Bushfire", "Fire", "Flood", "Drought", "Heatwave", "Landslide"};
        assertArrayEquals(expectedDisasterTypes, ReportDisaster.disasterTypes);
    }

    @Test
    public void testStateInitials() {
        String[] expectedStateInitials = {"NSW", "VIC", "QLD", "WA", "SA", "TAS", "NT", "ACT"};
        assertArrayEquals(expectedStateInitials, ReportDisaster.stateInitials);
    }

    @Test
    public void testDisasterCSVFile() {
        assertEquals("disaster.csv", ReportDisaster.CSV_FILE);
    }

    // Test for Responder
    @Test
    public void testGetDepartmentByRole() {
        assertEquals("Fire Department", Responder.getDepartmentByRole("RESPONDER_FIRE_DEPARTMENT"));
        assertEquals("Flood Department", Responder.getDepartmentByRole("RESPONDER_FLOOD_DEPARTMENT"));
        assertEquals("Health Department", Responder.getDepartmentByRole("RESPONDER_HEALTH_DEPARTMENT"));
        assertEquals("Desert Department", Responder.getDepartmentByRole("RESPONDER_DESERT_DEPARTMENT"));
        assertEquals("Unknown Department", Responder.getDepartmentByRole("UNKNOWN_ROLE"));
    }

    // Test for UsersConstant
    @Test
    public void testUserRoles() {
        String[] expectedUserRoles = {
            "ADMIN", 
            "RESPONDER_FIRE_DEPARTMENT", 
            "RESPONDER_FLOOD_DEPARTMENT", 
            "RESPONDER_HEALTH_DEPARTMENT", 
            "RESPONDER_DESERT_DEPARTMENT", 
            "USER"
        };
        assertArrayEquals(expectedUserRoles, UsersConstant.userRole);
    }

    
    
    @Test
    public void testInvalidTypeUserRoles() {
        String[] expectedUserRoles = {
            "ADMIN", 
            "RESPONDER_FIRE_DEPARTMENT", 
            "RESPONDER_FLOOD_DEPARTMENT", 
            "RESPONDER_HEALTH_DEPARTMENT", 
            "RESPONDER_DESERT_DEPARTMENT", 
            "USER",
            "PLAMBER"
        };
        assertArrayEquals(expectedUserRoles, UsersConstant.userRole);
    }
    
    @Test
    public void testUserCSVFile() {
        assertEquals("user.csv", UsersConstant.CSV_FILE);
    }
}

