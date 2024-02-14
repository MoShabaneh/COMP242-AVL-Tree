package org.example.datastruct3;

public class MonthNode extends TNode implements Comparable<MonthNode> {
    private String month;
    private AVL<DayNode> data;

    private MonthNode left;
    private MonthNode right;

    public MonthNode() {
        this.left = this.right = null;
    }

    public MonthNode(String month, AVL<DayNode> data) {
        this.month = month;
        this.data = data;
    }

    public MonthNode(String month) {
        this.month = month;
    }

    // Getters and Setters
    public MonthNode getLeft() {
        return left;
    }

    public void setLeft(MonthNode left) {
        this.left = left;
    }

    public MonthNode getRight() {
        return right;
    }

    public void setRight(MonthNode right) {
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

    public AVL<DayNode> getTreeData() {
        return data;
    }

    public void setData(AVL<DayNode> data) {
        this.data = data;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    // Methods
    @Override
    public String toString() {
        return month;
    }

    @Override
    public int compareTo(MonthNode other) {

        return this.month.compareTo(other.getMonth());
    }

    public void insert(DayNode data) {
        if (this.data == null){
            this.data = new AVL<>();
            this.data.insert(data);
        }   else {
            AVL<DayNode> rootNode = this.data;
            TNode<DayNode> root = rootNode.getRoot();
            rootNode.addEntry(data, root);
            this.data = rootNode;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthNode monthNode = (MonthNode) o;
        return month.equals(monthNode.getMonth());
    }
}
