package org.example.datastruct3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;



public class Driver extends Application {
    private Stage primaryStage;
    private Scene mainScene;
    private Scene selectScene;
    private Scene managementScene;
    private Scene statisticsScene;
    private Scene saveScene;
    private File selectedFile;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    AVL<YearNode> yearTree = new AVL<>();

    public String monthNumToName(int month) {
        String monthName = "";
        switch (month) {
            case 1:
                monthName = "Jan";
                break;
            case 2:
                monthName = "Feb";
                break;
            case 3:
                monthName = "Mar";
                break;
            case 4:
                monthName = "Apr";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "Jun";
                break;
            case 7:
                monthName = "Jul";
                break;
            case 8:
                monthName = "Aug";
                break;
            case 9:
                monthName = "Sep";
                break;
            case 10:
                monthName = "Oct";
                break;
            case 11:
                monthName = "Nov";
                break;
            case 12:
                monthName = "Dec";
        }
        return monthName;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;


        // Create a File Chooser instance
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");



        // Create a button to trigger file selection
        Button selectButton = new Button("Select File");
        Label nameLabel = new Label("Mohammed Shabaneh\n1201297");
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        nameLabel.setTextAlignment(TextAlignment.CENTER);
        selectButton.setOnAction(e -> {
                    selectedFile = fileChooser.showOpenDialog(primaryStage);
                    if (selectedFile != null) {
                        String path = selectedFile.getAbsolutePath();
                        // create file object
                        File file = new File(path);
                        Scanner scanner = null;
                        try {
                            scanner = new Scanner(file);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                        String line = scanner.nextLine();
                        // print all lines in csv file
                        while (scanner.hasNextLine()) {
                            line = scanner.nextLine();
                            String data[] = line.split(",");
                            Date date = null;
                            if (!data[0].isEmpty()) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                date = null;
                                try {
                                    date = dateFormat.parse(data[0]);
                                } catch (ParseException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                date = dateFormat.parse(data[0]);

                                String Date[] = data[0].split("-");
                                int Year = Integer.parseInt(Date[0]);
                                int Month = Integer.parseInt(Date[1]);
                                int Day = Integer.parseInt(Date[2]);

                                // Rest of your code for processing the data
                                double israeli_Lines = Double.parseDouble(data[1]);
                                double Gaza_Power_Plant = Double.parseDouble(data[2]);
                                double Egyptian_Lines = Double.parseDouble(data[3]);
                                double total_Demand = Double.parseDouble(data[4]);
                                double Overall_Demand = Double.parseDouble(data[5]);
                                double Power_Cuts_Hours_Day = Double.parseDouble(data[6]);
                                double temp = Double.parseDouble(data[7]);

                                // Switch case for month to turn month number into month name (Jan, Feb, etc.)
                                String monthName = monthNumToName(Month);

                                ElectricityRecord er = new ElectricityRecord(date, israeli_Lines, Gaza_Power_Plant, Egyptian_Lines, total_Demand, Overall_Demand, Power_Cuts_Hours_Day, temp);
                                DayNode dn = new DayNode(Day, er);
                                MonthNode mn = new MonthNode(monthName);
                                mn.insert(dn);
                                YearNode yn = new YearNode(Year);
                                yn.insert(mn);
                                yearTree.insert(yn);

                            } catch (ParseException | NumberFormatException ex) {
                                // Handle the exception as needed
                                System.out.println("Error parsing date components or numeric values: " + ex.getMessage());
                            }

                        }
                        showMainScreen();
                    }
                }

        );

        VBox selectLayout = new VBox(20, nameLabel, selectButton);
        selectLayout.setAlignment(Pos.CENTER);
        selectScene = new Scene(selectLayout, 400, 300);

        primaryStage.setScene(selectScene);
        primaryStage.setTitle("File Selection");
        primaryStage.show();
    }


    private void showMainScreen() {

        Button managementButton = new Button("Management");
        managementButton.setOnAction(e -> primaryStage.setScene(managementScene));

        Button statisticsButton = new Button("Statistics");
        statisticsButton.setOnAction(e -> primaryStage.setScene(statisticsScene));

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> primaryStage.setScene(saveScene));

        VBox mainLayout = new VBox(20, managementButton, statisticsButton, saveButton);
        mainLayout.setAlignment(Pos.CENTER);
        mainScene = new Scene(mainLayout, 400, 300);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();

        createManagementScreen();
        createStatisticsScreen();
        createSaveScreen();
    }

    private void createManagementScreen() {
        Label managementLabel = new Label("Management Screen");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Date");

        TextField israeliMWsField = new TextField();
        israeliMWsField.setPromptText("Israeli MWs");

        TextField gazaMWsField = new TextField();
        gazaMWsField.setPromptText("Gaza MWs");

        TextField egyptMWsField = new TextField();
        egyptMWsField.setPromptText("Egypt MWs");

        TextField totalMWsField = new TextField();
        totalMWsField.setPromptText("Total MWs");

        TextField overallMWsField = new TextField();
        overallMWsField.setPromptText("Overall MWs");

        TextField powerCutsField = new TextField();
        powerCutsField.setPromptText("Power Cuts");

        TextField tempField = new TextField();
        tempField.setPromptText("Temperature");

        Button submitButton = new Button("Insert");
        Button searchButton = new Button("Search");
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update");
        Button heightButton = new Button("Height");
        Button printButton = new Button("Print");
        Button clearButton = new Button("Clear");
        Button backButton = new Button("Back");
        HBox layout = new HBox(20, submitButton, searchButton, deleteButton, updateButton, heightButton, printButton, clearButton, backButton);
        layout.setAlignment(Pos.CENTER);

        submitButton.setOnAction(e -> {
            LocalDate date = datePicker.getValue();
            String sDate = date.toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date utilDate = null;
            try {
                utilDate = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            String Date[] = sDate.split("-");
            int Year = Integer.parseInt(Date[0]);
            int Month = Integer.parseInt(Date[1]);
            int Day = Integer.parseInt(Date[2]);
            String monthName = monthNumToName(Month);
            double israeliMWs = Double.parseDouble(israeliMWsField.getText());
            double gazaMWs = Double.parseDouble(gazaMWsField.getText());
            double egyptMWs = Double.parseDouble(egyptMWsField.getText());
            double totalMWs = Double.parseDouble(totalMWsField.getText());
            double overallMWs = Double.parseDouble(overallMWsField.getText());
            double powerCuts = Double.parseDouble(powerCutsField.getText());
            double temp = Double.parseDouble(tempField.getText());

            // input data into ElectricityRecord object
            ElectricityRecord er = new ElectricityRecord(utilDate, israeliMWs, gazaMWs, egyptMWs, totalMWs, overallMWs, powerCuts, temp);
            DayNode dn = new DayNode(Day, er);
            MonthNode mn = new MonthNode(monthName);
            mn.insert(dn);
            YearNode yn = new YearNode(Year);
            yn.insert(mn);
            yearTree.insert(yn);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Electrical Record was inserted successfully");
            alert.showAndWait();
        });

        searchButton.setOnAction(e -> {
            LocalDate date = datePicker.getValue();
            String sDate = date.toString();
            String Date[] = sDate.split("-");
            int Year = Integer.parseInt(Date[0]);
            int Month = Integer.parseInt(Date[1]);
            int Day = Integer.parseInt(Date[2]);
            String monthName = monthNumToName(Month);
            YearNode yn = new YearNode(Year);
            MonthNode mn = new MonthNode(monthName);
            DayNode dn = new DayNode(Day);
            TNode<YearNode> yt = yearTree.search(yn);
            if (yt == null) {
                // alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Year not found");
                alert.setContentText("The year you entered was not found.");
                alert.showAndWait();
            } else {
                TNode<MonthNode> mt = yt.getData().getTreeData().search(mn);
                if (mt == null) {
                    // alert
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Month not found");
                    alert.setContentText("The month you entered was not found.");
                    alert.showAndWait();
                } else {
                    TNode<DayNode> dt = mt.getData().getTreeData().search(dn);
                    if (dt == null) {
                        // alert
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Day not found");
                        alert.setContentText("The day you entered was not found.");
                        alert.showAndWait();
                    } else {
                        ElectricityRecord er = dt.getData().getRecord();
                        israeliMWsField.setText(er.getIsraeli_MWs() + "");
                        gazaMWsField.setText(er.getGaza_MWs() + "");
                        egyptMWsField.setText(er.getEgypt_MWs() + "");
                        totalMWsField.setText(er.getTotal_MWs() + "");
                        overallMWsField.setText(er.getOverall_MWs() + "");
                        powerCutsField.setText(er.getPowerCuts() + "");
                        tempField.setText(er.getTemp() + "");
                    }
                }
            }

        });

        deleteButton.setOnAction(e -> {
            Alert delAlert = new Alert(Alert.AlertType.CONFIRMATION);
            delAlert.setTitle("Confirmation Dialog");
            delAlert.setHeaderText("Delete Record");
            delAlert.setContentText("Are you sure you want to delete this record?");

            Optional<ButtonType> result = delAlert.showAndWait();
            if (result.get() == ButtonType.OK) {
                LocalDate date = datePicker.getValue();
                String sDate = date.toString();
                String Date[] = sDate.split("-");
                int Year = Integer.parseInt(Date[0]);
                int Month = Integer.parseInt(Date[1]);
                int Day = Integer.parseInt(Date[2]);
                String monthName = monthNumToName(Month);
                YearNode yn = new YearNode(Year);
                MonthNode mn = new MonthNode(monthName);
                DayNode dn = new DayNode(Day);
                TNode<YearNode> yt = yearTree.search(yn);
                if (yt == null) {
                    // alert
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Year not found");
                    alert.setContentText("The year you entered was not found.");
                    alert.showAndWait();
                } else {
                    TNode<MonthNode> mt = yt.getData().getTreeData().search(mn);
                    if (mt == null) {
                        // alert
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Month not found");
                        alert.setContentText("The month you entered was not found.");
                        alert.showAndWait();
                    } else {
                        TNode<DayNode> dt = mt.getData().getTreeData().search(dn);
                        if (dt == null) {
                            // alert
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Day not found");
                            alert.setContentText("The day you entered was not found.");
                            alert.showAndWait();
                        } else {
                            mt.getData().getTreeData().delete(dt.getData());
                            if (mt.getData().getTreeData().getRoot() == null) {
                                yt.getData().getTreeData().delete(mt.getData());
                                if (yt.getData().getTreeData().getRoot() == null) {
                                    yearTree.delete(yt.getData());
                                }
                            }
                        }
                    }
                }
            }
            // clear fields
            datePicker.setValue(null);
            israeliMWsField.clear();
            gazaMWsField.clear();
            egyptMWsField.clear();
            totalMWsField.clear();
            overallMWsField.clear();
            powerCutsField.clear();
            tempField.clear();
        });

        updateButton.setOnAction(e -> {
            LocalDate date = datePicker.getValue();
            String sDate = date.toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date utilDate = null;
            try {
                utilDate = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            String Date[] = sDate.split("-");
            int Year = Integer.parseInt(Date[0]);
            int Month = Integer.parseInt(Date[1]);
            int Day = Integer.parseInt(Date[2]);
            String monthName = monthNumToName(Month);
            YearNode yn = new YearNode(Year);
            MonthNode mn = new MonthNode(monthName);
            DayNode dn = new DayNode(Day);
            TNode<YearNode> yt = yearTree.search(yn);
            if (yt == null) {
                // alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Year not found");
                alert.setContentText("The year you entered was not found.");
                alert.showAndWait();
            } else {
                TNode<MonthNode> mt = yt.getData().getTreeData().search(mn);
                if (mt == null) {
                    // alert
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Month not found");
                    alert.setContentText("The month you entered was not found.");
                    alert.showAndWait();
                } else {
                    TNode<DayNode> dt = mt.getData().getTreeData().search(dn);
                    if (dt == null) {
                        // alert
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Day not found");
                        alert.setContentText("The day you entered was not found.");
                        alert.showAndWait();
                    } else {
                        // get data
                        double israeliMWs = Double.parseDouble(israeliMWsField.getText());
                        double gazaMWs = Double.parseDouble(gazaMWsField.getText());
                        double egyptMWs = Double.parseDouble(egyptMWsField.getText());
                        double totalMWs = Double.parseDouble(totalMWsField.getText());
                        double overallMWs = Double.parseDouble(overallMWsField.getText());
                        double powerCuts = Double.parseDouble(powerCutsField.getText());
                        double temp = Double.parseDouble(tempField.getText());

                        // input data into ElectricityRecord object
                        ElectricityRecord er = new ElectricityRecord(utilDate, israeliMWs, gazaMWs, egyptMWs, totalMWs, overallMWs, powerCuts, temp);
                        dt.getData().setRecord(er);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Electrical Record was updated successfully");
                        alert.showAndWait();
                    }
                }
            }
            // clear fields
            datePicker.setValue(null);
            israeliMWsField.clear();
            gazaMWsField.clear();
            egyptMWsField.clear();
            totalMWsField.clear();
            overallMWsField.clear();
            powerCutsField.clear();
            tempField.clear();
        });

        heightButton.setOnAction(e -> {
            int yearHeight = yearTree.getHeight();
            int monthHeight = yearTree.getRoot().getData().getTreeData().getHeight();
            int dayHeight = yearTree.getRoot().getData().getTreeData().getRoot().getData().getTreeData().getHeight();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Heights");
            alert.setHeaderText(null);
            alert.setContentText("Year Height: " + yearHeight + "\nMonth Height: " + monthHeight + "\nDay Height: " + dayHeight);
            alert.showAndWait();
        });

        printButton.setOnAction(e -> printTreeScreen());

        clearButton.setOnAction(e -> {
            // clear fields
            datePicker.setValue(null);
            israeliMWsField.clear();
            gazaMWsField.clear();
            egyptMWsField.clear();
            totalMWsField.clear();
            overallMWsField.clear();
            powerCutsField.clear();
            tempField.clear();
        });

        backButton.setOnAction(e -> {
            // clear fields
            datePicker.setValue(null);
            israeliMWsField.clear();
            gazaMWsField.clear();
            egyptMWsField.clear();
            totalMWsField.clear();
            overallMWsField.clear();
            powerCutsField.clear();
            tempField.clear();
            primaryStage.setScene(mainScene);
        });

        VBox managementLayout = new VBox(20, managementLabel, datePicker, israeliMWsField, gazaMWsField, egyptMWsField, totalMWsField, overallMWsField, powerCutsField, tempField, layout);
        managementLayout.setAlignment(Pos.CENTER);
        managementScene = new Scene(managementLayout, 800, 600);
    }

    private void printTreeScreen() {
        Label printLabel = new Label("Print Tree Screen");
        TextArea printArea = new TextArea();
        printArea.setEditable(false);
        printArea.setPrefSize(800, 600);
        printArea.setText("Year Tree:\n" + yearTree.printTree() + "\n\nMonth Tree:\n" + yearTree.getRoot().getData().getTreeData().printTree() + "\n\nDay Tree:\n" + yearTree.getRoot().getData().getTreeData().getRoot().getData().getTreeData().printTree());
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(managementScene));
        VBox printLayout = new VBox(20, printLabel, printArea, backButton);
        printLayout.setAlignment(Pos.CENTER);
        Scene printScene = new Scene(printLayout, 800, 600);
        primaryStage.setScene(printScene);
    }

    private void createStatisticsScreen() {
        Label statisticsLabel = new Label("Statistics Screen");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Date");
        Label total = new Label("Total MWs: ");
        Label average = new Label("Average MWs: ");
        Label max = new Label("Max MWs: ");
        Label min = new Label("Min MWs: ");

        Button yearButton = new Button("Year");
        Button monthButton = new Button("Month");
        Button dayButton = new Button("Day");
        Button allButton = new Button("All");
        Button clearButton = new Button("Clear");
        Button backButton = new Button("Back");
        HBox layout = new HBox(20, yearButton, monthButton, dayButton, allButton, clearButton, backButton);
        layout.setAlignment(Pos.CENTER);

        yearButton.setOnAction(e -> {
            if (datePicker.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Date not found");
                alert.setContentText("The date you entered was not found.");
                alert.showAndWait();
            } else {
                int year = datePicker.getValue().getYear();
                YearNode yn = new YearNode(year);
                TNode<YearNode> yt = yearTree.search(yn);
                if (yt == null) {
                    // alert
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Year not found");
                    alert.setContentText("The year you entered was not found.");
                    alert.showAndWait();
                } else {
                    double totalMWs = 0;
                    double count = 0;
                    double maxMWs = -100000000;
                    double minMWs = 100000000;
                    String minDate = "";
                    String maxDate = "";
                    for (int i = 1; i <= 12; i++) {
                        String monthName = monthNumToName(i);
                        MonthNode mn = new MonthNode(monthName);
                        TNode<MonthNode> mt = yt.getData().getTreeData().search(mn);
                        if (mt != null) {
                            for (int j = 1; j <= 31; j++) {
                                DayNode dn = new DayNode(j);
                                TNode<DayNode> dt = mt.getData().getTreeData().search(dn);
                                if (dt != null) {
                                    ElectricityRecord er = dt.getData().getRecord();
                                    totalMWs += er.getGaza_MWs();
                                    count++;
                                    if (er.getGaza_MWs() > maxMWs) {
                                        maxMWs = er.getGaza_MWs();
                                        String strDate = formatter.format(er.getDate());
                                        maxDate = strDate;
                                    }
                                    if (er.getGaza_MWs() < minMWs) {
                                        minMWs = er.getGaza_MWs();
                                        String strDate = formatter.format(er.getDate());
                                        minDate = strDate;
                                    }
                                }
                            }
                        }
                    }
                    total.setText("Total MWs: " + totalMWs);
                    average.setText("Average MWs: " + totalMWs/count);
                    max.setText("Max MWs: " + maxMWs + " on " + maxDate);
                    min.setText("Min MWs: " + minMWs + " on " + minDate);
                }
            }
        });

        monthButton.setOnAction(e -> {
            if (datePicker.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Date not found");
                alert.setContentText("The date you entered was not found.");
                alert.showAndWait();
            } else {
                int month = datePicker.getValue().getMonthValue();
                String monthName = monthNumToName(month);
                MonthNode mn = new MonthNode(monthName);
                int startYear = yearTree.findMin().getData().getYear();
                int endYear = yearTree.findMax().getData().getYear();
                double totalMWs = 0;
                double count = 0;
                double maxMWs = -100000000;
                double minMWs = 100000000;
                String minDate = "";
                String maxDate = "";
                for(int i = startYear; i <= endYear; i++) {
                    YearNode yn = new YearNode(i);
                    TNode<YearNode> yt = yearTree.search(yn);
                    if (yt != null) {
                        TNode<MonthNode> mt = yt.getData().getTreeData().search(mn);
                        if (mt != null) {
                            for (int j = 1; j <= 31; j++) {
                                DayNode dn = new DayNode(j);
                                TNode<DayNode> dt = mt.getData().getTreeData().search(dn);
                                if (dt != null) {
                                    ElectricityRecord er = dt.getData().getRecord();
                                    totalMWs += er.getGaza_MWs();
                                    count++;
                                    if (er.getGaza_MWs() > maxMWs) {
                                        maxMWs = er.getGaza_MWs();
                                        String strDate = formatter.format(er.getDate());
                                        maxDate = strDate;
                                    }
                                    if (er.getGaza_MWs() < minMWs) {
                                        minMWs = er.getGaza_MWs();
                                        String strDate = formatter.format(er.getDate());
                                        minDate = strDate;
                                    }
                                }
                            }
                        }
                    }
                }
                total.setText("Total MWs: " + totalMWs);
                average.setText("Average MWs: " + totalMWs/count);
                max.setText("Max MWs: " + maxMWs + " on " + maxDate);
                min.setText("Min MWs: " + minMWs + " on " + minDate);
            }
        });

        dayButton.setOnAction(e -> {
            if (datePicker.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Date not found");
                alert.setContentText("The date you entered was not found.");
                alert.showAndWait();
            } else {
                int day = datePicker.getValue().getDayOfMonth();
                DayNode dn = new DayNode(day);
                int startYear = yearTree.findMin().getData().getYear();
                int endYear = yearTree.findMax().getData().getYear();
                double totalMWs = 0;
                double count = 0;
                double maxMWs = -100000000;
                double minMWs = 100000000;
                String minDate = "";
                String maxDate = "";
                for(int i = startYear; i <= endYear; i++) {
                    YearNode yn = new YearNode(i);
                    TNode<YearNode> yt = yearTree.search(yn);
                    if (yt != null) {
                        for (int j = 1; j <= 12; j++) {
                            String monthName = monthNumToName(j);
                            MonthNode mn = new MonthNode(monthName);
                            TNode<MonthNode> mt = yt.getData().getTreeData().search(mn);
                            if (mt != null) {
                                TNode<DayNode> dt = mt.getData().getTreeData().search(dn);
                                if (dt != null) {
                                    ElectricityRecord er = dt.getData().getRecord();
                                    totalMWs += er.getGaza_MWs();
                                    count++;
                                    if (er.getGaza_MWs() > maxMWs) {
                                        maxMWs = er.getGaza_MWs();
                                        String strDate = formatter.format(er.getDate());
                                        maxDate = strDate;
                                    }
                                    if (er.getGaza_MWs() < minMWs) {
                                        minMWs = er.getGaza_MWs();
                                        String strDate = formatter.format(er.getDate());
                                        minDate = strDate;
                                    }
                                }
                            }
                        }
                    }
                }
                total.setText("Total MWs: " + totalMWs);
                average.setText("Average MWs: " + totalMWs/count);
                max.setText("Max MWs: " + maxMWs + " on " + maxDate);
                min.setText("Min MWs: " + minMWs + " on " + minDate);
            }
        });

        allButton.setOnAction(e -> {
            int startYear = yearTree.findMin().getData().getYear();
            int endYear = yearTree.findMax().getData().getYear();
            double totalMWs = 0;
            double count = 0;
            double maxMWs = -100000000;
            double minMWs = 100000000;
            String minDate = "";
            String maxDate = "";
            for(int i = startYear; i <= endYear; i++) {
                YearNode yn = new YearNode(i);
                TNode<YearNode> yt = yearTree.search(yn);
                if (yt != null) {
                    for (int j = 1; j <= 12; j++) {
                        String monthName = monthNumToName(j);
                        MonthNode mn = new MonthNode(monthName);
                        TNode<MonthNode> mt = yt.getData().getTreeData().search(mn);
                        if (mt != null) {
                            for (int k = 1; k <= 31; k++) {
                                DayNode dn = new DayNode(k);
                                TNode<DayNode> dt = mt.getData().getTreeData().search(dn);
                                if (dt != null) {
                                    ElectricityRecord er = dt.getData().getRecord();
                                    totalMWs += er.getGaza_MWs();
                                    count++;
                                    if (er.getGaza_MWs() > maxMWs) {
                                        maxMWs = er.getGaza_MWs();
                                        String strDate = formatter.format(er.getDate());
                                        maxDate = strDate;
                                    }
                                    if (er.getGaza_MWs() < minMWs) {
                                        minMWs = er.getGaza_MWs();
                                        String strDate = formatter.format(er.getDate());
                                        minDate = strDate;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            total.setText("Total MWs: " + totalMWs);
            average.setText("Average MWs: " + totalMWs/count);
            max.setText("Max MWs: " + maxMWs + " on " + maxDate);
            min.setText("Min MWs: " + minMWs + " on " + minDate);
        });

        clearButton.setOnAction(e -> {
            // clear fields
            datePicker.setValue(null);
            total.setText("Total MWs: ");
            average.setText("Average MWs: ");
            max.setText("Max MWs: ");
            min.setText("Min MWs: ");
        });

        backButton.setOnAction(e -> {
            // clear fields
            datePicker.setValue(null);
            total.setText("Total MWs: ");
            average.setText("Average MWs: ");
            max.setText("Max MWs: ");
            min.setText("Min MWs: ");
            primaryStage.setScene(mainScene);
        });
        VBox statisticsLayout = new VBox(20, statisticsLabel, datePicker, total, average, max, min, layout);
        statisticsLayout.setAlignment(Pos.CENTER);
        statisticsScene = new Scene(statisticsLayout, 400, 300);
    }

    private void createSaveScreen() {
        Label saveLabel = new Label("Save Screen");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");

        // Create a button to trigger file selection
        Button selectButton = new Button("Select File");
        selectButton.setOnAction(e ->{
            selectedFile = fileChooser.showOpenDialog(primaryStage);
            try {
                PrintWriter pw = new PrintWriter(selectedFile);
                pw.println("Date,Israeli_Lines_MWs,Gaza_Power_Plant_MWs,Egyptian_Lines_MWs,Total_daily_Supply_available_in_MWs,Overall_demand_in_MWs,Power_Cuts_hours_day_400mg,Temp");
                int startYear = yearTree.findMin().getData().getYear();
                int endYear = yearTree.findMax().getData().getYear();
                for(int i = startYear; i <= endYear; i++) {
                    YearNode yn = new YearNode(i);
                    TNode<YearNode> yt = yearTree.search(yn);
                    if (yt != null) {
                        for (int j = 1; j <= 12; j++) {
                            String monthName = monthNumToName(j);
                            MonthNode mn = new MonthNode(monthName);
                            TNode<MonthNode> mt = yt.getData().getTreeData().search(mn);
                            if (mt != null) {
                                for (int k = 1; k <= 31; k++) {
                                    DayNode dn = new DayNode(k);
                                    TNode<DayNode> dt = mt.getData().getTreeData().search(dn);
                                    if (dt != null) {
                                        ElectricityRecord er = dt.getData().getRecord();
                                        String strDate = formatter.format(er.getDate());
                                        pw.println(strDate + "," + er.getIsraeli_MWs() + "," + er.getGaza_MWs() + "," + er.getEgypt_MWs() + "," + er.getTotal_MWs() + "," + er.getOverall_MWs() + "," + er.getPowerCuts() + "," + er.getTemp());
                                        pw.flush();
                                    }
                                }
                            }
                        }
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("File was saved successfully");
                alert.showAndWait();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));
        VBox saveLayout = new VBox(20, saveLabel, selectButton, backButton);
        saveLayout.setAlignment(Pos.CENTER);
        saveScene = new Scene(saveLayout, 400, 300);
    }

    public static void main(String[] args) {
        launch();
    }
}