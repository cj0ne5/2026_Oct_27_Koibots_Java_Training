package ghclib;

// A Command is an object that knows how to start, keep going, and tell when it's done
public abstract class Command {
    public void initialize() {}

    public void execute() {}

    public boolean isFinished() {
        return true;
    }
}
