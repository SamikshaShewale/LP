import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

// Class representing a node in the search tree
class Node {
    Node parent;        // Parent node in the search tree
    int[][] mat;        // Matrix representing the current state
    int x, y;           // Position of the blank space (0)
    int hscore;         // Heuristic score
    int gscore;         // Cost from the initial state to the current state

    Node(int[][] mat, int x, int y, int newX, int newY, int gscore, Node parent) {
        this.parent = parent;
        this.mat = new int[3][3];

        // Copy the matrix to avoid modifying the original matrix
        for (int i = 0; i < 3; i++) {
            this.mat[i] = Arrays.copyOf(mat[i], 3);
        }

        // Swap the blank space with the new position
        swap(this.mat, x, y, newX, newY);

        this.hscore = Integer.MAX_VALUE;
        this.gscore = gscore;
        this.x = newX;
        this.y = newY;
    }

    // Helper method to swap two elements in the matrix
    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }
}

// Class for graph traversal and solving the puzzle
class GraphTraversal {
    static void printMatrix(int[][] mat) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    static Node newNode(int[][] mat, int x, int y, int newX, int newY, int gscore, Node parent) {
        return new Node(mat, x, y, newX, newY, gscore, parent);
    }

    // Calculate the number of misplaced tiles between two matrices
    static int calculateCost(int[][] initial, int[][] finalMat) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (initial[i][j] != 0 && initial[i][j] != finalMat[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // Check if the given position (x, y) is within the bounds of the matrix
    static boolean isSafe(int x, int y) {
        return (x >= 0 && x < 3 && y >= 0 && y < 3);
    }

    // Print the path from the initial state to the goal state
    static void printPath(Node root) {
        if (root == null) {
            return;
        }
        printPath(root.parent);
        printMatrix(root.mat);
        System.out.println("hscore: " + root.hscore + "\ngscore: " + root.gscore +
                "\nfscore: " + (root.hscore + root.gscore) + "\n");
    }

    // Comparator for comparing nodes based on their f-score (heuristic + cost)
    static class NodeComparator implements Comparator<Node> {
        public int compare(Node lhs, Node rhs) {
            return (lhs.hscore + lhs.gscore) - (rhs.hscore + rhs.gscore);
        }
    }

    // Solve the puzzle using A* algorithm
    static void solve(int[][] initial, int x, int y, int[][] finalMat) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());

        // Create the root node and calculate its heuristic score
        Node root = newNode(initial, x, y, x, y, 0, null);
        root.hscore = calculateCost(initial, finalMat);
        pq.add(root);

        while (!pq.isEmpty()) {
            Node min = pq.poll();

            // If the goal state is reached, print the path and return
            if (min.hscore == 0) {
                printPath(min);
                return;
            }

            // Generate child nodes by moving the blank space in all possible directions
            int[] row = { -1, 0, 1, 0 };
            int[] col = { 0, -1, 0, 1 };
            for (int i = 0; i < 4; i++) {
                if (isSafe(min.x + row[i], min.y + col[i])) {
                    Node child = newNode(min.mat, min.x, min.y, min.x + row[i],
                            min.y + col[i], min.gscore + 1, min);
                    child.hscore = calculateCost(child.mat, finalMat);
                    pq.add(child);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] initial = new int[3][3];
        int x = 0, y = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nEnter Initial Block Structure\n\nEnter 0 for blank space:\n");

        // Get the input for the initial state of the puzzle
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Row " + (i + 1) + " Column " + (j + 1) + " Element = ");
                initial[i][j] = scanner.nextInt();
                if (initial[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }

        int[][] finalMat = new int[3][3];
        System.out.println("\n\nEnter Final Block Structure\n\nEnter 0 for blank space:\n");

        // Get the input for the goal state of the puzzle
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Row " + (i + 1) + " Column " + (j + 1) + " Element = ");
                finalMat[i][j] = scanner.nextInt();
            }
        }

        System.out.println("\nInitial Structure:\n");
        printMatrix(initial);

        System.out.println("\nFinal Structure:\n");
        printMatrix(finalMat);

        System.out.println("\n\nThis is the solution using A* Algorithm:\n");
        solve(initial, x, y, finalMat);
    }
}
