import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static boolean checkSafety(List<List<Integer>> b, int r, int n, int c) {
        // Traversing the row where the current queen is to be placed to check for conflicting queens
        for (int i = 0; i < c; i++) {
            if (b.get(r).get(i) == 1) {
                return false; // Conflicting queen found in the same row
            }
        }

        int r1 = r, c1 = c;
        // Traversing the top left diagonal to check for conflicting queens
        while (r1 != -1 && c1 != -1) {
            if (b.get(r1).get(c1) == 1) {
                return false; // Conflicting queen found in the top left diagonal
            }
            r1--;
            c1--;
        }

        // Traversing the bottom left diagonal to check for conflicting queens
        r1 = r;
        c1 = c;
        while (r1 < n && c1 != -1) {
            if (b.get(r1).get(c1) == 1) {
                return false; // Conflicting queen found in the bottom left diagonal
            }
            r1++;
            c1--;
        }

        return true; // No conflicting queen found
    }

    public static boolean NQueens(List<List<Integer>> b, int c, int n) {
        if (c == n) {
            return true; // Base case: All queens have been placed successfully
        }
        for (int i = 0; i < n; i++) {
            if (checkSafety(b, i, n, c)) {
                b.get(i).set(c, 1); // Place a queen at position (i, c)
                if (NQueens(b, c + 1, n)) {
                    return true; // Recursive call for the next column
                }
                b.get(i).set(c, 0); // Backtrack: Remove the queen from position (i, c)
            }
        }
        return false; // No solution found for the current configuration
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter the number of queens: ");
            int n = scanner.nextInt();
            if (n == -1) {
                System.out.println("\nThank You.....");
                return;
            }

            List<List<Integer>> b = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<Integer> x = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    x.add(0); // Initialize the board with zeros (no queens placed)
                }
                b.add(x);
            }

            if (NQueens(b, 0, n)) {
                System.out.println();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (b.get(i).get(j) == 1) {
                            System.out.print("Q "); // Print 'Q' for a queen
                        } else {
                            System.out.print(". "); // Print '.' for an empty cell
                        }
                    }
                    System.out.println();
                }
            } else {
                System.out.println("\nSolution not possible");
            }
            System.out.println("\nEnter -1 to exit...");
        }
    }
}
