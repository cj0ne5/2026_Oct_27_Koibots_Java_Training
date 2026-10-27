package ghclib;

public class RunOnceCommand extends Command {
    private final Runnable action;

    public RunOnceCommand(Runnable action) {
        this.action = action;
    }

    @Override
    public void initialize() {
        action.run(); // finally runs the lambda
    }
}
