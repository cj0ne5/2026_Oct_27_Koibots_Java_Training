// GHCLib: our stand-in for WPILib. On the real robot, WPILib and vendor libraries provide these.
// It's here so you can see there's no magic: it's all classes and methods.
package ghclib;

// Pretend hardware. Stands in for the real motor controller (a SparkMax).
public class Motor {
    private double power;

    public Motor(int canId) {
        System.out.println("Motor on CAN ID " + canId + " connected");
    }

    public void setCurrentLimit(int amps) {}

    public void set(double power) {
        this.power = power;
    }

    public double getVelocity() {
        return power * 5000;
    }

    public double getOutputCurrent() {
        return Math.abs(power) * 20;
    }
}
