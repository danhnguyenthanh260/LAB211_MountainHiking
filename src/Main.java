
import collections.MountainList;
import collections.StudentList;
import java.util.Scanner;
import menu.Menu;
import collections.Stastistics;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DANH NGUYEN
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
       
        StudentList.readFromFile();
        MountainList.loadFromFile();
        Stastistics stastistics = new Stastistics(StudentList.students);
        do {
            Menu.printMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    StudentList.addNewStudent();
                    break;
                case 2:
                    StudentList.updateStudent();
                    break;
                case 3:
                    StudentList.showStudent();
                    break;
                case 4:
                    StudentList.deleteStudent();
                    break;
                case 5:
                    StudentList.SearchingByStudentName();
                    break;
                case 6:
                    StudentList.filterDataByCampus();
                    break;
                case 7:
                    stastistics.show();
                    break;
                case 8:
                    StudentList.writeToFile();
                    break;
                case 9:
                    StudentList.exitProgram();
                    break;
                default:
                    System.out.println("This function is not available.");
                    break;
            }
        } while (choice != 9);

    }
}
