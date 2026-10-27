// A STATIC factory class, like ShootCommands.java in the repo.
// No fields, never use "new". It just builds bigger commands out of smaller ones.

import ghclib.Command;
import ghclib.Commands;

public class IndexerCommands {
    public static Command unjam(Indexer indexer) {
        return Commands.sequence(
                indexer.setSpeedCommand(-0.5), // run backwards...
                Commands.waitSeconds(0.06), // ...for 3 ticks...
                indexer.setSpeedCommand(0)); // ...then stop
    }
}
