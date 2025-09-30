package traffic;

public class Road {

    private final String name;
    private boolean isOpen;
    private int timeRemaining;

    public Road(String name, boolean isOpen, int timeRemaining) {
        this.name = name;
        this.isOpen = isOpen;
        this.timeRemaining = timeRemaining;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public void decreaseTime() {
        if (timeRemaining > 0) {
            timeRemaining--;
        }
    }

    @Override
    public String toString() {
        String color = isOpen ? "\u001B[32m" : "\u001B[31m"; 
        String status = isOpen ? "open" : "closed";
        String timeMsg = isOpen
                ? "will be open for " + timeRemaining + "s."
                : "will be closed for " + timeRemaining + "s.";
        return color + "Road \"" + name + "\" is " + status + " → " + timeMsg + "\u001B[0m";
    }
}
