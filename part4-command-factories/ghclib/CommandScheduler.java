package ghclib;

import java.util.ArrayList;
import java.util.List;

public class CommandScheduler {
    private static final List<SubsystemBase> subsystems = new ArrayList<>();
    private static final List<Command> running = new ArrayList<>();

    public static void registerSubsystem(SubsystemBase subsystem) {
        subsystems.add(subsystem);
    }

    public static void schedule(Command command) {
        command.initialize();
        if (!command.isFinished()) {
            running.add(command);
        }
    }

    // Called every 20 ms: update every subsystem, then push every running command forward
    public static void run() {
        for (SubsystemBase s : subsystems) {
            s.periodic();
        }
        for (Command c : new ArrayList<>(running)) {
            c.execute();
            if (c.isFinished()) {
                running.remove(c);
            }
        }
    }
}
