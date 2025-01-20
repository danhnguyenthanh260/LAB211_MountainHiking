package menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DANH NGUYEN
 */
public class Menu {

    public static void printMainMenu() {

        System.out.println("\n<=================================================  MENU  ================================================>");
        System.out.println("        1. New Registration");
        System.out.println("        2. Update Registration Information");
        System.out.println("        3. Display Registered List");
        System.out.println("        4. Delete Registration Information");
        System.out.println("        5. Search Participants by Name");
        System.out.println("        6. Filter Data by Campus");
        System.out.println("        7. Statistics of Registration Numbers by Location");
        System.out.println("        8. Save Data to File");
        System.out.println("        9. Exit the Program");
        System.out.println("<==========================================================================================================>");
        System.out.print("Enter your choice: ");

    }

    public static void displayRegistrationListBar() {
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Student ID  | Name                  | Phone      | Peak Code| Fee");
        System.out.println("---------------------------------------------------------------------------------------------------------");

    }
    
     public static void displaySearchingListBar() {
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Student ID  | Name                  | Email             | Phone      | Peak Code");
        System.out.println("---------------------------------------------------------------------------------------------------------");

    }
     
      public static void displayFilterBar() {
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("Student ID  | Name                  | Email             | Phone      | Peak Code| Tuition Fee");
        System.out.println("----------------------------------------------------------------------------------------------------------");

    }
      
      public static void displayStastisticBar() {
          System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("PeakName  | Number of participants | Total cost   ");
        System.out.println("----------------------------------------------------------------------------------------------------------");
      }

    public static void DisplayBarLine() {
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

}
