public class Robot {
    // static final = a constant: one copy shared by every Robot, and it never changes
    // Below this voltage, the roboRIO shuts off the motors to protect itself (a "brownout")
    public static final double BROWNOUT_VOLTS = 6.8;

    // Every battery loses some voltage inside itself. The more current the motors pull,
    // the more voltage is lost before it reaches the robot.
    public static final double INTERNAL_RESISTANCE_OHMS = 0.02;

    // static = one copy shared by every Robot (each new robot bumps the same counter)
    private static int robotsBuilt = 0;

    // final = set once (in the constructor), then locked
    private final String name;

    // private = only code inside Robot can read or change this directly
    // Battery voltage with nothing running. It drops slowly as the battery gets used up.
    private double restingVolts;

    public Robot(String name, double restingVolts) {
        this.name = name;
        this.restingVolts = restingVolts;
        robotsBuilt++;
    }

    public void drive(int seconds) {
        restingVolts -= 0.005 * seconds;
    }

    // What the robot actually sees while the motors pull this many amps
    public double getVoltageUnderLoad(double amps) {
        return restingVolts - amps * INTERNAL_RESISTANCE_OHMS;
    }

    public boolean wouldBrownOut(double amps) {
        return getVoltageUnderLoad(amps) < BROWNOUT_VOLTS;
    }

    // public "getters" let other code READ private values without being able to CHANGE them
    public String getName() {
        return name;
    }

    public double getRestingVolts() {
        return restingVolts;
    }

    public static int getRobotsBuilt() {
        return robotsBuilt;
    }
}
