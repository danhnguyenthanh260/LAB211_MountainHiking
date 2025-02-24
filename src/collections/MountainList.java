/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import model.Mountain;

/**
 *
 * @author DANH NGUYEN
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MountainList {

    private static List<Mountain> mountains = new ArrayList<>();
    private static Set<String> mountainCodes = new HashSet<>();

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
            String line;
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
}
