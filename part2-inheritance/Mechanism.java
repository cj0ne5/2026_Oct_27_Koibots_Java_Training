// The parent class (superclass): everything every mechanism has in common
public class Mechanism {
    private final String name;
    private double speed; // -1.0 (full reverse) to 1.0 (full forward)

    public Mechanism(String name) {
        this.name = name;
    }

    public void setSpeed(double speed) { // void: does something, returns nothing
        this.speed = Math.max(-1.0, Math.min(1.0, speed));
    }

    public double getSpeed() { // returns a double
        return speed;
    }

    public String getName() { // returns a String (which is an object)
        return name;
    }

    public boolean isRunning() { // returns a boolean
        return speed != 0;
    }

    public String describe() {
        return name + " running at " + Math.round(speed * 100) + "%";
    }
}
