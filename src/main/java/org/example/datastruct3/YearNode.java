package org.example.datastruct3;

import java.util.Objects;

public class YearNode extends TNode implements Comparable<YearNode>{
    private int year;
    private AVL<MonthNode> data;

    private YearNode left;
    private YearNode right;

    public YearNode() {
        this.left = this.right = null;
    }

    public YearNode(int year, AVL<MonthNode> data) {
        this.year = year;
        this.data = data;
    }

    public YearNode(int year) {
        this.year = year;
    }

    // Getters and Setters
    public YearNode getLeft() {
        return left;
    }

    public void setLeft(YearNode left) {
        this.left = left;
    }

    public YearNode getRight() {
        return right;
    }

    public void setRight(YearNode right) {
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

    public AVL<MonthNode> getTreeData() {
        return data;
    }

    public void setData(AVL<MonthNode> data) {
        this.data = data;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Methods
    @Override
    public String toString() {
        return year+"";
    }

    @Override
    public int compareTo(YearNode other) {

        return this.year - other.getYear();
    }

    public void insert(MonthNode data) {
        if (this.data == null) {
            this.data = new AVL<>();
            this.data.insert(data);
        } else {
            AVL<MonthNode> rootNode = this.data;
            rootNode.addEntry(data, rootNode.getRoot());
            this.data = rootNode;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YearNode yearNode = (YearNode) o;
        return year == yearNode.getYear();
    }


}
