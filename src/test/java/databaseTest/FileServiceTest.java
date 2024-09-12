/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseTest;

/**
 *
 * @author tarequzzamankhan
 */


import database.FileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTest {

    private static final String TEST_FILE_NAME = "test_file.csv";
    private static final String TEST_HEADERS = "column1,column2,column3";
    private FileService fileService;

    @BeforeEach
    public void setUp() {
        // Initialize the FileService object
        fileService = new FileService();
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up by deleting the test file after each test
        Files.deleteIfExists(Paths.get(TEST_FILE_NAME));
    }

    @Test
    public void testCreateFileIfNotExists_FileDoesNotExist() {
        // Verify that the file does not exist before creation
        assertFalse(fileService.fileExists(TEST_FILE_NAME));

        // Create the file
        boolean isFileCreated = fileService.createFileIfNotExists(TEST_FILE_NAME, TEST_HEADERS);
        assertTrue(isFileCreated);

        // Verify that the file now exists
        assertTrue(fileService.fileExists(TEST_FILE_NAME));

        // Verify that the file contains the correct headers
        try {
            String content = Files.readString(Paths.get(TEST_FILE_NAME));
            assertEquals(TEST_HEADERS, content.trim());
        } catch (IOException e) {
            fail("File could not be read");
        }
    }

    @Test
    public void testCreateFileIfNotExists_FileAlreadyExists() throws IOException {
        // Create the file manually
        Files.createFile(Paths.get(TEST_FILE_NAME));

        // Try to create the file again using the method, should return false
        boolean isFileCreated = fileService.createFileIfNotExists(TEST_FILE_NAME, TEST_HEADERS);
        assertFalse(isFileCreated);

        // Verify that the file still exists
        assertTrue(fileService.fileExists(TEST_FILE_NAME));
    }

    @Test
    public void testFileExists_FileDoesNotExist() {
        // Verify that the file does not exist
        assertFalse(fileService.fileExists(TEST_FILE_NAME));
    }

    @Test
    public void testFileExists_FileExists() throws IOException {
        // Create the file manually
        Files.createFile(Paths.get(TEST_FILE_NAME));

        // Verify that the file exists
        assertTrue(fileService.fileExists(TEST_FILE_NAME));
    }
}
