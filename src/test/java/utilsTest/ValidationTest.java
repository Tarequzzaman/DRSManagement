/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilsTest;

import utils.ValidationUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tarequzzamankhan
 */
public class ValidationTest {

    @Test
    public void testValidEmail() {
        // Test valid email addresses
        assertTrue(ValidationUtil.isValidEmail("test@example.com"));
        assertTrue(ValidationUtil.isValidEmail("user.name@domain.co.uk"));
        assertTrue(ValidationUtil.isValidEmail("user+name@sub.domain.com"));
    }

    @Test
    public void testInvalidEmail() {
        // Test invalid email addresses
        assertFalse(ValidationUtil.isValidEmail("plainaddress"));
        assertFalse(ValidationUtil.isValidEmail("test@.com")); // missing domain name
        assertFalse(ValidationUtil.isValidEmail("test@domain")); // no top-level domain
        assertFalse(ValidationUtil.isValidEmail("user@domain..com")); // consecutive dots
        assertFalse(ValidationUtil.isValidEmail("")); // empty email
        assertFalse(ValidationUtil.isValidEmail(null)); // null email
    }

    @Test
    public void testValidPhoneNumber() {
        // Test valid Australian phone numbers (starts with 04 and 10 digits)
        assertTrue(ValidationUtil.isValidPhoneNumber("0412345678"));
        assertTrue(ValidationUtil.isValidPhoneNumber("0498765432"));
    }

    @Test
    public void testInvalidPhoneNumber() {
        // Test invalid phone numbers
        assertFalse(ValidationUtil.isValidPhoneNumber("0312345678")); // wrong prefix
        assertFalse(ValidationUtil.isValidPhoneNumber("041234567"));  // less than 10 digits
        assertFalse(ValidationUtil.isValidPhoneNumber("041234567890")); // more than 10 digits
        assertFalse(ValidationUtil.isValidPhoneNumber("041234abcd"));  // contains letters
        assertFalse(ValidationUtil.isValidPhoneNumber("")); // empty phone number
        assertFalse(ValidationUtil.isValidPhoneNumber(null)); // null phone number
    }

}
