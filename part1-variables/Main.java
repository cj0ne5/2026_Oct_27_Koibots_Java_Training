// Part 1: Variables and objects (public / private, static, final)

public class Main {
    public static void main(String[] args) {
        Robot sparky = new Robot("Sparky", 12.8); // fresh battery
        Robot bolt = new Robot("Bolt", 12.2); // battery that wasn't fully charged

        // Both robots play a full 150-second match
        sparky.drive(150);
        bolt.drive(150);

        // Then both accelerate as hard as they can, pulling 250 amps
        double amps = 250;
        report(sparky, amps);
        report(bolt, amps);

        // static things belong to the CLASS, so we ask Robot, not sparky or bolt
        System.out.println("Robots built: " + Robot.getRobotsBuilt());
        System.out.println("Brownout below: " + Robot.BROWNOUT_VOLTS + " V");

        // Uncomment these one at a time. Why won't each one compile?
        // sparky.restingVolts = 99;
        // Robot.BROWNOUT_VOLTS = 5;
        // sparky.name = "Not Sparky";
    }

    public static void report(Robot robot, double amps) {
        System.out.printf(
                "%s: %.2f V resting, %.2f V while accelerating -> %s%n",
                robot.getName(),
                robot.getRestingVolts(),
                robot.getVoltageUnderLoad(amps),
                robot.wouldBrownOut(amps) ? "BROWNOUT!" : "OK");
    }
}
