package binarysearchtree;

import java.util.*;

public class BinarySearchTree {

    Node head;

    public BinarySearchTree(ArrayList<Integer> arr) {
        arr = removeDuplicates(arr);
        Collections.sort(arr);
        ArrayList<Node> nodes = new ArrayList<>();
        for (Integer i : arr) {
            nodes.add(new Node(i));
        }
        head = buildTree(nodes, 0, nodes.size() - 1);
    }

    public Node buildTree(ArrayList<Node> arr, int l, int r) {
        if (l > r) {
            return null;
        }
        int m = (l + r) / 2;
        Node n = arr.get(m);
        n.left = buildTree(arr, l, m - 1);
        n.right = buildTree(arr, m + 1, r);
        return n;
    }

    public boolean insert(int value) {
        // true if inserted
        // false if duplicate/not inserted
        head = add(value, head);
        return head != null;
    }

    public boolean delete(int value) {
        if (!find(value)) {
            return false;
        }
        head = remove(value, head);
        return head != null;
    }

    public boolean find(int value) {
        return search(value, head) != null;
    }

    public void display() {
        if (head == null) {
            return;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(head);
        while (!nodes.isEmpty()) {
            int n = nodes.size();
            for (int i = 0; i < n; i++) {
                Node current = nodes.remove(0);
                System.out.print(current.value + " ");
                if (current.left != null) {
                    nodes.add(current.left);
                }
                if (current.right != null) {
                    nodes.add(current.right);
                }
            }
            System.out.println();
        }
    }

    public ArrayList<Integer> traverse(int choice) {
        // 1 = preorder
        // 2 = inorder
        // 3 = postorder

        switch (choice) {
            case 1 -> {
                System.out.print("Preorder: ");
                return preorder(head, new ArrayList<>());
            }
            case 2 -> {
                System.out.print("Inorder: ");
                return inorder(head, new ArrayList<>());
            }
            case 3 -> {
                System.out.print("Postorder: ");
                return postorder(head, new ArrayList<>());
            }
        }
        return new ArrayList<>();
    }

    public boolean balanced() {
        return balance(head) != -1;
    }

    public void rebalance() {
        ArrayList<Integer> arr = inorder(head, new ArrayList<>());
        ArrayList<Node> nodes = new ArrayList<>();
        for (Integer i : arr) {
            nodes.add(new Node(i));
        }
        head = buildTree(nodes, 0, arr.size() - 1);
        System.out.println("Rebalanced");
    }

    private Node add(int value, Node current) {
        if (current == null) {
            return new Node(value);
        } else if (value > current.value) {
            current.right = add(value, current.right);
        } else if (value < current.value) {
            current.left = add(value, current.left);
        }
        return current;
    }

    private Node remove(int value, Node current) {
        if (current == null) {
            return current;
        } else if (value > current.value) {
            current.right = remove(value, current.right);
        } else if (value < current.value) {
            current.left = remove(value, current.left);
        } else {
            if (current.right == null) {
                return current.right;
            } else if (current.left == null) {
                return current.left;
            }
            current.value = minValue(current.right);
            current.right = remove(current.value, current.right);
        }
        return current;
    }

    private Node search(int value, Node current) {
        if (current.value == value) {
            return current;
        } else if (value > current.value && current.right != null) {
            return search(value, current.right);
        } else if (value < current.value && current.left != null) {
            return search(value, current.left);
        }
        return null;
    }

    private int height(Node current) {
        if (current == null) {
            return -1;
        }
        int hLeft = height(current.left);
        int hRight = height(current.right);
        return Math.max(hLeft, hRight) + 1;
    }

    private int depth(Node current, Node n) {
        if (current == null) {
            return -1;
        }
        int dLeft = depth(current.left, n);
        int dRight = depth(current.right, n);
        if (current.value == n.value) {
            return 0;
        } else if (dLeft != -1) {
            return dLeft + 1;
        } else if (dRight != -1) {
            return dRight + 1;
        }
        return -1;
    }

    private ArrayList<Integer> preorder(Node n, ArrayList<Integer> arr) {
        if (n == null) {
            return null;
        }
        arr.add(n.value);
        preorder(n.left, arr);
        preorder(n.right, arr);
        return arr;
    }

    private ArrayList<Integer> inorder(Node n, ArrayList<Integer> arr) {
        if (n == null) {
            return null;
        }
        inorder(n.left, arr);
        arr.add(n.value);
        inorder(n.right, arr);
        return arr;
    }

    private ArrayList<Integer> postorder(Node n, ArrayList<Integer> arr) {
        if (n == null) {
            return null;
        }
        postorder(n.left, arr);
        postorder(n.right, arr);
        arr.add(n.value);
        return arr;
    }

    private int balance(Node current) {
        if (current == null) {
            return 0;
        }
        int hLeft = balance(current.left);
        if (hLeft == -1) {
            return -1;
        }
        int hRight = balance(current.right);
        if (hRight == -1) {
            return -1;
        }
        if (Math.abs(hLeft - hRight) > 1) {
            return -1;
        } else {
            return height(current);
        }
    }

    private ArrayList<Integer> removeDuplicates(ArrayList<Integer> arr) {
        ArrayList<Integer> set = new ArrayList<>();
        for (Integer i : arr) {
            if (!set.contains(i)) {
                set.add(i);
            }
        }
        return set;
    }

    private int minValue(Node n) {
        int min = n.value;
        while (n.left != null) {
            min = n.left.value;
            n = n.left;
        }
        return min;
    }
}
