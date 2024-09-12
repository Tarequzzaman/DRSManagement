package databaseTest;

import database.DisasterService;
import Model.DisasterDetails;
import constant.ReportDisaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DisasterServiceTest {

    private static final String TEST_CSV_FILE = "test_disasters.csv";
    private DisasterService disasterService;
    private DisasterDetails disaster1;
    private DisasterDetails disaster2;

    @BeforeEach
    public void setUp() throws IOException {
        // Clear the test CSV file or create a new one before each test
        Files.deleteIfExists(Paths.get(ReportDisaster.CSV_FILE));
        Files.deleteIfExists(Paths.get(TEST_CSV_FILE));

        // Initialize DisasterService
        this.disasterService = new DisasterService();

        // Initialize global test data
        disaster1 = new DisasterDetails(1, "Fire", "Fire in suburb", "1234567890",
                "Unit1", "House10", "SuburbX", "StateY", "User1", "pending", "high", "Fire Department");
        disaster2 = new DisasterDetails(2, "Flood", "Flood in suburb", "0987654321",
                "Unit2", "House20", "SuburbY", "StateZ", "User2", "pending", "low", "Flood Department");

        // Save test data globally for all tests
        disasterService.saveDisaster(disaster1);
        disasterService.saveDisaster(disaster2);
    }

    @Test
    public void testSaveDisaster() {
        // Test if disaster1 is saved correctly
        List<DisasterDetails> disastersByUser1 = disasterService.getDisastersByUser("User1");
        assertEquals(1, disastersByUser1.size());
        DisasterDetails savedDisaster = disastersByUser1.get(0);
        assertEquals(1, savedDisaster.getDisasterId());
        assertEquals("Fire", savedDisaster.getDisasterTitle());
        assertEquals("Fire in suburb", savedDisaster.getDetail());
    }

    @Test
    public void testGenerateNextId() {
        // Test if the next ID is generated correctly after inserting disaster1 and disaster2
        int nextId = disasterService.generateNextId();
        assertEquals(3, nextId);
    }

    @Test
    public void testGetDisastersByUser() {
        // Test retrieving disasters for User1
        List<DisasterDetails> disastersByUser1 = disasterService.getDisastersByUser("User1");
        assertEquals(1, disastersByUser1.size());
        assertEquals("Fire", disastersByUser1.get(0).getDisasterTitle());

        // Test retrieving disasters for User2
        List<DisasterDetails> disastersByUser2 = disasterService.getDisastersByUser("User2");
        assertEquals(1, disastersByUser2.size());
        assertEquals("Flood", disastersByUser2.get(0).getDisasterTitle());
    }

    @Test
    public void testUpdateDisaster() {
        // Update the first disaster
        boolean isUpdated = disasterService.updateDisaster(1, "Health Department", "low", "dispatched");
        assertTrue(isUpdated);

        // Verify the updated disaster
        DisasterDetails updatedDisaster = disasterService.getDisasterById(1);
        assertNotNull(updatedDisaster);
        assertEquals("dispatched", updatedDisaster.getStatus());
        assertEquals("low", updatedDisaster.getPriority());
        assertEquals("Health Department", updatedDisaster.getDesignatedDepartment());
    }

    @Test
    public void testGetDisasterById() {
        // Retrieve the disaster by ID
        DisasterDetails retrievedDisaster = disasterService.getDisasterById(1);
        assertNotNull(retrievedDisaster);
        assertEquals(1, retrievedDisaster.getDisasterId());
        assertEquals("Fire", retrievedDisaster.getDisasterTitle());
    }
}
