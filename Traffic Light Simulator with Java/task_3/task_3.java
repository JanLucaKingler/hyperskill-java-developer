package traffic;

import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        printWelcomeMessage();
        printStreetNumber();
        printInterval();
        printMenu();
        userInput();
    }

    private static void printWelcomeMessage() {
        System.out.println("Welcome to the traffic management system!");
    }

    private static void printStreetNumber() {
        System.out.print("Input the number of roads:");
        int numberOfRoads;
        do {
            numberOfRoads = SCANNER.nextInt();

            if (numberOfRoads <= 0) {
                System.out.println("Incorrect input | Try again:");
            }
        } while (numberOfRoads <= 0);
    }

    private static void printInterval() {
        System.out.print("Input the interval:");
        int numberOfIntervals;
        do {
            numberOfIntervals = SCANNER.nextInt();

            if (numberOfIntervals <= 0) {
                System.out.println("Incorrect input | Try again:");
            }
        } while (numberOfIntervals <= 0);
    }


    private static void printMenu() {
        System.out.println("Menu:\n" +
                "1. Add road\n" +
                "2. Delete road\n" +
                "3. Open system\n" +
                "0. Quit");
    }

    private static void userInput() {
        int menuUserChoice = SCANNER.nextInt();

        switch (menuUserChoice) {
            case 1:
                addRoad();
                break;
            case 2:
                deleteRoad();
                break;
            case 3:
                openSystem();
                break;
            case 0:
                quitSystem();

            default:
                System.out.println("Incorrect menu choice | Try again:");
                userInput();
        }
    }

    private static void addRoad() {
        System.out.println("Road added");
    }

    private static void deleteRoad() {
        System.out.println("Road deleted");
    }

    private static void openSystem() {
        System.out.println("System opened");
    }

    private static void quitSystem() {
        System.out.println("Bye!");
        System.exit(0);
    }
}

