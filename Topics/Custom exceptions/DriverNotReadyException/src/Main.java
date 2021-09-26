import java.util.Scanner;

class Driver {
    String name;
    Boolean isDoorLocked;
    Boolean isBeltFasten;

    public Driver(String name, Boolean isDoorLocked, Boolean isBeltFasten) {
        this.name = name;
        this.isDoorLocked = isDoorLocked;
        this.isBeltFasten = isBeltFasten;

    }

    public void closeDoor() {
        this.isDoorLocked = true;
    }

    public void fastenBelt() {
        this.isBeltFasten = true;
    }

    public void drive(boolean isBeltFasten, boolean isDoorLocked) throws DriverNotReadyException {
        if (isBeltFasten && isDoorLocked) {
            System.out.println("Here we go");
        } else {
            throw new DriverNotReadyException("Close the door or fasten your seatbelt");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        Boolean isDoorLocked = scanner.nextBoolean();
        Boolean isBeltFasten = scanner.nextBoolean();
        Driver driver = new Driver(name, isDoorLocked, isBeltFasten);
        try {
            driver.drive(isBeltFasten, isDoorLocked);
        } catch (DriverNotReadyException e) {
            System.out.println(e.getMessage());
        }
    }
}

// please skip a code style report about serialVersionUID 
class DriverNotReadyException extends Exception {
    public DriverNotReadyException(String message) {
        super(message);
    }
}