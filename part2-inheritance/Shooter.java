public class Shooter extends Mechanism {
    public static final double MAX_RPM = 5000;

    public Shooter() {
        super("Shooter");
    }

    // New methods only a Shooter has
    public double getRpm() {
        return getSpeed() * MAX_RPM; // can't touch the private 'speed' field directly!
    }

    public boolean isReadyToShoot() {
        return getRpm() > 4000;
    }

    // Replace the parent's version of describe(), but reuse it with super
    @Override
    public String describe() {
        return super.describe() + " (" + Math.round(getRpm()) + " RPM)";
    }
}
