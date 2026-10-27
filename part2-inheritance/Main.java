// Part 2: Object types, method return types, class inheritance

public class Main {
    public static void main(String[] args) {
        // The array's type is Mechanism, but it holds an Intake and a Shooter.
        // That works because an Intake IS-A Mechanism and a Shooter IS-A Mechanism.
        Mechanism[] mechanisms = {new Intake(), new Shooter()};

        for (Mechanism m : mechanisms) {
            m.setSpeed(0.9);
            System.out.println(m.describe()); // each object uses its OWN version of describe()
        }

        Mechanism found = findByName(mechanisms, "Shooter"); // a method can return an object
        System.out.println("Found: " + found.getName() + ", running? " + found.isRunning());

        // found.getRpm();  // Uncomment: why doesn't this compile, even though it IS a Shooter?

        Shooter shooter = (Shooter) found; // now Java knows it's a Shooter
        double rpm = shooter.getRpm();
        boolean ready = shooter.isReadyToShoot();
        System.out.printf("Shooter at %.0f RPM, ready to shoot? %b%n", rpm, ready);
    }

    // Return type: Mechanism. Could hand back an Intake, a Shooter, or null.
    public static Mechanism findByName(Mechanism[] list, String name) {
        for (Mechanism m : list) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }
}
