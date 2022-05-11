package com.ironhack.WondercodersCRMDataLayer.classes;

import com.ironhack.WondercodersCRMDataLayer.Enums.Color;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AppHelp {

    public static String id ;

    public static String getId() { return id; }
    public static void setId(String id) {AppHelp.id = id;}

    // INPUT-VALIDATION --------------------------------------------------------------

    // Ask for email
    public static String askForEmail(String question) {
        boolean ok;
        String entry = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(question);
            entry = scanner.nextLine();
            ok = entry.matches(
                    "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
            );
            if(!ok) System.err.println("You did not introduce a valid email");
        } while (!ok);
        return entry;

    }

    // Ask for phone number
    public static String askForPhoneNumber(String question) {
        boolean ok;
        String entry = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(question);
            entry = scanner.nextLine();
            ok = entry.matches(
                    "^\\d{9}$"
            );
            if(!ok) System.err.println("You did not introduce a valid phone number");
        } while (!ok);
        return entry;

    }
    // Ask for YES / NO
    public static String askForYesOrNo(String question) {
        boolean ok;
        String entry = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(question);
            entry = scanner.nextLine();
            ok = entry.equals("yes") || entry.equals("yes") ? true : false;
            if(!ok) System.err.println("Only 'yes' and 'no' are valid answers");
        } while (!ok);
        return entry;

    }

    // Ask user for a String
    public static String askForString(String question) {
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        String entry = "";
        boolean done = false;
        do {
            try {
                entry = scanner.nextLine();
                done = true;
            } catch (Exception e) {
                System.err.println("You did not introduce a valid text");
                scanner.next();
            }
        } while (!done);
        return entry;
    }

    //Ask user for an integer
    public static int askForInt(String question){
        boolean done = false;
        int entry = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println(question);
                entry = scanner.nextInt();
                done = true;
            } catch(Exception e){
                System.out.println("\nYou did not introduce a valid integer. Please try again.");
                scanner.next();
            }
        }while(!done);
        return entry;
    }

    // Ask for an integer within a range
    public static int askForIntInRange(String question, int min, int max) {
        boolean done = false;
        int entry = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println(question);
                entry = scanner.nextInt();
                while (entry < min || entry > max) {
                    System.err.println(entry + " is not a valid option");
                    entry = scanner.nextInt();
                }
                done = true;
            } catch (Exception e) {
                System.err.println("You did not introduce a valid integer. Please try again.");
                scanner.next();
            }
        } while (!done);
        return entry;
    }
    //Select an option from a list
    public static int selectOption(String instruction, String[] optionsList) {
        //Show menu
        System.out.println("\n" + instruction);
        printList(optionsList);
        //Ask for selected option
        int response = askForIntInRange("\n-> Type a number from 1 to " + (optionsList.length), 1, optionsList.length);
        //Show selected option
        if (response > 0 && response < optionsList.length + 1) {
            System.out.println("You have selected option " + response + ": " + optionsList[response - 1] + "\n");
        }
        return response - 1;
    }
    public static int selectOption(String instruction, Object[] optionsList) {
        //Show menu
        System.out.println("\n" + instruction);
        printList(optionsList);
        //Ask for selected option
        int response = askForIntInRange("\n-> Type a number from 1 to " + (optionsList.length), 1, optionsList.length);
        //Show selected option
        if (response > 0 && response < optionsList.length + 1) {
            System.out.println("You have selected option " + response + ": " + optionsList[response - 1] + "\n");
        }
        return response - 1;
    }
    //Print List
    public static void printList(String[] optionsList) {
        for (int i = 0; i < optionsList.length; i++) {
            System.out.println("\t\t" + (i + 1) + "." + optionsList[i]);
        }
    }
    public static void printList(Object[] optionsList) {
        for (int i = 0; i < optionsList.length; i++) {
            System.out.println("\t\t" + (i + 1) + "." + optionsList[i].toString());
        }
    }

    // Print table
    public static void printTable(String title, String[] headers, List<String[]> list) {

        String headersString = "| " + String.join("", headers) + " |";
        String horizontalLine = "";
        for(int i = 0; i < headersString.length(); i++) horizontalLine += "-";

        TextColor.changeTo(Color.BLUE);
        System.out.println("\n  " + title);
        System.out.println(horizontalLine);
        System.out.println(headersString);
        System.out.println(horizontalLine);
        for(int i = 0; i<list.size(); i++) {
            System.out.format("%-2.2s", "| ");
            for(int n = 0; n<list.get(i).length; n++) System.out.format("%-" + headers[n].length() + "." + headers[n].length() + "s", list.get(i)[n]);
            System.out.format("%-2.2s", " |");
            System.out.println();
        }
        System.out.println(horizontalLine);
        TextColor.reset();
    }

}
