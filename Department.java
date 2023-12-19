import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Department implements DataIO{
    private int number;
    private String name;
    private int manager;
    private double budget;
    private String startDate;

    public Department(int number, String name, int manager, double budget, String startDate) {
        this.number = number;
        this.name = name;
        this.manager = manager;
        this.budget = budget;
        this.startDate = startDate;
    }

    public Department() {
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getManager() {
        return manager;
    }

    public double getBudget() {
        return budget;
    }

    public String getStartDate() {
        return startDate;
    }

    @Override
    public void dataInput(Scanner scanner) {
        try {
            number = scanner.nextInt();
            name = scanner.next();
            manager = scanner.nextInt();

            budget = scanner.nextDouble();
            startDate = scanner.next();

        }
        catch (InputMismatchException e) {
            System.out.println("Wrong input type. " + e);
            scanner.next(); //Jump over the string
        }
    }

    @Override
    public void dataOutput(Formatter formatter) {

    }
}
