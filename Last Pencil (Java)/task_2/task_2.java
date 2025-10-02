package lastpencil;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        printUserInput();
    }

    private static void printUserInput() {
        System.out.println("How many pencils would you like to use:");
        int pencil = input.nextInt();

        System.out.println("Who will be the first (John, Jack):");
        String user = input.next();

        String stroke = "|";

        if (pencil > 0) {
            String repeat = stroke.repeat(pencil);
            System.out.println(repeat);
        } else {
            System.out.println(" ");
        }

        if (user.equalsIgnoreCase("John")) {
            System.out.println("John is going first!");
        } else {
            System.out.println("Jack is going first!");
        }
    }
}
