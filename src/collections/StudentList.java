/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Student;
import java.util.ArrayList;
import java.util.Scanner;
import tools.Inputter;
import menu.Menu;

/**
 *
 * @author DANH NGUYEN F
 */
public class StudentList {

    public static ArrayList<Student> students = new ArrayList<>();

    public static boolean isSaved = false;

    public static void addNewStudent() {
        String studentId = Inputter.getstudentId();
        String name = Inputter.inputName();
        String phone = Inputter.inputPhone();
        String email = Inputter.inputEmail();
        String mountainCode = Inputter.inputMountainCode();
        String tutionFee = Inputter.getTuitionFee(phone);

        Student student = new Student(studentId, name, phone,
                email, mountainCode, tutionFee);
        students.add(student);
        writeToFile();
        System.out.println("Successfully add new student information!");
    }

    public static void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student ID to update:");
        String findId = scanner.nextLine();
        Student st = findStudentById(findId);
        if (st != null) {
            System.out.println("Do you want to change student name?(if NO press enter)");
            String updatedName = Inputter.inputName();
            if (!updatedName.equals("")) {
                st.setName(updatedName);
            } else {
                st.setName(st.getName());
            }

            System.out.println("Do you want to change phone number?(if NO press enter)");

            String updatedPhone = Inputter.inputPhone();
            if (!updatedPhone.equals("")) {
                st.setPhone(updatedPhone);
                String updateTuitionFee = Inputter.getTuitionFee(updatedPhone);
                st.setTuitionFee(updateTuitionFee);
            } else {
                st.setPhone(st.getPhone());
                st.setTuitionFee(st.getTuitionFee());
            }

            System.out.println("Do you want to change email?(if NO press enter)");
            String updatedEmail = Inputter.inputEmail();
            if (!updatedEmail.equals("")) {
                st.setEmail(updatedEmail);
            } else {
                st.setEmail(st.getEmail());
            }

            System.out.println("Do you want to change mountain code?(if NO press enter)");
            String updatedMountainCode
                    = scanner.nextLine();
            if (!updatedMountainCode.equals("")) {
                updatedMountainCode = Inputter.inputMountainCode();
                st.setMountainCode(updatedMountainCode);
            } else {
                st.setMountainCode(st.getMountainCode());
            }
            System.out.println("Update student information successfully!!");
        } else {
            System.out.println("This student has not registered yet.");
        }
    }

    public static void showStudent() {

        if (students.isEmpty()) {
            System.out.println("No students have registered yet");
        } else {
            Menu.displayRegistrationListBar();
            for (Student student : students) {
                System.out.println(student);
            }
            Menu.DisplayBarLine();
        }
    }

    public static void deleteStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student ID to delete:");
        String findIdForDelete = scanner.nextLine();
        Student st = findStudentById(findIdForDelete);
        if (st != null) {
            for (Student student : students) {
                if (student.getId().equals(st.getId())) {
                    Menu.DisplayBarLine();
                    System.out.println(" Student ID        : " + student.getId() + "\n"
                            + " Name              : " + student.getName() + "\n"
                            + " Phone number      : " + student.getPhone() + "\n"
                            + " Email             : " + student.getEmail() + "\n"
                            + " Mountain Peak Code: " + "MT" + student.getMountainCode() + "\n"
                            + " Tuition Fee       : " + student.getTuitionFee());
                    Menu.DisplayBarLine();
                }
            }
            System.out.println("Are you sure you want to delete this registration? (Y/N):");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("Y")) {
                students.remove(st);
                System.out.println("The registration has been successfully deleted.");
                writeToFile();
            } else {
                System.out.println("Cancel delete success!");
            }
        } else {
            System.out.println("This student has not registered yet.");
        }

    }

    public static void SearchingByStudentName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student name you want to find: ");
        String studentName = sc.nextLine();
        Student st = findStudentByName(studentName);
        if (st != null) {
            if (studentName.equalsIgnoreCase(st.getName())) {
                Menu.displaySearchingListBar();
                for (Student student : students) {
                    if (studentName.equalsIgnoreCase(student.getName())) {
                        System.out.format("%-11s | %-22s| %-18s| %-11s| MT%s\n", student.getId(), student.getName(), student.getEmail(), student.getPhone(), student.getMountainCode());
                    }
                }
                Menu.DisplayBarLine();
            } else {
                System.out.println("No one matches the search criteria!");
            }
        }
    }

    public static void filterDataByCampus() {
        String campusName = Inputter.getStudentCampus().toUpperCase();
        Menu.displayFilterBar();
        for (Student student : students) {
            if (student.getId().startsWith(campusName)) {
                System.out.format("%-11s | %-22s| %-18s| %-11s| MT%-7s| %s\n", student.getId(), student.getName(), student.getEmail(), student.getPhone(), student.getMountainCode(), student.getTuitionFee());
            }
        }
        Menu.DisplayBarLine();

    }

    public static Student findStudentById(String id) {
        for (Student student : students) {
            if (id.equalsIgnoreCase(student.getId())) {
                System.out.println("Your student ID had been found!");
                return student;
            }
        }
        return null;
    }

    public static Student findStudentByName(String studentName) {
        for (Student student : students) {
            if (studentName.equalsIgnoreCase(student.getName())) {
                System.out.println("Your student ID had been found!");
                return student;
            }
        }
        System.out.println("This student has not registered yet");
        return null;
    }

    public static String addCommaToTuitionFee(double tuitionFee) {
        String formattedFee = String.format("%,.0f", tuitionFee);
        return formattedFee;
    }

    public static double deleteCommaToTuitionFee(String tuitionFee) {
        String formattedFee = tuitionFee.replace(",", "");
        return Double.parseDouble(formattedFee);
    }

    public static void writeToFile() {
        try (FileOutputStream fos = new FileOutputStream("src/data/students.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            isSaved = true;
            oos.writeObject(students);
            System.out.println("Registration data has been successfully saved to `student.dat`");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/data/students.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            students = (ArrayList<Student>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void exitProgram() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (!isSaved) {
                System.out.println("Do you want to save the changes before exiting? (Y/N)");
                String exit = sc.nextLine();
                if (exit.equalsIgnoreCase("y")) {
                    writeToFile();
                    break;
                } else if (exit.equalsIgnoreCase("n")) {
                    System.out.println("Exit program without save successfully!");
                    break;
                } else {
                    System.out.println("Please enter (Y/N)");
                }
            }
        }

    }
}
