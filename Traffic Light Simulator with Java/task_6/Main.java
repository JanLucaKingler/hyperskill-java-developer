package traffic;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static GameState CURRENT_STATE = GameState.NOT_STARTED;
    private static int numberOfRoads;
    private static int numberOfIntervals;
    private static int secondsSinceStart = 0;
    private static BlockingQueue<Road> roadQueue;

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

        if (roadQueue.remainingCapacity() == 0) {
            System.out.println("Queue is full");
            return;
        }

        boolean isFirstRoad = roadQueue.isEmpty();
        int timeRemaining = isFirstRoad ? numberOfIntervals : numberOfIntervals * roadQueue.size();

        Road newRoad = new Road(roadName, isFirstRoad, timeRemaining);
        roadQueue.offer(newRoad);

        System.out.println(roadName + " added");
    }

    private static void deleteRoad() {
        if (roadQueue.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        Road removed = roadQueue.poll();
        System.out.println(removed.getName() + " deleted");

        if (removed.isOpen() && !roadQueue.isEmpty()) {
            Road next = roadQueue.peek();
            next.setOpen(true);
            next.setTimeRemaining(numberOfIntervals);


            int delay = numberOfIntervals;
            int index = 1;
            for (Road r : roadQueue) {
                if (index == 1) {

                    index++;
                    continue;
                }
                r.setOpen(false);
                r.setTimeRemaining(delay * index);
                index++;
            }
        }

        if (roadQueue.size() == 1) {
            Road onlyRoad = roadQueue.peek();
            onlyRoad.setOpen(true);
            onlyRoad.setTimeRemaining(numberOfIntervals);
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

    public static void tick() {
        clearConsole();
        secondsSinceStart++;

        System.out.println("\n! " + secondsSinceStart + "s. have passed since system startup !");
        System.out.println("! Number of roads: " + numberOfRoads + " !");
        System.out.println("! Interval: " + numberOfIntervals + " !\n");

        if (roadQueue.isEmpty()) {
            System.out.println("No roads in system.");
            System.out.println("\n! Press \"Enter\" to open menu !");
            return;
        }

        for (Road road : roadQueue) {
            road.decreaseTime();
        }

        Road openRoad = roadQueue.peek();
        if (openRoad != null && openRoad.isOpen() && openRoad.getTimeRemaining() <= 0) {
            if (roadQueue.size() > 1) {
                rotateRoads();
            } else {
                openRoad.setTimeRemaining(numberOfIntervals);
            }
        }

        boolean someoneIsOpen = roadQueue.stream().anyMatch(Road::isOpen);
        if (!someoneIsOpen) {
            for (Road road : roadQueue) {
                if (road.getTimeRemaining() <= 0) {
                    road.setOpen(true);
                    road.setTimeRemaining(numberOfIntervals);
                    break;
                }
            }
        }

        int index = 0;
        for (Road road : roadQueue) {
            if (road.isOpen()) {
                index++;
                continue;
            }

            int expectedTime = numberOfIntervals * index;

            if (road.getTimeRemaining() > expectedTime) {
            } else if (road.getTimeRemaining() <= 0) {
                road.setTimeRemaining(expectedTime);
            }

            index++;
        }

        for (Road road : roadQueue) {
            System.out.println(road);
        }

        System.out.println("\n! Press \"Enter\" to open menu !");
    }

    private static void rotateRoads() {
        Road finished = roadQueue.poll();
        if (finished != null) {
            finished.setOpen(false);
            roadQueue.offer(finished);

            Road next = roadQueue.peek();
            if (next != null) {
                next.setOpen(true);
                next.setTimeRemaining(numberOfIntervals);
            }
        }
    }

    private static void clearConsole() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public static GameState getCurrentState() {
        return CURRENT_STATE;
    }

    public static void setCurrentState(GameState state) {
        CURRENT_STATE = state;
    }

    public static BlockingQueue<Road> getRoadQueue() {
        return roadQueue;
    }
}
