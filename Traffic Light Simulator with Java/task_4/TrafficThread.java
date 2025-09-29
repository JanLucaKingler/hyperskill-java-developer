package traffic;

public class TrafficThread extends Thread {
    private int secondsSinceStart = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            secondsSinceStart++;

            if (Main.getCurrentState() == GameState.SYSTEM) {
                System.out.println("\n! " + secondsSinceStart + "s. have passed since system startup !");
                System.out.println("! Number of roads: " + Main.getNumberOfRoads() + " !");
                System.out.println("! Interval: " + Main.getNumberOfIntervals() + " !");
                System.out.println("! Press \"Enter\" to open menu !");
            }
        }
    }
}
