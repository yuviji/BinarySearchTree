package binarysearchtree;

import java.util.*;

// Yuvraj
public class BST {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();
        System.out.println("Initialize tree with values (-1 to stop)");
        int val;
        do {
            System.out.print("Enter value: ");
            val = scan.nextInt();
            numbers.add(val);
        } while (val != -1);
        numbers.remove(numbers.size() - 1);
        BinarySearchTree tree = new BinarySearchTree(numbers);
        int choice;
        do {
            System.out.println("""
                               Choose action: 
                               1) Find value
                               2) Insert value
                               3) Remove value
                               4) Display
                               5) Traverse
                               6) Check Balance
                               7) Rebalance Tree""");
            choice = scan.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.print("Enter find value: ");
                    val = scan.nextInt();
                    System.out.println("Found " + val + ": " + tree.find(val));
                case 2:
                    System.out.print("Enter insert value: ");
                    val = scan.nextInt();
                    tree.insert(val);
                    System.out.println("Inserted " + val);
                    break;
                case 3:
                    System.out.print("Enter delete value: ");
                    val = scan.nextInt();
                    tree.delete(val);
                    System.out.println("Deleted " + val);
                    break;
                case 4:
                    tree.display();
                    break;
                case 5:
                    System.out.println("""
                                       1) Preorder
                                       2) Inorder
                                       3) Postorder
                                       """);
                    for (Integer a : tree.traverse(scan.nextInt())) {
                        System.out.print(a + " ");
                    }
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Tree Balanced: " + tree.balanced());
                    break;
                case 7:
                    tree.rebalance();
                    System.out.println("Tree Balanced: " + tree.balanced());
                    break;
                default:
                    System.out.print("Try again: ");
                    choice = scan.nextInt();
                    break;
            }
            System.out.println();
        } while (choice != -1);
    }
}
