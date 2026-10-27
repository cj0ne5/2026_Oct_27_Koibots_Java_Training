// Matches the real Indexer, minus the units library and logging

import ghclib.Command;
import ghclib.Commands;
import ghclib.Motor;
import ghclib.SubsystemBase;

public class Indexer extends SubsystemBase {
    private final Motor motor;

    private double current;
    private double velocity;

    // Constructor: set up the hardware once
    public Indexer() {
        this.motor = new Motor(IndexerConstants.MOTOR_ID);
        this.motor.setCurrentLimit(IndexerConstants.MAX_MOTOR_CURRENT_AMPS);
        readValues();
    }

    // Runs every 20 ms, called by the scheduler
    @Override
    public void periodic() {
        readValues();
    }

    public void readValues() {
        this.current = motor.getOutputCurrent();
        this.velocity = motor.getVelocity();
    }

    // PRIVATE: does the thing right now. Nobody outside Indexer can call this.
    private void setSpeed(double speedPercent) {
        this.motor.set(speedPercent);
    }

    // PUBLIC COMMAND FACTORY: returns a Command that will call setSpeed LATER,
    // whenever the scheduler runs it.
    // () -> this.setSpeed(percent) is a lambda: a little chunk of code saved for later.
    public Command setSpeedCommand(double percent) {
        return Commands.runOnce(() -> this.setSpeed(percent), this);
    }

    // The real Indexer uses @Logged to record these. We just print them.
    @Override
    public String toString() {
        return String.format("indexer: %5.0f RPM, %4.1f A", velocity, current);
    }
}
