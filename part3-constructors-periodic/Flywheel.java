public class Flywheel extends SubsystemBase {
    private final Motor motor;
    private double targetRpm;
    private double currentRpm;

    // Constructor: one-time SETUP. Create the hardware.
    public Flywheel() {
        System.out.println("Flywheel constructor:");
        motor = new Motor(12);
    }

    public void setTargetRpm(double rpm) {
        targetRpm = rpm;
        motor.set(rpm / 5000);
    }

    public boolean atSpeed() {
        return Math.abs(targetRpm - currentRpm) < 200;
    }

    // periodic: runs over and over. Read sensors, keep values up to date.
    @Override
    public void periodic() {
        currentRpm = motor.getVelocity();
        System.out.printf("flywheel %4.0f RPM  ", currentRpm);
    }
}
