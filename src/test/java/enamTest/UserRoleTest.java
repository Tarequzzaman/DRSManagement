package enamTest;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tarequzzamankhan
 */


import enam.UserRole;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserRoleTest {

    @Test
    public void testEnumValues() {
        // Test that all enum values exist
        UserRole[] expectedValues = {
            UserRole.ADMIN,
            UserRole.RESPONDER_FIRE_DEPARTMENT,
            UserRole.RESPONDER_FLOOD_DEPARTMENT,
            UserRole.RESPONDER_HEALTH_DEPARTMENT,
            UserRole.RESPONDER_DESERT_DEPARTMENT,
            UserRole.USER
        };
        
        assertArrayEquals(expectedValues, UserRole.values());
    }

    @Test
    public void testValueOf() {
        // Test each enum value is correctly mapped to its string representation
        assertEquals(UserRole.ADMIN, UserRole.valueOf("ADMIN"));
        assertEquals(UserRole.RESPONDER_FIRE_DEPARTMENT, UserRole.valueOf("RESPONDER_FIRE_DEPARTMENT"));
        assertEquals(UserRole.RESPONDER_FLOOD_DEPARTMENT, UserRole.valueOf("RESPONDER_FLOOD_DEPARTMENT"));
        assertEquals(UserRole.RESPONDER_HEALTH_DEPARTMENT, UserRole.valueOf("RESPONDER_HEALTH_DEPARTMENT"));
        assertEquals(UserRole.RESPONDER_DESERT_DEPARTMENT, UserRole.valueOf("RESPONDER_DESERT_DEPARTMENT"));
        assertEquals(UserRole.USER, UserRole.valueOf("USER"));
    }

    @Test
    public void testInvalidValueOf() {
        // Test that an exception is thrown for an invalid value
        assertThrows(IllegalArgumentException.class, () -> {
            UserRole.valueOf("INVALID_ROLE");
        });
    }

    @Test
    public void testDefaultUserRole() {
        // Test that USER is the default role
        assertEquals(UserRole.USER, UserRole.USER);
    }
}
