package traffic;

public class TrafficThread extends Thread {
    private int secondsSinceStart = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Traffic thread interrupted!");
                return;
            }

            secondsSinceStart++;

            if (Main.getCurrentState() == GameState.SYSTEM) {
                clearConsole();

                System.out.println("\n! " + secondsSinceStart + "s. have passed since system startup !");
                System.out.println("! Number of roads: " + Main.getNumberOfRoads() + " !");
                System.out.println("! Interval: " + Main.getNumberOfIntervals() + " !\n");
                for (String road : Main.getRoadQueue()) {
                    System.out.println(road);
                }
                System.out.println("\n! Press \"Enter\" to open menu !");
            }
        }
    }

    private void clearConsole() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}
