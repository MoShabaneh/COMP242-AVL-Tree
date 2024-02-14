package org.example.datastruct3;


public class AVL<T extends Comparable<T>> {
    TNode<T> root;

    private TNode<T> rotateRightLeft(TNode<T> node) {
        node.setRight(rotateRight(node.getRight()));

        return rotateLeft(node);
    }

    private TNode<T> rotateLeft(TNode<T> node) {
        TNode<T> nodec = node.getRight();
        node.setRight(nodec.getLeft());
        nodec.setLeft(node);
        return nodec;
    }

    private TNode<T> rotateLeftRight(TNode<T> node) {
        node.setLeft(rotateLeft(node.getLeft()));

        return rotateRight(node);
    }

    private TNode<T> rotateRight(TNode<T> node) {
        TNode<T> nodec = node.getLeft();
        node.setLeft(nodec.getRight());
        nodec.setRight(node);

        return nodec;
    }

    private TNode<T> rebalance(TNode<T> nodeN) {
        int diff = getHeightDifference(nodeN);
        if (diff > 1) { // addition was in node's left subtree
            if (getHeightDifference(nodeN.getLeft()) > 0)
                nodeN = rotateRight(nodeN);
            else
                nodeN = rotateLeftRight(nodeN);
        } else if (diff < -1) { // addition was in node's right subtree
            if (getHeightDifference(nodeN.getRight()) < 0)
                nodeN = rotateLeft(nodeN);
            else
                nodeN = rotateRightLeft(nodeN);
        }
        return nodeN;
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(TNode<T> node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.println(node.getData() + " ");
            traverseInOrder(node.getRight());
        }
    }

    private int getHeightDifference(TNode<T> nodeN) {
        if (nodeN == null) {
            return 0;
        } else {
            int leftHeight = getHeight(nodeN.getLeft());
            int rightHeight = getHeight(nodeN.getRight());

            return leftHeight - rightHeight;
        }
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(TNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
        }
    }

    public void insert(T data) {
        if (root == null)
            root = new TNode<>(data);
        else {
            TNode rootNode = root;
            addEntry(data, rootNode);
            root = rebalance(rootNode);
        }
    }

    public void addEntry(T data, TNode<T> rootNode) {
        assert rootNode != null;

        if (data == null) {

            return;
        }

        if (rootNode.getData() == null || data.compareTo(rootNode.getData()) == 0) {
            // insert month
            if(rootNode.getData().getClass() == YearNode.class){
            YearNode yn = (YearNode) rootNode.getData();
            yn.insert(((YearNode) data).getTreeData().getRoot().getData());
            }

            if(rootNode.getData().getClass() == MonthNode.class){
                MonthNode mn = (MonthNode) rootNode.getData();
                mn.insert(((MonthNode) data).getTreeData().getRoot().getData());
            }

            return;
        }

        if (rootNode.getData() == null || data.compareTo(rootNode.getData()) < 0) {
            if (rootNode.hasLeft()) {
                TNode<T> leftChild = rootNode.getLeft();
                addEntry(data, leftChild);
                rootNode.setLeft(rebalance(leftChild));
            } else {
                rootNode.setLeft(new TNode<>(data));
            }
        } else {
            if (rootNode.hasRight()) {
                TNode<T> rightChild = rootNode.getRight();
                addEntry(data, rightChild);
                rootNode.setRight(rebalance(rightChild));
            } else {
                rootNode.setRight(new TNode<>(data));
            }
        }
    }

    public TNode<T> getRoot() {
        return root;
    }


    public TNode<T> search(T data) {
        return search(data, root);
    }

    private TNode<T> search(T data, TNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.getData().equals(data)) {
            return node;
        } else if (node.getData().compareTo(data) > 0) {
            return search(data, node.getLeft());
        } else {
            return search(data, node.getRight());
        }
    }

    public TNode<T> findMax() {
        return findMax(root);
    }

    private TNode<T> findMax(TNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.hasRight()) {
            return findMax(node.getRight());
        } else {
            return node;
        }
    }

    public TNode<T> findMin() {
        return findMin(root);
    }

    private TNode<T> findMin(TNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.hasLeft()) {
            return findMin(node.getLeft());
        } else {
            return node;
        }
    }

    public void delete(T data) {
        root = delete(data, root);
    }

    private TNode<T> delete(T data, TNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.getData().equals(data)) {
            if (node.isLeaf()) {
                return null;
            } else if (node.hasLeft() && !node.hasRight()) {
                return node.getLeft();
            } else if (!node.hasLeft() && node.hasRight()) {
                return node.getRight();
            } else {
                TNode<T> replacement = findMax(node.getLeft());
                node.setData(replacement.getData());
                node.setLeft(delete(replacement.getData(), node.getLeft()));
                return node;
            }
        } else if (node.getData().compareTo(data) > 0) {
            node.setLeft(delete(data, node.getLeft()));
            return node;
        } else {
            node.setRight(delete(data, node.getRight()));
            return node;
        }
    }

    public String printTree() {
        return printTree(root);
    }

    public String printTree(TNode<T> node) {
        if (node == null) {
            return "";
        } else {
            String output = "";
            QueueArray<TNode<T>> queue = new QueueArray<>(50);
            queue.enqueue(node);
            while(true) {
                int nodeCount = queue.size();
                if (nodeCount == 0)
                    break;
                while (nodeCount > 0) {
                    TNode<T> current = queue.dequeue();
                    nodeCount--;
                    if (current.getData().getClass() == YearNode.class) {
                        output += ((YearNode) current.getData()).getYear() + " ";
                    } else if (current.getData().getClass() == MonthNode.class) {
                        output += ((MonthNode) current.getData()).getMonth() + " ";
                    } else {
                        output += ((DayNode) current.getData()).getDay() + " ";
                    }
                    if (current.hasLeft()) {
                        queue.enqueue(current.getLeft());
                    }
                    if (current.hasRight()) {
                        queue.enqueue(current.getRight());
                    }
                }
                output += "\n";
            }
            return output;
        }
    }
}

