import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Optional;
import java.util.Scanner;

public class DEP extends Application implements EventHandler<ActionEvent> {
    private ArrayList<Department> departments;
    private ArrayList<Employee> employees;
    private ArrayList<Project> projects;
    private ArrayList<WorksOn> worksOns;
    //    private DEP dep;
    private GridPane departmentPane;
    private GridPane employeePane;
    private GridPane projectPane;
    private GridPane worksOnPane;

    ScrollPane departmentScroll, employeeScroll, projectScroll, worksOnScroll;

    private ListView<String> departmentList;
    private ListView<String> employeeList;
    private ListView<String> projectList;
    private ListView<String> worksOnList;
    private PieChart pieChart;
    private PieChart.Data edu, res, fin, com, sale, acct, game, humanRes, spo;
    private Label dept = new Label("Department");
    private Label emp = new Label("Employee");
    private Label projectLabel = new Label("Project");
    private Label work = new Label("Works on");
    private Label caption = new Label("");

    private TextField deptName, manager, deptBudget, startDate;
    private TextField empName, dob, address, gender, salary, supervisor, empDept, skillLang;
    private TextField title, sponsor, projDept, projBudget;
    private TextField empWorkOnNum, projWorkOnNum, hour, message;

    private Button add, delete, save;
    private Dialog<WorksOn> addWorksOnDialog;
    private DialogPane addWorksOnPane;

    private Employee selectedEmp;
    private Project selectedProj;

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public ArrayList<WorksOn> getWorksOns() {
        return worksOns;
    }

    public DEP() {

    }

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        departments = new ArrayList<>();
        employees = new ArrayList<>();
        projects = new ArrayList<>();
        worksOns = new ArrayList<>();

        loadDepartments();
        loadEmployees();
        loadProjects();
        loadWorksOn();

        departmentPane = new GridPane();
        departmentPane.setStyle("-fx-background-color: white");
        departmentPane.setHgap(10);
        departmentPane.setVgap(5);

        employeePane = new GridPane();
        employeePane.setStyle("-fx-background-color: white");
        employeePane.setHgap(10);
        employeePane.setVgap(5);

        projectPane = new GridPane();
        projectPane.setStyle("-fx-background-color: white");
        projectPane.setHgap(10);
        projectPane.setVgap(5);

        worksOnPane = new GridPane();
        worksOnPane.setStyle("-fx-background-color: white");
        worksOnPane.setHgap(10);
        worksOnPane.setVgap(5);

        departmentScroll = new ScrollPane();
        employeeScroll = new ScrollPane();
        projectScroll = new ScrollPane();
        worksOnScroll = new ScrollPane();

        departmentScroll.setPrefViewportHeight(120);
        departmentScroll.setPrefViewportWidth(260);

        employeeScroll.setPrefViewportHeight(120);
        employeeScroll.setPrefViewportWidth(260);


        projectScroll.setPrefViewportHeight(120);
        projectScroll.setPrefViewportWidth(260);


        worksOnScroll.setPrefViewportHeight(120);
        worksOnScroll.setPrefViewportWidth(610);

        departmentScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        departmentScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        employeeScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        employeeScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        projectScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        projectScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        worksOnScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        worksOnScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        HBox.setHgrow(departmentPane, Priority.ALWAYS);
        HBox.setHgrow(employeePane, Priority.ALWAYS);
        HBox.setHgrow(projectPane, Priority.ALWAYS);
        HBox.setHgrow(worksOnPane, Priority.ALWAYS);

        departmentScroll.setPrefViewportWidth(240);
        employeeScroll.setPrefViewportWidth(240);
        projectScroll.setPrefViewportWidth(240);
        worksOnScroll.setPrefViewportWidth(580);

        departmentPane.getColumnConstraints().addAll(
                new ColumnConstraints(120),
                new ColumnConstraints(180)
        );
        departmentPane.add(dept, 0, 0);
        departmentPane.add(new Label("information"), 1, 0);
        departmentPane.add(departmentScroll, 1, 1);
        GridPane.setHalignment(new Label("information"), HPos.RIGHT);
        GridPane.setMargin(dept, new Insets(0, 0, 0, 30));

        employeePane.getColumnConstraints().addAll(
                new ColumnConstraints(120),
                new ColumnConstraints(180)
        );
        employeePane.add(emp, 0, 0);
        employeePane.add(new Label("information"), 1, 0);
        employeePane.add(employeeScroll, 1, 1);
        GridPane.setHalignment(new Label("information"), HPos.RIGHT);
        GridPane.setMargin(emp, new Insets(0, 0, 0, 30));

        projectPane.getColumnConstraints().addAll(
                new ColumnConstraints(120),
                new ColumnConstraints(180)
        );
        projectPane.add(projectLabel, 0, 0);
        projectPane.add(new Label("information"), 1, 0);
        projectPane.add(projectScroll, 1, 1);
        GridPane.setHalignment(new Label("information"), HPos.RIGHT);
        GridPane.setMargin(projectLabel, new Insets(0, 0, 0, 30));

        worksOnPane.getColumnConstraints().addAll(
                new ColumnConstraints(120),
                new ColumnConstraints(560)
        );
        worksOnPane.add(work, 0, 0);
        worksOnPane.add(new Label("information"), 1, 0);
        worksOnPane.add(worksOnScroll, 1, 1);
        GridPane.setHalignment(new Label("information"), HPos.RIGHT);
        GridPane.setMargin(work, new Insets(0, 0, 0, 30));

        departmentPane.add(new Label("Department\nNumber"), 0, 1);
        departmentPane.add(new Label("Name"), 0, 2);
        departmentPane.add(new Label("Manager"), 0, 3);
        departmentPane.add(new Label("Budget"), 0, 4);
        departmentPane.add(new Label("Start date"), 0, 5);
        deptName = new TextField();
        departmentPane.add(deptName, 1, 2);
        manager = new TextField();
        departmentPane.add(manager, 1, 3);
        deptBudget = new TextField();
        departmentPane.add(deptBudget, 1, 4);
        startDate = new TextField();
        departmentPane.add(startDate, 1, 5);


        employeePane.add(new Label("Employee\nNumber"), 0, 1);
        employeePane.add(new Label("Name"), 0, 2);
        employeePane.add(new Label("DOB"), 0, 3);
        employeePane.add(new Label("Address"), 0, 4);
        employeePane.add(new Label("Gender"), 0, 5);
        employeePane.add(new Label("Salary"), 0, 6);
        employeePane.add(new Label("Supervisor"), 0, 7);
        employeePane.add(new Label("Department"), 0, 8);
        employeePane.add(new Label("Skill/Language"), 0, 9);
        empName = new TextField();
        employeePane.add(empName, 1, 2);
        dob = new TextField();
        employeePane.add(dob, 1, 3);
        address = new TextField();
        employeePane.add(address, 1, 4);
        gender = new TextField();
        employeePane.add(gender, 1, 5);
        salary = new TextField();
        employeePane.add(salary, 1, 6);
        supervisor = new TextField();
        employeePane.add(supervisor, 1, 7);
        empDept = new TextField();
        employeePane.add(empDept, 1, 8);
        skillLang = new TextField();
        employeePane.add(skillLang, 1, 9);

        projectPane.add(new Label("Project\nNumber"), 0, 1);
        projectPane.add(new Label("Title"), 0, 2);
        projectPane.add(new Label("Sponsor"), 0, 3);
        projectPane.add(new Label("Department"), 0, 4);
        projectPane.add(new Label("Budget"), 0, 5);
        title = new TextField();
        projectPane.add(title, 1, 2);
        sponsor = new TextField();
        projectPane.add(sponsor, 1, 3);
        projDept = new TextField();
        projectPane.add(projDept, 1, 4);
        projBudget = new TextField();
        projectPane.add(projBudget, 1, 5);

        worksOnPane.add(new Label("Works on"), 0, 1);
        worksOnPane.add(new Label("Employee\nNumber"), 0, 2);
        worksOnPane.add(new Label("Project\nNumber"), 0, 3);
        worksOnPane.add(new Label("Hours"), 0, 4);
        worksOnPane.add(new Label("Message"), 0, 15);
        empWorkOnNum = new TextField();
        worksOnPane.add(empWorkOnNum, 1, 2);
        projWorkOnNum = new TextField();
        worksOnPane.add(projWorkOnNum, 1, 3);
        hour = new TextField();
        worksOnPane.add(hour, 1, 4);

        //set up pie chart
        pieChart = new PieChart();
        res = new PieChart.Data("Research", 6);
        edu = new PieChart.Data("Education", 7);
        fin = new PieChart.Data("Finance", 8);
        com = new PieChart.Data("Computer", 9);
        sale = new PieChart.Data("Sales", 1);
        acct = new PieChart.Data("Accounting", 2);
        game = new PieChart.Data("Games", 3);
        humanRes = new PieChart.Data("Human Resources", 4);
        spo = new PieChart.Data("Sport", 5);

        pieChart.getData().add(res);
        pieChart.getData().add(edu);
        pieChart.getData().add(fin);
        pieChart.getData().add(com);
        pieChart.getData().add(sale);
        pieChart.getData().add(acct);
        pieChart.getData().add(game);
        pieChart.getData().add(humanRes);
        pieChart.getData().add(spo);
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 24 arial;");

        message = new TextField();
        worksOnPane.add(message, 1, 15);

        add = new Button("Add");
        add.setOnAction(this);
        delete = new Button("Delete");
        delete.setOnAction(this);
        save = new Button("Save");
        save.setOnAction(this);
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(add, delete, save);
        buttonBox.setAlignment(Pos.CENTER);
        worksOnPane.add(buttonBox, 1, 5);

        departmentScroll.setContent(departmentList);
        projectScroll.setContent(projectList);
        worksOnScroll.setContent(worksOnList);
        employeeScroll.setContent(employeeList);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(departmentPane, employeePane, projectPane, worksOnPane);

        departmentPane.setPrefWidth(100);
        employeePane.setPrefWidth(100);
        projectPane.setPrefWidth(100);
        worksOnPane.setPrefWidth(400);
        pieChart.setPrefWidth(350);
        pieChart.setPrefHeight(350);

        VBox vbox = new VBox();
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(pieChart);
        vbox.getChildren().add(caption);

        VBox.setVgrow(hbox, Priority.ALWAYS);
        VBox.setVgrow(pieChart, Priority.ALWAYS);

        root.setCenter(vbox);
        root.setPadding(new Insets(0, 20, 0, 20));


        Scene scene = new Scene(root, 1700, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Department, employee and project management system");
        primaryStage.show();


    }

    public Object getObj(String number) {
        for (Department dept : departments) {
            if (dept.getNumber() == Integer.parseInt(number))
                return dept;
        }
        for (Employee emp : employees) {

            if (emp.getNumber() == Integer.parseInt(number))
                return emp;
        }
        for (Project project : projects) {
            if (project.getNumber() == Integer.parseInt(number))
                return project;
        }
        return null;
    }

    public void loadDepartments() {
        String filename = "departments.txt";
        Path path = Paths.get(filename);

        try {
            if (Files.exists(path)) {
                if (!Files.isDirectory(path)) { //Not a directory, read data
                    Scanner fin = new Scanner(path);
                    fin.useDelimiter(", |\r\n|\t|\n");
                    //Clear the container
                    departments.clear();
                    while (fin.hasNext()) {
                        Department department = new Department();
                        department.dataInput(fin);
                        departments.add(department);
                    }
                    fin.close();
                } else
                    System.out.printf("File %s does not exist", path);
            }

            departmentList = new ListView<>();
            for (Department dept : departments
            ) {
                departmentList.getItems().add(String.valueOf(dept.getNumber()));
            }
            departmentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue v, String oldV, String newV) {
                    //System.out.println("Change old value " + oldV + " to new value " + newV);
                    Department department = (Department) getObj(newV);
                    //System.out.println(d);
                    if (department != null) {
                        deptName.setText(department.getName());
                        manager.setText(String.format("%d", department.getManager()));
                        deptBudget.setText(String.format("%.2f", department.getBudget()));
                        startDate.setText(department.getStartDate());
                    } else {
                        message.setText(String.format("Cannot find the account %s", newV));
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEmployees() {
        String filename = "employees.txt";
        Path path = Paths.get(filename);

        try {
            if (Files.exists(path)) {
                if (!Files.isDirectory(path)) { //Not a directory, read data
                    Scanner fin = new Scanner(path);
                    fin.useDelimiter(", |\r\n|\t|\n");
                    //Clear the container
                    employees.clear();
                    while (fin.hasNext()) {
                        Employee employee = new Employee();
                        employee.dataInput(fin);
                        if (employee.getType().equalsIgnoreCase("D")) {
                            Employee dev = new Developer();
                            dev = employee;
                            employees.add(employee);
                        } else {
                            Employee admin = new Admin();
                            admin = employee;
                            employees.add(admin);
                        }
                    }
                    fin.close();
                } else
                    System.out.printf("File %s does not exist", path);
            }
            employeeList = new ListView<>();
            for (Employee employee : employees
            ) {
                employeeList.getItems().add(String.valueOf(employee.getNumber()));
            }
            employeeList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue v, String oldV, String newV) {
                    //System.out.println("Change old value " + oldV + " to new value " + newV);
                    Employee emp = (Employee) getObj(newV);
                    //System.out.println(d);
                    if (emp != null) {
                        empName.setText(emp.getName());
                        dob.setText(String.format("%s", emp.getDob()));
                        address.setText(String.format("%s", emp.getAddress()));
                        gender.setText(emp.getGender());
                        salary.setText(String.valueOf(emp.getSalary()));
                        supervisor.setText(String.valueOf(emp.getSupervisor()));
                        empDept.setText(String.valueOf(emp.getdNumber()));
                        skillLang.setText(emp.getSkillLang());
                        selectedEmp = emp;
                    } else {
                        message.setText(String.format("Cannot find the account %s", newV));
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadProjects() {
        String filename = "projects.txt";
        Path path = Paths.get(filename);

        try {
            if (Files.exists(path)) {
                if (!Files.isDirectory(path)) { //Not a directory, read data
                    Scanner fin = new Scanner(path);
                    fin.useDelimiter(", |\r\n|\t|\n");
                    //Clear the container
                    projects.clear();
                    while (fin.hasNext()) {
                        Project project = new Project();
                        project.dataInput(fin);
                        projects.add(project);
                    }
                    fin.close();
                } else
                    System.out.printf("File %s does not exist", path);
            }

            projectList = new ListView<>();
            for (Project project : projects
            ) {
                projectList.getItems().add(String.valueOf(project.getNumber()));
            }
            projectList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue v, String oldV, String newV) {
                    //System.out.println("Change old value " + oldV + " to new value " + newV);
                    Project project = (Project) getObj(newV);
                    //System.out.println(d);
                    if (project != null) {
                        title.setText(project.getTitle());
                        sponsor.setText(project.getSponsor());
                        projDept.setText(String.valueOf(project.getdNumber()));
                        projBudget.setText(String.format("%.2f", project.getBudget()));
                        selectedProj = project;
                    } else {
                        message.setText(String.format("Cannot find the account %s", newV));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadWorksOn() {
        String filename = "workson.txt";
        Path path = Paths.get(filename);

        try {
            if (Files.exists(path)) {
                if (!Files.isDirectory(path)) { //Not a directory, read data
                    Scanner fin = new Scanner(path);
                    fin.useDelimiter(", |\r\n|\t|\n");
                    //Clear the container
                    worksOns.clear();
                    while (fin.hasNext()) {
                        WorksOn worksOn = new WorksOn();
                        worksOn.dataInput(fin);
                        worksOns.add(worksOn);
                    }
                    fin.close();
                } else
                    System.out.printf("File %s does not exist", path);
            }
            worksOnList = new ListView<>();
            for (WorksOn worksOn : worksOns
            ) {
                worksOnList.getItems().add(worksOn.toString());
            }
            worksOnList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue v, String oldV, String newV) {
                    //System.out.println("Change old value " + oldV + " to new value " + newV);
                    //System.out.println(d);
                    String[] data = newV.split(", |\n" + "|\t|\n");
                    if (!worksOnList.getItems().isEmpty()) {
                        empWorkOnNum.setText(data[0]);
                        projWorkOnNum.setText(data[1]);
                        hour.setText(data[2]);
                    } else {
                        message.setText(String.format("Cannot find the account %s", newV));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == add) {//Add button clicked
            addWorksOn();
        } else if (event.getSource() == delete) { //Delete button clicked
            deleteWorksOn();
        } else if (event.getSource() == save) { //Save button clicked
            saveToFiles();
            message.setText(String.format("%d works on are saved", worksOns.size()));
        }
    }

    public void saveToFiles() {
        String filename = "workson.txt";

        try {
            Formatter formatter = new Formatter(filename);
            for (WorksOn work : worksOns
            ) {
                work.dataOutput(formatter);
                formatter.format("\n");
            }
            formatter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteWorksOn() {
        int i = worksOnList.getSelectionModel().getSelectedIndex();
        WorksOn worksOn;
        if (i < 0) { //No item selected
            message.setText("Select a works on from the Works on list");
        } else {
            worksOn = worksOns.get(i);
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm the removal");
            confirm.setContentText("Are you sure?");
            confirm.showAndWait();
            if (confirm.getResult() == ButtonType.OK) { //Confirmed
                //Remove the selected item from the list
                worksOnList.getItems().remove(i);
                //Remove the account from the array list
                worksOns.remove(i);
                message.setText(String.format("Works on %s has been removed", worksOn.toString()));
            } else {
                message.setText("Cannot remove works on!!!");
            }
        }
    }

    public void addWorksOn() {

        if (!isWorkingOn()) {

            if (selectedEmp == null || selectedEmp.getType().equalsIgnoreCase("A")) {
                message.setText("Please select a developer");
            } else if (selectedProj == null)
                message.setText("Please select a project");

            else {
                TextInputDialog td = new TextInputDialog();
                // setHeaderText
                td.setHeaderText("Enter Hours");
                Optional<String> result = new TextInputDialog().showAndWait();
                if (result.isPresent()) {
                    WorksOn worksOn = new WorksOn(selectedEmp.getNumber(), selectedProj.getNumber(), Integer.parseInt(result.get()));
                    worksOns.add(worksOn);
                    worksOnList.getItems().add(worksOn.toString());
                    message.setText(String.format("Employee %d works on %s is added", selectedEmp.getNumber(), selectedProj.getNumber()));
                }
            }
        }

    }
        public boolean isWorkingOn () {
            String str = "";
            ArrayList<String> workingOn = new ArrayList<>();
            for (WorksOn worksOn : worksOns) {
                if (worksOn.geteNumber() == selectedEmp.getNumber()) {
                    workingOn.add(String.valueOf(worksOn.getpNumber()));
                }
            }
            if (workingOn.size() > 0) {
                for (int i = 0; i < workingOn.size(); i++) {
                    str += " " + workingOn.get(i) + ",";
                }
                str = str.substring(0, str.length() - 1);
                message.setText(String.format("Employee %d already work on%s", selectedEmp.getNumber(), str));
                return true;
            }
            return false;
        }
    }
