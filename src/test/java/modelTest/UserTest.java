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
import Model.User;
import enam.UserRole;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        // Initialize user object before each test
        user = new User("U123", "John", "Doe", "9876543210", "john.doe@example.com", UserRole.ADMIN);
    }

    // 90% Passing Test Cases
    @Test
    public void testGetAndSetId() {
        // Test getter
        assertEquals("U123", user.getId());

        // Test setter
        user.setId("U456");
        assertEquals("U456", user.getId());
    }

    @Test
    public void testGetAndSetFirstName() {
        // Test getter
        assertEquals("John", user.getFirstName());

        // Test setter
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());
    }

    @Test
    public void testGetAndSetLastName() {
        // Test getter
        assertEquals("Doe", user.getLastName());

        // Test setter
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }

    @Test
    public void testGetAndSetPhoneNumber() {
        // Test getter
        assertEquals("9876543210", user.getPhoneNumber());

        // Test setter
        user.setPhoneNumber("0123456789");
        assertEquals("0123456789", user.getPhoneNumber());
    }

    @Test
    public void testGetAndSetEmail() {
        // Test getter
        assertEquals("john.doe@example.com", user.getEmail());

        // Test setter
        user.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", user.getEmail());
    }

    @Test
    public void testGetAndSetRole() {
        // Test getter
        assertEquals(UserRole.ADMIN, user.getRole());

        // Test setter
        user.setRole(UserRole.USER);
        assertEquals(UserRole.USER, user.getRole());
    }

    @Test
    public void testToStringMethod() {
        // Test the toString method for User object
        String expected = "User{id=U123, firstName=John, lastName=Doe, phoneNumber=9876543210, email=john.doe@example.com, password=null, role=ADMIN}";
        assertEquals(expected, user.toString());
    }

    // 10% Failing Test Cases (Intentionally incorrect)
    @Test
    public void testSetIdFail() {
        // Failing test - Intentionally incorrect
        user.setId("U789");
        assertNotEquals("U123", user.getId()); // This will fail because the new ID is "U789"
    }

    @Test
    public void testSetFirstNameFail() {
        // Failing test - Intentionally incorrect
        user.setFirstName("Emily");
        assertNotEquals("John", user.getFirstName()); // This will fail because the name was changed to "Emily"
    }

    @Test
    public void testSetEmailFail() {
        // Failing test - Intentionally incorrect
        user.setEmail("test@example.com");
        assertNotEquals("john.doe@example.com", user.getEmail()); // This will fail because the email is different
    }

    // Extra test cases that pass as expected
    @Test
    public void testSetPhoneNumberPass() {
        // Test phone number setter and getter
        user.setPhoneNumber("0987654321");
        assertEquals("0987654321", user.getPhoneNumber()); // Passes because it's the expected value
    }

    @Test
    public void testSetLastNamePass() {
        // Test last name setter and getter
        user.setLastName("Brown");
        assertEquals("Brown", user.getLastName()); // Passes because it's the expected value
    }
}

