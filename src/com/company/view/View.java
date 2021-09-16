package com.company.view;

import com.company.controller.DiskController;
import com.company.controller.exceptions.DiskIsNotFoundException;
import com.company.controller.exceptions.DiskIsNotUniqueException;
import com.company.dao.exceptions.UnableToLoadListOfDvdException;
import com.company.dao.exceptions.UnableToSaveListOfDvdException;
import com.company.dto.interfaces.Disk;

import java.time.DateTimeException;
import java.util.Scanner;

public class View {
    private final DiskController diskController;
    private final Scanner scanner;
    private final String nameForExitToMainMenu = "exit()";

    public View() {
        diskController = new DiskController();
        scanner = new Scanner(System.in);
    }

    public void start(){

        loop: while (true){
            String choice;

            printMainMenu();
            choice = scanner.nextLine();

            switch (choice){
                case "1":
                    addDvdActionMenu();
                    break;
                case "2":
                    removeDvdActionMenu();
                    break;
                case "3":
                    editDvdActionMenu();
                    break;
                case "4":
                    listAllDvd();
                    break;
                case "5":
                    informationOfDiskActionMenu();
                    break;
                case "6":
                    break;
                case "7":
                    loadFromFile();
                    break;
                case "8":
                    saveToFile();
                    break;
                case "exit()":
                    exit();
                    break loop;
                default:
                    System.out.println("Incorrect word");
            }
        }
    }

    private void printMainMenu(){
        cls();

        System.out.print("""
                Enter the number that corresponds to the required action:
                
                1. add a DVD to the collection
                2. remove a DVD from the collection
                
                3. edit the information for an existing DVD in the collection
                
                4. list the DVDs in the collection
                5. display the information for a particular DVD
                
                6. search for a DVD by title
                
                7. Load the DVD library from a file
                8. Save the DVD library (when the program completes it will be starts automatically)
                
                exit() to quit the program
                >>""");
    }

    private void cls() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
    private void exit(){
        try {
            diskController.save();
        } catch (UnableToSaveListOfDvdException e) {
            System.out.println("It's impossible to save you list");
        }
    }
    private void informationOfDiskActionMenu(){
        cls();
        System.out.print("Enter the name of disk that you want to see >> ");
        String name = scanner.nextLine();

        Disk disk = diskController.findDisk(name);

        if (disk == null){
            System.out.println("There is no such name");
        }
        else{
            System.out.println(disk);
        }

        System.out.println("enter a key to continue >> ");
        scanner.nextLine();
    }

    private void loadFromFile(){
        cls();
        try {
            diskController.load();
        } catch (UnableToLoadListOfDvdException e) {
            System.out.println("It's impossible to load your file");
        }
        System.out.print("You loaded a list from file. Enter a key to continue >> ");
        scanner.nextLine();
    }

    private void saveToFile(){
        cls();
        try {
            diskController.save();
        } catch (UnableToSaveListOfDvdException e) {
            System.out.println("It's impossible to save your list of dvd");
        }

        System.out.print("You saved a list to file. Enter a key to continue >> ");
        scanner.nextLine();
    }

    private void listAllDvd(){
        cls();
        for (Disk i : diskController.getAllDisks()){
            System.out.println(i + "\n");
        }

        System.out.print("Enter a key to continue >> ");
        scanner.nextLine();
    }

    private void editDvdActionMenu(){
        cls();

        boolean error = false;

        do {
            if (error){
                System.out.println("There is no disk with this name. Try again.");
            }

            System.out.print("Enter the name of dvd to edit it >> ");
            String name = scanner.nextLine();

            if (name.equals(nameForExitToMainMenu)) return;

            Disk disk = diskController.findDisk(name);

            if (disk == null){
                error = true;
                continue;
            }

            System.out.println(disk + "\n");
            System.out.print("Enter a number of field that needs to be edited >> ");

            String field = scanner.nextLine();
            System.out.print("Enter a value of this field >> ");
            String value = scanner.nextLine();

            if (field.equals("2")){
                System.out.print("Enter year >> ");
                int year = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter month >> ");
                int month = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter day >> ");
                int day = Integer.parseInt(scanner.nextLine());

                diskController.editDateOfReleaseOfDisk(disk, year, month, day);
            }
            else {
                diskController.editFieldOfDisk(disk, field, value);
            }
            error = false;
        } while (error);

        System.out.print("You edited a disk. Enter a key to continue >> ");
        scanner.nextLine();
    }



    private void removeDvdActionMenu(){
        cls();
        String name;

        boolean error = false;

        do{
            if (error){
                System.out.println("There is no dvd with this name. ");
            }

            System.out.print("Enter a dvd name for deleting >> ");
            name = scanner.nextLine();

            if (name.equals(nameForExitToMainMenu)){
                return;
            }

            try {
                diskController.removeDisk(name);
                error = false;
            } catch (DiskIsNotFoundException e) {
                error = true;
            }
        } while (error);

        System.out.print("You removed a disk from file. Enter a key to continue >> ");
        scanner.nextLine();
    }

    private void addDvdActionMenu(){


        boolean incorrectDate = false;
        boolean dvdExist = false;

        do {
            cls();

            if (incorrectDate){
                System.out.println("It's impossible to add a disk with these date, try again.\n");
            }
            if (dvdExist){
                System.out.println("It's impossible to add a disk with such name, try again\n");
            }

            incorrectDate = false;
            dvdExist = false;

            System.out.println("""
                To add a disk you need to type an information.
                If you want to stop disk adding type 'exit()' anywhere.
                """);
            String name;
            int year;
            int month;
            int day;
            int mpaaRating;
            String nameOfDirector;
            String nameOfStudio;
            String userNote;

            String buffer;

            System.out.print("\nEnter the name of the dvd.\n >> ");
            name = scanner.nextLine();
            if (name.equals("exit()")) break;

            System.out.print("\nEnter the year of the release.\n >> ");
            buffer = scanner.nextLine();
            if (buffer.equals("exit()")) break;
            year = Integer.parseInt(buffer);


            System.out.print("\nEnter the month of the release.\n >> ");
            month = Integer.parseInt(scanner.nextLine());
            System.out.print("\nEnter the day of the release.\n >> ");
            day = Integer.parseInt(scanner.nextLine());
            System.out.print("\nEnter the MPAA rating of the dvd.\n >> ");
            mpaaRating = Integer.parseInt(scanner.nextLine());
            System.out.print("\nEnter the director's name.\n >> ");
            nameOfDirector = scanner.nextLine();
            System.out.print("\nEnter the studio.\n >> ");
            nameOfStudio = scanner.nextLine();
            System.out.print("\nEnter your note for this dvd.\n >> ");
            userNote = scanner.nextLine();

            try {
                diskController.addDisk(name, year, month, day, mpaaRating, nameOfDirector, nameOfStudio, userNote);
            } catch (DiskIsNotUniqueException e) {
                dvdExist = true;
            }
            catch (DateTimeException e){
                incorrectDate = true;
            }
        } while (dvdExist || incorrectDate);

        System.out.print("You added a list from file. Enter a key to continue >> ");
        scanner.nextLine();
    }
}
