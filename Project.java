import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project implements DataIO{
    private int number;
    private String title;
    private String sponsor;
    private int dNumber;
    private double budget;

    public Project(int number, String title, String sponsor, int dNumber, double budget) {
        this.number = number;
        this.title = title;
        this.sponsor = sponsor;
        this.dNumber = dNumber;
        this.budget = budget;
    }

    public Project() {
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getSponsor() {
        return sponsor;
    }

    public int getdNumber() {
        return dNumber;
    }

    public double getBudget() {
        return budget;
    }

    @Override
    public void dataInput(Scanner scanner){
        try {
            number = scanner.nextInt();
            title = scanner.next();
            sponsor = scanner.next();

            dNumber = scanner.nextInt();
            budget = scanner.nextDouble();

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
