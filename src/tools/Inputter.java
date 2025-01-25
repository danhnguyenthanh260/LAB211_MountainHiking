/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import collections.StudentList;
import java.util.Scanner;
import java.util.UUID;
import menu.Menu;
import collections.MountainList;
import java.util.InputMismatchException;

/**
 *
 * @author DANH NGUYEN
 */
public class Inputter {

    boolean check = false;

    public static String getStudentCampus() {
        Scanner scanner = new Scanner(System.in);
        String campusName = "";

        while (true) {
            Menu.DisplayBarLine();
            System.out.println("Campus:");
            System.out.println("1. Ha Noi: HE\n"
                    + "2. Sai gon: SE\n"
                    + "3. Da Nang: DE\n"
                    + "4. Quy Nhon: QE\n"
                    + "5. Can Tho: CE\n");
            Menu.DisplayBarLine();
            System.out.print("Choose your campus (1.HE-2.SE-3.DE-4.QE-5.CE): ");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        campusName = "HE";
                        break;
                    case 2:
                        campusName = "SE";
                        break;
                    case 3:
                        campusName = "DE";
                        break;
                    case 4:
                        campusName = "QE";
                        break;
                    case 5:
                        campusName = "CE";
                        break;
                    default:
                        System.out.println("Invalid campus!!");
                        continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid campus! Choose a number");
                scanner.next();
            }

        }
        return campusName;
    }

    public static String getstudentId() {
        String campusName = getStudentCampus().toUpperCase();

        String id = UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        String uniqueId = id.substring(0, 6);
     
        System.out.print("Student ID: ");
        System.out.println(campusName + uniqueId);
        return campusName + uniqueId;
    }

    public static String inputName() {
        boolean check = false;
        String name = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student name: ");
            name = sc.nextLine();
            if (Acceptable.isValid(name, Acceptable.NAME_VALID)) {
                check = true;
            } else {
                System.out.println("Name must be from(2-20) characters!");
            }
        } while (!check);
        return name;
    }

    public static String inputPhone() {
        boolean check = false;
        String phone = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student phone number: ");
            phone = sc.nextLine();
            if (Acceptable.isValid(phone, Acceptable.PHONE_VALID)) {
                check = true;
            } else {
                System.out.println("INVALID PHONE NUMBER!");
            }
        } while (!check);
        return phone;
    }

    public static String inputEmail() {
        boolean check = false;
        String email = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student email: ");
            email = sc.nextLine();
            if (Acceptable.isValid(email, Acceptable.EMAIL_VALID)) {
                check = true;
            } else {
                System.out.println("INVALID EMAIL!!");
            }
        } while (!check);
        return email;
    }

    public static String getTuitionFee(String phone) {
        double tutition = 6000000;
        String result = "";
        if (Acceptable.isValid(phone, Acceptable.VIETTEL_VALID)
                || Acceptable.isValid(phone, Acceptable.VNPT_VALID)) {
            tutition = (int) (6000000 * 0.65);;
            result = StudentList.addCommaToTuitionFee(tutition);
            return result;
        }
        result = StudentList.addCommaToTuitionFee(tutition);
        return result;
    }

    public static String inputMountainCode() {
        boolean check;
        String typeMountainCode = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter mountain Code(1-13): ");
            typeMountainCode = sc.nextLine();
            check = MountainList.isValidMountainCode(typeMountainCode);
            if (check) {
                return typeMountainCode;
            } else {
                System.out.println("INVALID CODE!!");
            }
        } while (check == false);
        return typeMountainCode;
    }

}
