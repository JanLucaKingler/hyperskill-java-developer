package traffic;

public class TrafficThread extends Thread {
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

            if (Main.getCurrentState() == GameState.SYSTEM) {
                Main.tick();
            }
        }
    }
}
