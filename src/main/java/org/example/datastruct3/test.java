package org.example.datastruct3;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
//        AVL<YearNode> yearTree = new AVL<>();
//
//        // create file object
//        File file = new File("src/Electricity.csv");
//        // read csv file
//        Scanner scanner = new Scanner(file);
//        String line = scanner.nextLine();
//        // print all lines in csv file
//        while (scanner.hasNextLine()) {
//            line = scanner.nextLine();
//            String data[] = line.split(",");
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = dateFormat.parse(data[0]);
//            String Date[] = data[0].split("-");
//            int Year = Integer.parseInt(Date[0]);
//            int Month = Integer.parseInt(Date[1]);
//            int Day = Integer.parseInt(Date[2]);
//            double israeli_Lines = Double.parseDouble(data[1]);
//            double Gaza_Power_Plant = Double.parseDouble(data[2]);
//            double Egyptian_Lines = Double.parseDouble(data[3]);
//            double total_Demand = Double.parseDouble(data[4]);
//            double Overall_Demand = Double.parseDouble(data[5]);
//            double Power_Cuts_Hours_Day = Double.parseDouble(data[6]);
//            double temp = Double.parseDouble(data[7]);
//
//            // switch case for month to turn month number into month name (Jan, Feb, etc.)
//            String monthName = "";
//            switch (Month) {
//                case 1:
//                    monthName = "Jan";
//                    break;
//                case 2:
//                    monthName = "Feb";
//                    break;
//                case 3:
//                    monthName = "Mar";
//                    break;
//                case 4:
//                    monthName = "Apr";
//                    break;
//                case 5:
//                    monthName = "May";
//                    break;
//                case 6:
//                    monthName = "Jun";
//                    break;
//                case 7:
//                    monthName = "Jul";
//                    break;
//                case 8:
//                    monthName = "Aug";
//                    break;
//                case 9:
//                    monthName = "Sep";
//                    break;
//                case 10:
//                    monthName = "Oct";
//                    break;
//                case 11:
//                    monthName = "Nov";
//                    break;
//                case 12:
//                    monthName = "Dec";
//                    break;
//            }
//            ElectricityRecord er = new ElectricityRecord(date, israeli_Lines, Gaza_Power_Plant, Egyptian_Lines, total_Demand, Overall_Demand, Power_Cuts_Hours_Day, temp);
//            DayNode dn = new DayNode(Day, er);
//            MonthNode mn = new MonthNode(monthName);
//            mn.insert(dn);
//            YearNode yn = new YearNode(Year);
//            yn.insert(mn);
//            yearTree.insert(yn);
//        }
//        System.out.println("File successfully read.\n");
//        yearTree.printTreeStylized();
//        System.out.println("----------------------2020 month tree----------------------\n");
//        yearTree.getRoot().getData().getTreeData().printTreeStylized();
//        System.out.println("----------------------2020 day tree----------------------\n");
//        yearTree.getRoot().getData().getTreeData().getRoot().getData().getTreeData().printTreeStylized();
        LocalDate date = LocalDate.now();
        String sDate = date.toString();
        System.out.println(sDate);
    }
}
