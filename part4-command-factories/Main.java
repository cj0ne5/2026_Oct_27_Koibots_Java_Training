// Part 4: Command factories, applied to the team's Indexer
//
// Indexer.java is written to match the real one in
// Rebuilt-2026/src/main/java/frc/robot/subsystems/Indexer.java. Compare them side by side!
// The ghclib/ folder is GHCLib: our own tiny stand-in for WPILib, so this runs anywhere.

import ghclib.Command;
import ghclib.CommandScheduler;

public class Main {
    public static void main(String[] args) {
        Indexer indexer = new Indexer();

        // A command factory gives you a command object. It does NOT run it yet.
        Command spin = indexer.setSpeedCommand(1.0);
        System.out.println("Made a command. Motor is still: " + indexer);

        // On the robot, buttons schedule commands (RobotContainer.configureBindings).
        // Here we fake a driver pressing buttons at certain times.
        for (int tick = 1; tick <= 14; tick++) {
            if (tick == 2) {
                System.out.println("  >> Driver PRESSES shoot button");
                CommandScheduler.schedule(indexer.setSpeedCommand(IndexerConstants.SHOOTING_SPEED));
            }
            if (tick == 5) {
                System.out.println("  >> Driver RELEASES shoot button");
                CommandScheduler.schedule(indexer.setSpeedCommand(0));
            }
            if (tick == 8) {
                System.out.println("  >> Driver presses UNJAM button");
                CommandScheduler.schedule(IndexerCommands.unjam(indexer));
            }

            CommandScheduler.run(); // Robot.robotPeriodic() does this every 20 ms
            System.out.printf("t=%.2fs  %s%n", tick * 0.02, indexer);
        }
    }
}
