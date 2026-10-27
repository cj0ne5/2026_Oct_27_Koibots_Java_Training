package ghclib;

// Static factory methods: the same names the team uses (Commands.runOnce, Commands.sequence...)
public class Commands {
    // Real WPILib uses the "requirements" to stop two commands fighting over one motor.
    // We accept them so the code looks the same, but ignore them.
    public static Command runOnce(Runnable action, SubsystemBase... requirements) {
        return new RunOnceCommand(action);
    }

    public static Command waitSeconds(double seconds) {
        return new WaitCommand((int) Math.round(seconds / 0.02));
    }

    public static Command sequence(Command... commands) {
        return new SequenceCommand(commands);
    }
}
