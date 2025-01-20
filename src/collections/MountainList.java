/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import java.util.*;
import model.Mountain;

/**
 *
 * @author DANH NGUYEN
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MountainList {

    private static final List<Mountain> mountains = new ArrayList<>();
    private static final Set<String> mountainCodes = new HashSet<>();

    public static boolean isValidMountainCode(String mountainCode) {
        return mountainCodes.contains(mountainCode.toUpperCase());
    }

    private static Mountain dataToObject(String text) {
        String[] parts = text.split(",", -1);
        if (parts.length == 4) {
            return new Mountain(
                    parts[0].trim(),
                    parts[1].trim(),
                    parts[2].trim(),
                    parts[3].trim()
            );
        } else {
            System.out.println("Invalid data format: " + text);
        }
        return null;
    }

    public static void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/MountainList.csv"))) {
            String line = br.readLine(); // Đọc tiêu đề
            while ((line = br.readLine()) != null) {
                Mountain mountain = dataToObject(line);
                if (mountain != null) {
                    mountains.add(mountain);
                    mountainCodes.add(mountain.getMountainCode().toUpperCase());
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public static void display() {
        if (mountains.isEmpty()) {
            System.out.println("No mountains available. Please load data first.");
        } else {
            for (Mountain mountain : mountains) {
                System.out.println(mountain);
            }
        }
    }
}
