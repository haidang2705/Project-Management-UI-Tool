import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Employee implements DataIO {
    private String type;
    private int number;
    private String name;
    private String dob;
    private String address;
    private String gender;
    private double salary;
    private int supervisor;
    private int dNumber;
    private String skillLang;

    public Employee(String type, int number, String name, String dob, String address, String gender, double salary, int supervisor, int dNumber, String skillLang) {
        this.type = type;
        this.number = number;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.salary = salary;
        this.supervisor = supervisor;
        this.dNumber = dNumber;
        this.skillLang = skillLang;
    }

    public Employee() {
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public double getSalary() {
        return salary;
    }

    public int getSupervisor() {
        return supervisor;
    }

    public int getdNumber() {
        return dNumber;
    }

    @Override
    public void dataInput(Scanner scanner) {

        try {
            type = scanner.next();
            number = scanner.nextInt();
            name = scanner.next();
            dob = scanner.next();
            address = scanner.next();
            gender = scanner.next();
            salary = scanner.nextDouble();
            supervisor = scanner.nextInt();
            dNumber = scanner.nextInt();
            skillLang = scanner.next();
        }
        catch (InputMismatchException e) {
            System.out.println("Wrong input type. " + e);
            scanner.next(); //Jump over the string
        }
    }

    @Override
    public void dataOutput(Formatter formatter) {

    }

    public String toString() {
        return super.toString();
    }

    public String getSkillLang() {
        return skillLang;
    }

    public void setSkillLang(String skillLang) {
        this.skillLang = skillLang;
    }
}

class Admin extends Employee {
    private String skills;

    public Admin(int number, String name, String dob, String address, String gender, double salary, int supervisor, int dNumber, String skills, String type) {
        super(type, number, name, dob, address, gender, salary, supervisor, dNumber, skills);
        this.skills = skills;
    }

    public Admin() {
    }

    public String getSkills() {
        return skills;
    }
}

class Developer extends Employee {
    private String languages;

    public Developer(int number, String name, String dob, String address, String gender, double salary, int supervisor, int dNumber, String languages, String type) {
        super(type, number, name, dob, address, gender, salary, supervisor, dNumber, languages);
        this.languages = languages;
    }

    public Developer() {
    }

    public String getLanguages() {
        return languages;
    }

}
