/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Student;
import java.util.ArrayList;
import java.util.Scanner;
import tools.Inputter;
import menu.Menu;
import tools.Acceptable;

/**
 *
 * @author DANH NGUYEN F
 */
public class StudentList {

    public static ArrayList<Student> students = new ArrayList<>();
    public static Stastistics stastistics = new Stastistics();

    public static boolean isSaved = true;

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
        stastistics.statisticalize(students);
        isSaved = false;
        System.out.println("Successfully add new student information!");
    }

    public static void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student ID to update:");
        String findId = scanner.nextLine();
        Student st = findStudentById(findId);

        if (st != null) {
            while (true) {
                System.out.print("Do you want to change student name?(if NO press enter)");
                String updatedName = scanner.nextLine();
                if (!updatedName.equals("") && Acceptable.isValid(updatedName, Acceptable.NAME_VALID)) {
                    st.setName(updatedName);
                    break;
                } else if (updatedName.equals("")) {
                    System.out.println("Skipping");
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }

            while (true) {
                System.out.print("Do you want to change phone number?(if NO press enter)");

                String updatedPhone = scanner.nextLine();
                if (!updatedPhone.equals("") && Acceptable.isValid(updatedPhone, Acceptable.PHONE_VALID)) {
                    st.setPhone(updatedPhone);
                    String updateTuitionFee = Inputter.getTuitionFee(updatedPhone);
                    st.setTuitionFee(updateTuitionFee);
                    break;
                } else if (updatedPhone.equals("")) {
                    System.out.println("Skipping");
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }

            while (true) {
                System.out.print("Do you want to change email?(if NO press enter)");
                String updatedEmail = scanner.nextLine();
                if (!updatedEmail.equals("") && Acceptable.isValid(updatedEmail, Acceptable.EMAIL_VALID)) {
                    st.setEmail(updatedEmail);
                } else if (updatedEmail.equals("")) {
                    System.out.println("Skipping");
                    break;
                } else {
                    System.out.println("Invalid input!");
                }

            }

            while (true) {
                System.out.print("Do you want to change mountain code?(if NO press enter)");
                String updatedMountainCode = scanner.nextLine();
                if (!updatedMountainCode.equals("") && MountainList.isValidMountainCode(updatedMountainCode)) {
                    st.setMountainCode(updatedMountainCode);
                    break;
                } else if (updatedMountainCode.equals("")) {
                    System.out.println("Skipping");
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
                System.out.println("Update student information successfully!!");
            }
            stastistics.statisticalize(students);
            isSaved = false;
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
                stastistics.statisticalize(students);
                isSaved = false;
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
        ArrayList<Student> matchStudents = existStudentByName(studentName.toLowerCase());

        if (!matchStudents.isEmpty()) {
            Menu.displaySearchingListBar();
            for (Student student : matchStudents) {
                System.out.format("%-11s | %-22s| %-28s| %-11s| MT%s\n", student.getId(), student.getName(), student.getEmail(), student.getPhone(), student.getMountainCode());
            }
            Menu.DisplayBarLine();
        } else {
            System.out.println("No one matches the search criteria!");
        }

    }

    public static ArrayList<Student> existStudentByName(String studentName) {
        ArrayList<Student> studentMatchName = new ArrayList<>();

        for (Student student : students) {
            if (student.getName().toLowerCase().contains(studentName)) {
                studentMatchName.add(student);
            }
        }
        return studentMatchName;
    }

    public static void filterDataByCampus() {
        String campusName = Inputter.getStudentCampus().toUpperCase();
        ArrayList<Student> matchStudents = containStudentWithCampus(campusName);

        if (matchStudents.isEmpty()) {
            System.out.println("No students have registered under this campus.");
        } else {
            Menu.displayFilterBar();
            for (Student student : students) {
                if (student.getId().startsWith(campusName)) {
                    System.out.format("%-11s | %-22s| %-28s| %-11s| MT%-7s| %s\n", student.getId(), student.getName(), student.getEmail(), student.getPhone(), student.getMountainCode(), student.getTuitionFee());
                }
            }
            Menu.DisplayBarLine();
        }
    }

    public static ArrayList<Student> containStudentWithCampus(String campusName) {
        ArrayList<Student> studentMatchCampus = new ArrayList<>();
        for (Student student : students) {
            if (student.getId().startsWith(campusName)) {
                studentMatchCampus.add(student);
            }
        }
        return studentMatchCampus;
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

    public static void showStatistics() {
        stastistics.show();
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
        try (FileOutputStream fos = new FileOutputStream("src/data/registration.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            isSaved = true;
            oos.writeObject(students);
            stastistics.statisticalize(students);
            System.out.println("Registration data has been successfully saved to `registration.dat`");
        } catch (IOException e) {
            System.out.println("Can not create new file!");
        }
    }

    public static void readFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/data/registration.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            students = (ArrayList<Student>) objectInputStream.readObject();
            stastistics.statisticalize(students);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can not find file 'registration.dat'");
        } catch (IOException e) {
            System.out.println("Error to read from file " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Can not find class ro read data");
        }
    }

    public static void exitProgram() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (isSaved == false) {
                System.out.println("You have unsaved changes. Are you sure you want to exit without saving? (Y/N)");
                String exit = sc.nextLine();
                if (exit.equalsIgnoreCase("y")) {
                    writeToFile();
                    break;
                } else if (exit.equalsIgnoreCase("n")) {
                    System.out.println("Exit program without save to file successfully!");
                    break;
                } else {
                    System.out.println("Invalid input! Try (Y/N)");
                }
            } else {
                System.out.println("Exit program successful!");
                break;
            }
        }

    }
}
