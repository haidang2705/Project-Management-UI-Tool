import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WorksOn implements DataIO{
    private int eNumber;
    private int pNumber;
    private int hours;

    public WorksOn(int eNumber, int pNumber, int hours) {
        this.eNumber = eNumber;
        this.pNumber = pNumber;
        this.hours = hours;
    }

    public WorksOn() {
    }

    public int geteNumber() {
        return eNumber;
    }

    public int getpNumber() {
        return pNumber;
    }

    public int getHours() {
        return hours;
    }

    @Override
    public void dataInput(Scanner scanner) {
        try {
            eNumber = scanner.nextInt();
            pNumber = scanner.nextInt();
            hours = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("Wrong input type. " + e);
            scanner.next(); //Jump over the string
        }
    }

    @Override
    public void dataOutput(Formatter formatter) {
        formatter.format("%s", toString());
    }

    @Override
    public String toString() {
        return eNumber + ", " +
               pNumber + ", " +
                hours;
    }
}
