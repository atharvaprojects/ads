import java.util.*;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class bst_ass {
    // Node root = null;
    public Node insertNode(Node root, int data) {
        Node new_node = new Node(data);
        // base case
        if (root == null) {
            root = new_node;
            return root;
        }

        if (data < root.data) {
            root.left = insertNode(root.left, data);
        }

        else if (data >= root.data) {
            root.right = insertNode(root.right, data);
        }
        return root;
    }

    public Node deleteNode(Node root, int value) {
        if (root == null) {
            System.out.println("The tree is empty.");
            return null;
        }

        if (root.data > value)
            root.left = deleteNode(root.left, value);

        else if (root.data < value)
            root.right = deleteNode(root.right, value);

        else {
            // Node to be deteted has both the child
            if (root.left != null && root.right != null) {
                Node temp = root;
                Node minNodeRight = minimum(temp.right);

                root.data = minNodeRight.data;
                root.right = deleteNode(root.right, minNodeRight.data);
            }

            // if the node has only left child
            else if (root.left != null)
                root = root.left;

                // if the node has only right child
            else if (root.right != null)
                root = root.right;

                // has no child
            else
                root = null;
        }
        return root;
    }

    public Node minimum(Node root) {
        if (root.left == null)
            return root;
        return minimum(root.left);
    }

    // deleting a node:
    public Node delete_node(Node root, int data)
    {
        if (root == null)
        {
            System.out.println("tree is empty");
            return null;
        }

        Node temp = root;
        while (temp != null)
        {
            if (data < temp.data) {
                if (temp.left != null && temp.left.data == data)
                {
                    // call helper function
                    temp.left = helper(temp.left);
                    break;
                } else {
                    temp = temp.left;
                }
            }
            else {
                if (temp.right != null && temp.right.data == data)
                {
                    // call helper function
                    temp.right = helper(temp.right);
                    break;
                } else {
                    temp = temp.right;
                }
            }
        }
        return root;
    }

    public Node helper(Node root)
    {
        if (root.left == null)
        {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }

        Node rightChild = root.right;
        Node rightMost = findLastRight(root.left);
        rightMost.left = rightChild;
        return root.left;
    }

    public Node findLastRight(Node root)
    {
        Node temp = root;
        while (temp.right != null)
        {
            temp = temp.right;
        }
        return temp;
    }

    public Node searchNode(Node root, int data) {
        if (root == null || root.data == data)
            return root;
        if (data >= root.data)
            return searchNode(root.right, data);
        return searchNode(root.left, data);
    }

    public void search_node(Node root, int data)
    {
        Node temp = root;
        boolean found = false;
        while(temp != null)
        {
            if (data > temp.data)
            {
                temp = temp.right;
            }
            else if (data < temp.data) {
                temp = temp.left;
            }
            else {
                found = true;
                break;
            }
        }
        if (found)
        {
            System.out.println("found");
        }
        else {
            System.out.println("not found");
        }
    }

    public void Inorder(Node root) {
        if (root != null) {
            Inorder(root.left);
            System.out.print(root.data + " ");
            Inorder(root.right);
        }
    }

    public void levelOrderPrint(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            if (temp == null) {
                System.out.println();
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            } else {
                System.out.print(temp.data + " ");

                if (temp.left != null)
                    queue.add(temp.left);

                if (temp.right != null)
                    queue.add(temp.right);
            }
        }
    }

    public void findPredecessorAndSuccessor(Node root, int key) {
        int predecessor = -1;
        int successor = -1;

        Node temp = root;
        while (temp != null && temp.data != key) {
            if (key > temp.data) {
                predecessor = temp.data;
                temp = temp.right;
            } else { // (key < temp.data)
                successor = temp.data;
                temp = temp.left;
            }
        }

        // Key not found
        if (temp == null) {
            System.out.println("The node with key " + key + " doesn't present in the tree.");
            System.out.println("Inorder predecessor and successor cannot be determined.");
            return;
        }

        // now the key is found
        // find predecessor which is present in left subtree
        // take one left and then the rightmost element
        Node leftTree = temp.left;
        while (leftTree != null) {
            predecessor = leftTree.data;
            leftTree = leftTree.right;
        }

        // find successor which is present in right subtree
        // take one right and then the leftmost element
        Node rightTree = temp.right;
        while (rightTree != null) {
            successor = rightTree.data;
            rightTree = rightTree.left;
        }
        System.out.println("The inorder predecessor of " + key + " is : " + predecessor);
        System.out.println("The inorder successor of " + key + " is : " + successor);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node root = null;
        bst_ass tree1 = new bst_ass();
        System.out.println("Welcome user !! \nThis is the system which performs Binary search Tree Opeartions : ");
        System.out.println("-----------------------------------------");

        boolean x = true;
        while (x) {
            System.out.println("Operations Available  : ");
            System.out.println(
                    "1. Insert the Node \n2. Delete the Node \n3. Search the Node \n4. Inorder print \n5. Level order print \n6. Find the inorder predecessor and sucessor of the node you want \n7.Exit");
            System.out.println("-----------------------------------------");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the data to create the BST : ");
                    int data = sc.nextInt();
                    while (data != -1) {
                        root = tree1.insertNode(root, data);
                        data = sc.nextInt();
                    }
                    System.out.println("The data get successfully inserted.");
                    System.out.println("\n-----------------------------------------");
                    break;

                case 2:
                    System.out.println("Enter the element that you want to delete : ");
                    int element = sc.nextInt();
//                    root = tree1.deleteNode(root, element);
                    root = tree1.delete_node(root,element);
                    System.out.println("The element " + element + " get successfully deleted.");
                    System.out.println("\n-----------------------------------------");
                    break;

                case 3:
                    System.out.print("Enter the element that you want to search : ");
                    int search = sc.nextInt();
//                    if (tree1.searchNode(root, search) == null)
//                        System.out.println(search + " is not fount in BST.");
//                    else
//                        System.out.println(search + " is present in the BST.");

                    tree1.search_node(root,search);
                    System.out.println("\n-----------------------------------------");
                    break;

                case 4:
                    System.out.println("The Inorder representation of BST is : ");
                    tree1.Inorder(root);
                    System.out.println("\n-----------------------------------------");
                    break;

                case 5:
                    System.out.println("The level order representation of BST is : ");
                    tree1.levelOrderPrint(root);
                    System.out.println("\n-----------------------------------------");
                    break;

                case 6:
                    System.out.print("Enter the node whose predecessor and successor you want to find : ");
                    int key = sc.nextInt();
                    tree1.findPredecessorAndSuccessor(root, key);
                    System.out.println("\n-----------------------------------------");
                    break;
                case 7:
                    System.out.println(" !! Thank you for respounding !! \nHave a nice day ");
                    System.out.println("\n-------------------------------------");
                    x = false;
                    break;
            }
        }
    }
}