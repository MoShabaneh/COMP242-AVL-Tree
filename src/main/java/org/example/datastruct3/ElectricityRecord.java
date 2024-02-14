package org.example.datastruct3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ElectricityRecord {
    // Attributes
    private Date date;
    private double Israeli_MWs;
    private double Gaza_MWs;
    private double Egypt_MWs;
    private double Total_MWs;
    private double Overall_MWs;
    private double powerCuts;
    private double temp;

    // Constructors
    public ElectricityRecord(Date date, double israeli_MWs, double gaza_MWs, double egypt_MWs, double total_MWs, double overall_MWs, double powerCuts, double temp) {
        this.date = date;
        Israeli_MWs = israeli_MWs;
        Gaza_MWs = gaza_MWs;
        Egypt_MWs = egypt_MWs;
        Total_MWs = total_MWs;
        Overall_MWs = overall_MWs;
        this.powerCuts = powerCuts;
        this.temp = temp;
    }

    public ElectricityRecord() {

    }

    // Getters and Setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getIsraeli_MWs() {
        return Israeli_MWs;
    }

    public void setIsraeli_MWs(double israeli_MWs) {
        Israeli_MWs = israeli_MWs;
    }

    public double getGaza_MWs() {
        return Gaza_MWs;
    }

    public void setGaza_MWs(double gaza_MWs) {
        Gaza_MWs = gaza_MWs;
    }

    public double getEgypt_MWs() {
        return Egypt_MWs;
    }

    public void setEgypt_MWs(double egypt_MWs) {
        Egypt_MWs = egypt_MWs;
    }

    public double getTotal_MWs() {
        return Total_MWs;
    }

    public void setTotal_MWs(double total_MWs) {
        Total_MWs = total_MWs;
    }

    public double getOverall_MWs() {
        return Overall_MWs;
    }

    public void setOverall_MWs(double overall_MWs) {
        Overall_MWs = overall_MWs;
    }

    public double getPowerCuts() {
        return powerCuts;
    }

    public void setPowerCuts(double powerCuts) {
        this.powerCuts = powerCuts;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    // Methods

    // toString returns a string representation of the object in csv format
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);
        return formattedDate + "," +
                Israeli_MWs + "," +
                Gaza_MWs + "," +
                Egypt_MWs + "," +
                Total_MWs + "," +
                Overall_MWs + "," +
                powerCuts + "," +
                temp;

    }
}
