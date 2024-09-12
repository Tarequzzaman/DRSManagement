/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseTest;

/**
 *
 * @author tarequzzamankhan
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import Model.User;
import enam.UserRole;
import database.UserService;
import constant.UsersConstant;

import java.io.*;
import java.nio.file.*;

public class UserServiceTest {

    private UserService userService;
    private Path csvFilePath;

    @BeforeEach
    public void setUp() throws Exception {
        userService = new UserService();
        
        // Get the default file path from UsersConstant
        csvFilePath = Paths.get(UsersConstant.CSV_FILE);
        
        // Write some initial data into the file
        Files.write(csvFilePath, "1,Admin,User,0000000000,admin@drs.com,admin123,ADMIN\n".getBytes());
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Clean up the file after each test
        Files.deleteIfExists(csvFilePath);
    }

    @Test
    public void testFindUserByEmail() throws Exception {
        // Find user by email
        User user = userService.findUserByEmail("admin@drs.com");

        // Verify the user data
        assertNotNull(user);
        assertEquals("Admin", user.getFirstName());
        assertEquals("User", user.getLastName());
        assertEquals("admin@drs.com", user.getEmail());
    }

    @Test
    public void testSaveUser() throws Exception {
        // Create a new user
        User newUser = new User("2", "John", "Doe", "1234567890", "john.doe@example.com", userService.hashPassword("password"), UserRole.USER);
        
        // Save the user
        boolean success = userService.saveUser(newUser);

        // Verify the user was saved successfully
        assertTrue(success);

        // Check if the new user is in the file
        String fileContent = Files.readString(csvFilePath);
        assertTrue(fileContent.contains("John,Doe,1234567890,john.doe@example.com"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Create the updated user
        User updatedUser = new User("1", "Jane", "Doe", "9876543210", "admin@drs.com", userService.hashPassword("admin123"), UserRole.ADMIN);
        
        // Update the user
        boolean success = userService.updateUser(updatedUser);

        // Verify the update was successful
        assertTrue(success);

        // Check the updated file content
        String fileContent = Files.readString(csvFilePath);
        assertTrue(fileContent.contains("Jane,Doe,9876543210,admin@drs.com"));
    }

    @Test
    public void testGenerateNextId() throws Exception {
        // Generate the next ID
        String nextId = userService.generateNextId();

        // Verify that the next ID is "2" (since we have one user in the file)
        assertEquals("2", nextId);
    }
}
