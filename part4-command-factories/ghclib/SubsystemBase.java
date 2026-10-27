package ghclib;

public class SubsystemBase {
    // Every subsystem signs itself up with the scheduler when it's constructed
    public SubsystemBase() {
        CommandScheduler.registerSubsystem(this);
    }

    public void periodic() {}
}
