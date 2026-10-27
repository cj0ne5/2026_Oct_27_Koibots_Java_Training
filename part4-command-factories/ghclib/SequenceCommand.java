package ghclib;

public class SequenceCommand extends Command {
    private final Command[] steps;
    private int index;

    public SequenceCommand(Command... steps) {
        this.steps = steps;
    }

    @Override
    public void initialize() {
        index = 0;
        if (steps.length > 0) {
            steps[0].initialize();
        }
        advance();
    }

    @Override
    public void execute() {
        if (index < steps.length) {
            steps[index].execute();
            advance();
        }
    }

    // Move on to the next step(s) once the current one says it's done
    private void advance() {
        while (index < steps.length && steps[index].isFinished()) {
            index++;
            if (index < steps.length) {
                steps[index].initialize();
            }
        }
    }

    @Override
    public boolean isFinished() {
        return index >= steps.length;
    }
}
