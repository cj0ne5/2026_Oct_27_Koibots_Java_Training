// Part 3: Constructors and periodic()

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Constructors run ONCE, when the robot turns on
        System.out.println("=== Robot boots ===");
        Flywheel flywheel = new Flywheel();
        Lights lights = new Lights(flywheel);
        SubsystemBase[] subsystems = {flywheel, lights};

        System.out.println("=== Driver asks for 4000 RPM ===");
        flywheel.setTargetRpm(4000);

        // The robot loop. On a real robot, WPILib does this for you every 20 ms
        // and you NEVER call periodic() yourself.
        for (int tick = 1; tick <= 10; tick++) {
            System.out.print("tick " + tick + ": ");
            for (SubsystemBase s : subsystems) {
                s.periodic();
            }
            System.out.println();
            Thread.sleep(20);
        }
    }
}
