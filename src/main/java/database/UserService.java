package database;

import Model.User;
import constant.UsersConstant;
import enam.UserRole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private FileService fileService;

    public UserService() {
        this.fileService = new FileService();
        initializeDatabase();
    }

    private void initializeDatabase() {
        String headers = "id,first_name,last_name,phone_number,email,password,role";
        boolean created = fileService.createFileIfNotExists(UsersConstant.CSV_FILE, headers);

        // Check if the admin user exists using findUserByEmail
        if (created || findUserByEmail("admin@drs.com") == null) {
            insertAdminUser();
        }
    }

    private void insertAdminUser() {
        String hashedPassword = hashPassword("admin123");
        User admin = new User("1", "Admin", "User", "0000000000", "admin@drs.com", hashedPassword, UserRole.ADMIN);
        saveUser(admin);
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

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
        String lastId = "0";

        // Read the existing IDs from the CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(UsersConstant.CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                lastId = fields[0]; // Keep track of the last ID
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate the next ID
        int nextId = Integer.parseInt(lastId) + 1;
        return String.valueOf(nextId);
    }

    // Find a user by their email address
    public User findUserByEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(UsersConstant.CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[4].equals(email)) { // Assume email is the 5th field
                    return new User(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], UserRole.valueOf(fields[6]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Return null if user not found
    }

    public User findUserById(String userId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(UsersConstant.CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (fields.length >= 7 && fields[0].equals(userId)) {
                    return new User(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], UserRole.valueOf(fields[6]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   public boolean updateUser(User updatedUser) {
    List<User> allUsers = new ArrayList<>();
    boolean userFound = false;

    // Read all existing users from the CSV file
    try (BufferedReader reader = new BufferedReader(new FileReader(UsersConstant.CSV_FILE))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",", -1);

            // Assuming the correct order of fields in your CSV is:
            // id, firstName, lastName, phone, email, password, role

            // Handling the UserRole
            UserRole userType;
            try {
                userType = UserRole.valueOf(fields[6].toUpperCase()); // Convert string to enum
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid user role: " + fields[6]);
                continue; // Skip this line or handle it appropriately
            }

            // Create a User object from the CSV line
            User user = new User(
                    fields[0], // id
                    fields[1], // firstName
                    fields[2], // lastName
                    fields[3], // phoneNumber
                    fields[4], // email
                    fields[5], // password
                    userType  // role
            );

            // If this is the user to update, keep the existing password
            if (user.getEmail().equals(updatedUser.getEmail())) {
                updatedUser.setPassword(user.getPassword());
                allUsers.add(updatedUser);
                userFound = true;
            } else {
                allUsers.add(user);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    // If the user was found and updated, write the updated list back to the CSV file
    if (userFound) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(UsersConstant.CSV_FILE, false))) {
            for (User user : allUsers) {
                writer.println(user.getId() + "," + user.getFirstName() + "," + user.getLastName() + ","
                        + user.getPhoneNumber() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getRole().name());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    return false; // User not found
}


}
