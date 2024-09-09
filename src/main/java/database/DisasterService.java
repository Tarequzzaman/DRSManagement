/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author tarequzzamankhan
 */
import Model.DisasterDetails;
import constant.ReportDisaster;
import constant.Responder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisasterService {

    private FileService fileService;

    public DisasterService() {
        this.fileService = new FileService();
        initializeDatabase();
    }

    private void initializeDatabase() {
        String headers = "disasterId,disasterTitle,detail,phone,unit,houseNumber,suburb,state,submittedBy,status,priority,designatedDepartment";
        fileService.createFileIfNotExists(ReportDisaster.CSV_FILE, headers);
    }

    public boolean saveDisaster(DisasterDetails disasterDetails) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ReportDisaster.CSV_FILE, true))) {
            writer.println(disasterDetails.getDisasterId() + "," + disasterDetails.getDisasterTitle() + "," + disasterDetails.getDetail() + ","
                    + disasterDetails.getPhone() + "," + disasterDetails.getUnit() + "," + disasterDetails.getHouseNumber() + ","
                    + disasterDetails.getSuburb() + "," + disasterDetails.getState() + "," + disasterDetails.getSubmittedBy() + ","
                    + disasterDetails.getStatus() + "," + disasterDetails.getPriority() + "," + disasterDetails.getDesignatedDepartment());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int generateNextId() {
        int lastId = 0;

        // Read the existing IDs from the CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(ReportDisaster.CSV_FILE))) {
            String line;
            boolean isFirstLine = true; // Flag to skip the header row
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip the header row
                    continue;
                }
                String[] fields = line.split(",");
                lastId = Integer.parseInt(fields[0]); // Get the last ID
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return lastId + 1;
    }

    public List<DisasterDetails> getDisastersByUser(String userId) {
        List<DisasterDetails> disasters = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ReportDisaster.CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);  // -1 ensures it keeps empty fields

                if (fields.length == 12 && fields[8].equals(userId)) {
                    DisasterDetails disaster = new DisasterDetails(
                            Integer.parseInt(fields[0]), // disasterId
                            fields[1], // disasterTitle
                            fields[2], // detail
                            fields[3], // phone
                            fields[4], // unit
                            fields[5], // houseNumber
                            fields[6], // suburb
                            fields[7], // state
                            fields[8], // submittedBy
                            fields[9], // status
                            fields[10], // priority
                            fields[11] // designatedDepartment
                    );
                    disasters.add(disaster);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return disasters;
    }

    public List<DisasterDetails> getPendingDisasters() {
        List<DisasterDetails> pendingDisasters = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ReportDisaster.CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);  // -1 ensures it keeps empty fields

                if (fields.length == 12 && "pending".equalsIgnoreCase(fields[9])) {
                    DisasterDetails disaster = new DisasterDetails(
                            Integer.parseInt(fields[0]), // disasterId
                            fields[1], // disasterTitle
                            fields[2], // detail
                            fields[3], // phone
                            fields[4], // unit
                            fields[5], // houseNumber
                            fields[6], // suburb
                            fields[7], // state
                            fields[8], // submittedBy
                            fields[9], // status
                            fields[10], // priority
                            fields[11] // designatedDepartment
                    );
                    pendingDisasters.add(disaster);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pendingDisasters;
    }

    public boolean updateDisaster(int disasterId, String assignedGroup, String priority, String status) {
        StringBuilder updatedData = new StringBuilder();
        boolean isUpdated = false;

        // Read the existing data and update the required row
        try (BufferedReader reader = new BufferedReader(new FileReader(ReportDisaster.CSV_FILE))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    // Add the header to the updated data
                    updatedData.append(line).append("\n");
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",", -1);

                if (Integer.parseInt(fields[0]) == disasterId) {
                    // Update the fields for the matching disaster ID
                    fields[9] = status;
                    fields[10] = priority;
                    fields[11] = assignedGroup;
                    isUpdated = true;
                }

                // Append the updated or original line to the new data
                updatedData.append(String.join(",", fields)).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Write the updated data back to the CSV file
        if (isUpdated) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(ReportDisaster.CSV_FILE, false))) {
                writer.print(updatedData.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return isUpdated;
    }

    public DisasterDetails getDisasterById(int disasterId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ReportDisaster.CSV_FILE))) {
            String line;
            boolean isFirstLine = true;  // To skip the header
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;  // Skip header line
                }

                String[] fields = line.split(",", -1);

                // Ensure that the line has the correct number of fields
                if (fields.length == 12 && Integer.parseInt(fields[0]) == disasterId) {
                    return new DisasterDetails(
                            Integer.parseInt(fields[0]), // disasterId
                            fields[1], // disasterTitle
                            fields[2], // detail
                            fields[3], // phone
                            fields[4], // unit
                            fields[5], // houseNumber
                            fields[6], // suburb
                            fields[7], // state
                            fields[8], // submittedBy
                            fields[9], // status
                            fields[10], // priority
                            fields[11] // designatedDepartment
                    );
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return null;  // Return null if no matching disaster is found
    }

    public List<DisasterDetails> getAssessedDisasters() {
        List<DisasterDetails> assessedDisasters = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ReportDisaster.CSV_FILE))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                // Skip the header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",", -1);

                try {
                    int disasterId = Integer.parseInt(fields[0]);
                    if (!fields[9].equalsIgnoreCase("pending")) {
                        DisasterDetails disaster = new DisasterDetails(
                                disasterId, // disasterId
                                fields[1], // disasterTitle
                                fields[2], // detail
                                fields[3], // phone
                                fields[4], // unit
                                fields[5], // houseNumber
                                fields[6], // suburb
                                fields[7], // state
                                fields[8], // submittedBy
                                fields[9], // status
                                fields[10], // priority
                                fields[11] // designatedDepartment
                        );
                        assessedDisasters.add(disaster);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid disasterId: " + fields[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return assessedDisasters;
    }

   

    public List<DisasterDetails> getDisastersByStatusAndRole(String status, String userRole) {
                       

        List<DisasterDetails> disasters = new ArrayList<>();

        String department = Responder.getDepartmentByRole(userRole);
        try (BufferedReader reader = new BufferedReader(new FileReader(ReportDisaster.CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);  // -1 ensures it keeps empty fields

                // Check if the status matches and the designated department (role) matches
                if (fields.length == 12 && status.equalsIgnoreCase(fields[9]) && department.equalsIgnoreCase(fields[11])) {
                    DisasterDetails disaster = new DisasterDetails(
                            Integer.parseInt(fields[0]), // disasterId
                            fields[1], // disasterTitle
                            fields[2], // detail
                            fields[3], // phone
                            fields[4], // unit
                            fields[5], // houseNumber
                            fields[6], // suburb
                            fields[7], // state
                            fields[8], // submittedBy
                            fields[9], // status
                            fields[10], // priority
                            fields[11] // designatedDepartment
                    );
                    disasters.add(disaster);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return disasters;
    }
    
public void updateDisasterStatus(int disasterId, String newStatus) {
    List<DisasterDetails> allDisasters = new ArrayList<>();

    // Create a backup of the original file before updating
    try {
        Files.copy(Paths.get(ReportDisaster.CSV_FILE), Paths.get(ReportDisaster.CSV_FILE + ".bak"), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Failed to create backup. Aborting update to prevent data loss.");
        return;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(ReportDisaster.CSV_FILE))) {
        String line;
        boolean skipHeader = true;
        while ((line = reader.readLine()) != null) {
            if (skipHeader) {
                skipHeader = false;
                continue;
            }
            String[] fields = line.split(",", -1);
            if (fields.length == 12) {
                try {
                    int id = Integer.parseInt(fields[0]);
                    DisasterDetails disaster = new DisasterDetails(
                            id, // disasterId
                            fields[1], // disasterTitle
                            fields[2], // detail
                            fields[3], // phone
                            fields[4], // unit
                            fields[5], // houseNumber
                            fields[6], // suburb
                            fields[7], // state
                            fields[8], // submittedBy
                            fields[9], // status
                            fields[10], // priority
                            fields[11]  // designatedDepartment
                    );
                    if (id == disasterId) {
                        disaster.setStatus(newStatus); // Update status
                    }
                    allDisasters.add(disaster);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid line: " + line);
                }
            } else {
                System.err.println("Skipping line with incorrect format: " + line);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Write the updated list back to the CSV file
    try (PrintWriter writer = new PrintWriter(new FileWriter(ReportDisaster.CSV_FILE, false))) {
        writer.println("disasterId,disasterTitle,detail,phone,unit,houseNumber,suburb,state,submittedBy,status,priority,designatedDepartment");
        for (DisasterDetails disaster : allDisasters) {
            writer.println(disaster.getDisasterId() + "," + disaster.getDisasterTitle() + "," + disaster.getDetail() + ","
                    + disaster.getPhone() + "," + disaster.getUnit() + "," + disaster.getHouseNumber() + ","
                    + disaster.getSuburb() + "," + disaster.getState() + "," + disaster.getSubmittedBy() + ","
                    + disaster.getStatus() + "," + disaster.getPriority() + "," + disaster.getDesignatedDepartment());
        }
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Failed to update CSV file. Restoring from backup.");
        restoreBackup();
    }
}

private void restoreBackup() {
    try {
        Files.copy(Paths.get(ReportDisaster.CSV_FILE + ".bak"), Paths.get(ReportDisaster.CSV_FILE), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Data restored from backup.");
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Failed to restore data from backup.");
    }
}

}
