import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class GraphTraversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = scanner.nextInt();
        System.out.print("Enter number of edges: ");
        int m = scanner.nextInt();
        int[][] a = new int[n][n]; // Adjacency matrix to represent the graph
        System.out.println("Now enter vertex numbers between which edges are present");
        int v1, v2;
        for (int i = 0; i < m; i++) {
            System.out.print("Vertex: ");
            v1 = scanner.nextInt();
            System.out.print("Vertex: ");
            v2 = scanner.nextInt();
            System.out.println("Vertex " + v1 + " <---------> Vertex " + v2);
            a[v1][v2] = 1; // Mark the edge between v1 and v2 as present
            a[v2][v1] = 1; // Since the graph is undirected, mark the edge between v2 and v1 as present as well
        }
        
        Queue<Integer> q = new LinkedList<>(); // Queue for BFS traversal
        boolean[] v = new boolean[n]; // Array to track visited vertices
        for (int i = 0; i < n; i++) {
            v[i] = false; // Mark all vertices as not visited initially
        }
        System.out.print("Enter the root vertex: ");
        int root = scanner.nextInt();
        q.add(root); // Add the root vertex to the queue
        System.out.println("\nFollowing is the BFS traversal of the provided graph:");
        while (!q.isEmpty()) {
            int x = q.poll(); // Retrieve and remove the vertex at the front of the queue
            System.out.print(x + " "); // Process the vertex
            v[x] = true; // Mark the vertex as visited
            for (int i = 0; i < n; i++) {
                if (a[x][i] == 1 && !v[i]) { // If there is an edge between x and i, and i is not visited
                    v[i] = true; // Mark i as visited
                    q.add(i); // Add i to the queue for further traversal
                }
            }
        }

        for (int i = 0; i < n; i++) {
            v[i] = false; // Reset the visited array
        }
        Stack<Integer> s = new Stack<>(); // Stack for DFS traversal
        s.push(root); // Push the root vertex onto the stack
        System.out.println("\n\nFollowing is the DFS traversal of the provided graph:");
        while (!s.isEmpty()) {
            int x = s.pop(); // Remove and retrieve the vertex at the top of the stack
            System.out.print(x + " "); // Process the vertex
            v[x] = true; // Mark the vertex as visited
            for (int i = 0; i < n; i++) {
                if (a[x][i] == 1 && !v[i]) { // If there is an edge between x and i, and i is not visited
                    v[i] = true; // Mark i as visited
                    s.push(i); // Push i onto the stack for further traversal
                }
            }
        }
        scanner.close();
    }
}
