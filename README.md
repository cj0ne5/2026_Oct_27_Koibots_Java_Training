# Java for FRC: 1-hour newbie training

Each part is a folder with one class per file. Every folder has a `Main.java` with the `main` method. Plain Java, no WPILib needed.

To run a part, from inside its folder:

```
javac -d out $(find . -name "*.java") && java -cp out Main
```

(This needs a full JDK. The WPILib install includes one at `~/wpilib/2026/jdk/bin/`. In an online sandbox, upload all the files in the folder and run `Main`.)

| Time | Part | Folder | Key idea |
|---|---|---|---|
| 0:00–0:12 | 1. Variables and objects | `part1-variables/` | private / public, static, final |
| 0:12–0:24 | 2. Types and inheritance | `part2-inheritance/` | return types, `extends`, `super`, `@Override` |
| 0:24–0:36 | 3. Constructors and periodic | `part3-constructors-periodic/` | setup once vs. run every 20 ms |
| 0:36–0:52 | 4. Command factories | `part4-command-factories/` | methods that return "something to do later" |
| 0:52–1:00 | 5. The real thing | `Rebuilt-2026/.../subsystems/Indexer.java` | find each idea in the team's code |

## Part 1: Variables and objects (12 min)

- Files: `Main.java` creates and uses the robots; `Robot.java` is the class. Open both side by side.
- Run it. Ask: both robots pulled the same 250 amps, so why did only Bolt brown out? (Each object has its **own** `restingVolts`, and Bolt started with a less-charged battery.)
- The physics is real: voltage drops a little as a battery runs down, but brownouts come from **sag** under high current (`amps × INTERNAL_RESISTANCE_OHMS`). 6.8 V is the roboRIO's actual brownout threshold. This is why the team sets current limits on its motors (`smartCurrentLimit` in `Indexer.java`).
- Ask: why is `getRobotsBuilt()` 2 and not 1? (`static` means one copy **shared** by every Robot.)
- **Activity:** uncomment the three lines at the bottom of `main` one at a time and read the compiler errors.
  - `restingVolts` is `private`, so only Robot's own code can change it. That's why getters exist.
  - `BROWNOUT_VOLTS` is `final`, so it can't be changed after it's set.
- FRC link: `Constants.java` is full of `public static final` values like motor IDs and speeds.

## Part 2: Object types, return types, inheritance (12 min)

- Files: `Main.java`, `Mechanism.java` (parent), `Intake.java` and `Shooter.java` (children).
- Point out the return type of every method in `Mechanism`: `void`, `double`, `String`, `boolean`.
- `Intake extends Mechanism`: an Intake **is-a** Mechanism, so it gets every method for free.
- `Shooter` adds new methods and uses `@Override` to replace `describe()`. It also calls `super.describe()` to reuse the parent's version.
- **Activity:** uncomment `found.getRpm()`. The variable's type is `Mechanism`, so Java won't let you call Shooter-only methods, even though the object really is a Shooter.
- Ask: why does Shooter call `getSpeed()` instead of using `speed`? (It's `private` in the parent.)

## Part 3: Constructors and periodic (12 min)

- Files: `Main.java` (the robot loop), `SubsystemBase.java`, `Motor.java`, `Flywheel.java`, `Lights.java`.
- Run it and look at the order of the output: every constructor runs **once**, at boot.
- Then `periodic()` runs **every tick**. On the robot, WPILib calls it every 20 ms. **You never call it yourself.**
- Rule of thumb: create hardware in the constructor. Read sensors and update values in `periodic()`.
- `Lights` takes the flywheel as a constructor parameter, so one object can watch another.
- **Activity:** change the target to 5000 RPM, or the `0.3` in `Motor`. How many ticks until the lights go green?

## Part 4: Command factories (16 min)

- Files: `Main.java`, `Indexer.java`, `IndexerCommands.java`, `IndexerConstants.java`, and the `ghclib/` folder (GHCLib, our stand-in for WPILib).
- `ghclib/` is a **package**: its files start with `package ghclib;`, and `Indexer.java` uses `import ghclib.Command;`. The real code does the same thing with `import edu.wpi.first.wpilibj2.command.Command;`.
- Start at the top of `main`: `setSpeedCommand(1.0)` makes a command, but the motor **doesn't move**. A command is an object that describes an action. The scheduler decides when it runs.
- Walk through `Indexer`:
  - `setSpeed` is **private** and does the work right now.
  - `setSpeedCommand` is **public**. It's the factory, and it returns a `Command`.
  - `() -> this.setSpeed(percent)` is a **lambda**: code saved to run later. (New for most students; spend a minute here.)
- `IndexerCommands.unjam` is a **static** factory that builds a bigger command from small ones, the same way the team's `ShootCommands.java` does.
- Optional: open the `ghclib/` folder. It's ordinary classes using inheritance (`RunOnceCommand extends Command`), static methods (`Commands.sequence`), and a loop (`CommandScheduler.run`). There's no magic.
- **Activity:** in `IndexerCommands.java`, write a factory `pulse(indexer)` that runs forward for 0.1 s, stops, then runs forward again. Schedule it at tick 12 in `Main.java`.

## Part 5: Find it in the real robot code (8 min)

Open `src/main/java/frc/robot/subsystems/Indexer.java` from the repo next to `part4-command-factories/Indexer.java`. Have students find:

- [ ] `extends SubsystemBase` (inheritance)
- [ ] a `private final` field (`motor`)
- [ ] the constructor, and what it sets up
- [ ] `@Override public void periodic()`, and what it does every 20 ms
- [ ] the private method and the public command factory that wraps it
- [ ] a `static final` constant from `Constants.java` (`IndexerConstants.MOTOR_ID`)

Then one more step: in `RobotContainer.java`, find where `indexer.setSpeedCommand(...)` is attached to a controller button (`intakeReverse.onTrue(...)`).

**Differences students will notice in the real file:** it uses `SparkMax` instead of our `Motor`; unit types (`Amps.of(...)`, `RPM.of(...)`) instead of plain `double`; `@Logged`/`@NotLogged` instead of `toString()`; and `var` for the motor config.
