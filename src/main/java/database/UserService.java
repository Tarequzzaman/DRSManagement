/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author tarequzzamankhan
 */
import Model.User;
import constant.UsersConstant;
import enam.UserRole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserService {

    public boolean saveUser(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(UsersConstant.CSV_FILE, true))) {
            writer.println(user.getId() + "," + user.getFirstName() + "," + user.getLastName() + ","
                    + user.getPhoneNumber() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getRole());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Generate a unique ID by checking the existing IDs
    public String generateNextId() {
        Set<String> existingIds = new HashSet<>();
        String lastId = "0";

        // Read the existing IDs from the CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(UsersConstant.CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                existingIds.add(fields[0]); // Add ID to the set
                lastId = fields[0]; // Keep track of the last ID
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate the next ID
        int nextId = Integer.parseInt(lastId) + 1;
        String nextIdStr = String.valueOf(nextId);

        // Ensure the generated ID is unique
        while (existingIds.contains(nextIdStr)) {
            nextId++;
            nextIdStr = String.valueOf(nextId);
        }

        return nextIdStr;
    }

    // Find a user by their email address
    public User findUserByEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(UsersConstant.CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[4].equals(email)) { // Assume email is the 5th field
                    User user = new User(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], UserRole.valueOf(fields[6]));
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null ;// Return empty if user not found
    }
}
