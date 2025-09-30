package traffic;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static GameState CURRENT_STATE = GameState.NOT_STARTED;
    private static int numberOfRoads;
    private static int numberOfIntervals;
    private static BlockingQueue<String> roadQueue;

    public static void main(String[] args) {

        printWelcomeMessage();
        printStreetNumber();
        printInterval();
        startThread();
        printMenu();
        userInput();
    }

    private static void printWelcomeMessage() {
        System.out.println("Welcome to the traffic management system!");
    }

    private static void printStreetNumber() {
        System.out.print("Input the number of roads:");
        do {
            numberOfRoads = SCANNER.nextInt();
            roadQueue = new ArrayBlockingQueue<>(numberOfRoads);

            if (numberOfRoads <= 0) {
                System.out.println("Incorrect input | Try again:");
            }
        } while (numberOfRoads <= 0);
    }

    private static void printInterval() {
        System.out.print("Input the interval:");
        do {
            numberOfIntervals = SCANNER.nextInt();

            if (numberOfIntervals <= 0) {
                System.out.println("Incorrect input | Try again:");
            }
        } while (numberOfIntervals <= 0);
    }


    private static void printMenu() {
        CURRENT_STATE = GameState.MENU;
        System.out.println("""
                Menu:
                1. Add road
                2. Delete road
                3. Open system
                0. Quit""");
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
                return;
            case 0:
                quitSystem();

            default:
                System.out.println("Incorrect menu choice | Try again:");
                userInput();
        }
        printMenu();
        userInput();
    }

    private static void addRoad() {
        System.out.println("Input road name:");
        SCANNER.nextLine();
        String roadName = SCANNER.nextLine();

        boolean added = roadQueue.offer(roadName);
        if (added) {
            System.out.println(roadName + " added");
        } else {
            System.out.println("Queue is full");
        }
    }

    private static void deleteRoad() {
        if (roadQueue.isEmpty()) {
            System.out.println("Queue is empty");
        } else {

            System.out.println(roadQueue.poll() + " deleted");
        }
    }

    private static void openSystem() {

        setCurrentState(GameState.SYSTEM);
        SCANNER.nextLine();

        String input = SCANNER.nextLine();
        if (input.isEmpty()) {
            setCurrentState(GameState.MENU);
            printMenu();
            userInput();
        }
    }

    private static void quitSystem() {
        System.out.println("Bye!");
        System.exit(0);
    }

    private static void startThread() {
        TrafficThread trafficThread = new TrafficThread();
        trafficThread.setName("QueueThread");
        trafficThread.start();
    }

    public static GameState getCurrentState() {
        return CURRENT_STATE;
    }

    public static int getNumberOfRoads() {
        return numberOfRoads;
    }

    public static int getNumberOfIntervals() {
        return numberOfIntervals;
    }

    public static void setCurrentState(GameState state) {
        CURRENT_STATE = state;
    }

    public static BlockingQueue<String> getRoadQueue() {
        return roadQueue;
    }
}

