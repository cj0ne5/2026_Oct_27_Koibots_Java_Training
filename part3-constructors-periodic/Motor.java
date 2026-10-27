// Pretend hardware. Stands in for the real motor controller (a SparkMax).
public class Motor {
    private double power;
    private double rpm;

    public Motor(int canId) {
        System.out.println("  Motor on CAN ID " + canId + " connected");
    }

    public void set(double power) {
        this.power = power;
    }

    // Pretend physics: every time we check, the wheel has spun up a bit closer to its target
    public double getVelocity() {
        rpm += (power * 5000 - rpm) * 0.3;
        return rpm;
    }
}
