package ghclib;

public class WaitCommand extends Command {
    private final int ticks;
    private int elapsed;

    public WaitCommand(int ticks) {
        this.ticks = ticks;
    }

    @Override
    public void initialize() {
        elapsed = 0;
    }

    @Override
    public void execute() {
        elapsed++;
    }

    @Override
    public boolean isFinished() {
        return elapsed >= ticks;
    }
}
