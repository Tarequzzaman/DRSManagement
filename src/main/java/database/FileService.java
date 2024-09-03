/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author tarequzzamankhan
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {

    public boolean createFileIfNotExists(String fileName, String headers) {
        File file = new File(fileName);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(headers);
                writer.newLine();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false; // File already exists
    }

    public boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
