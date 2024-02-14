package org.example.datastruct3;

public class DayNode extends TNode implements Comparable<DayNode> {
    private int day;
    private ElectricityRecord data;
    private DayNode left;
    private DayNode right;

    public DayNode() {
        this.left = this.right = null;
    }

    public DayNode(int day) {
        this.day = day;
    }

    public DayNode(ElectricityRecord data) {
        this.data = data;
    }

    public DayNode(int day, ElectricityRecord data) {
        this.day = day;
        this.data = data;
    }

    // Getters and Setters

    public DayNode getLeft() {
        return left;
    }

    public void setLeft(DayNode left) {
        this.left = left;
    }

    public DayNode getRight() {
        return right;
    }

    public void setRight(DayNode right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return (left == null && right == null);
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public ElectricityRecord getRecord() {
        return data;
    }

    public void setRecord(ElectricityRecord data) {
        this.data = data;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    // Methods
    @Override
    public String toString() {
        return day + " ";
    }

    @Override
    public int compareTo(DayNode o) {

        return this.day - o.getDay();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayNode dayNode = (DayNode) o;
        return day == dayNode.getDay();
    }
}
