package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        userInput();
    }

    private static void userInput() {
        System.out.println("Write how many cups of coffee you will need:");
        int userInput = scanner.nextInt();

        System.out.println("For " + userInput + " cups of coffee you will need:");
        System.out.println(200 * userInput + " ml of water");
        System.out.println(50 * userInput + " ml of milk");
        System.out.println(15 * userInput + " g of coffee beans");
    }
}
