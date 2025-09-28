package traffic;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input the number of roads:");
        int numberOfRoads = scanner.nextInt();

        System.out.print("Input the interval");
        int interval = scanner.nextInt();

        int userInput;
        do {
            printMenu();
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1: {
                    addRoad();
                    break;
                }
                case 2: {
                    deleteRoad();
                    break;
                }
                case 3: {
                    openSystem();
                    break;
                }
                case 4: {
                    quitSystem();
                    break;
                }
            }

        } while (userInput != 0);
    }

    private static void printWelcomeMessage() {
        System.out.println("Welcome to the traffic management system!");
    }

    private static void printMenu() {
        System.out.println("Menu:\n" +
                "1. Add road\n" +
                "2. Delete road\n" +
                "3. Open system\n" +
                "0. Quit");
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

