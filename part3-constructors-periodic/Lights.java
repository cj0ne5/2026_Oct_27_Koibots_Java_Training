public class Lights extends SubsystemBase {
    private final Flywheel flywheel;

    // A constructor can take parameters: Lights needs to know which flywheel to watch
    public Lights(Flywheel flywheel) {
        System.out.println("Lights constructor: watching the flywheel");
        this.flywheel = flywheel;
    }

    @Override
    public void periodic() {
        System.out.print(flywheel.atSpeed() ? "lights GREEN (shoot!)" : "lights RED (wait...)");
    }
}
