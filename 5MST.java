import java.util.Scanner;

class MST {
    private int V; // Number of vertices in the graph

    // Method to find the vertex with the minimum key value
    int minKey(int key[], Boolean mstSet[]) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // Method to print the Minimum Spanning Tree (MST)
    void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    // Method to find the Minimum Spanning Tree (MST) using Prim's algorithm
    void primMST(int graph[][]) {
        int parent[] = new int[V]; // Array to store the parent of each vertex in the MST
        int key[] = new int[V]; // Array to store the minimum weight edges
        Boolean mstSet[] = new Boolean[V]; // Array to track visited vertices

        // Initialize key values and mstSet array
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0; // Set the key value of the first vertex as 0
        parent[0] = -1; // First vertex is always the root of MST

        // MST construction
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet); // Find the vertex with the minimum key value
            mstSet[u] = true; // Mark the vertex as visited

            // Update key values and parent array for the neighboring vertices
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph); // Print the Minimum Spanning Tree (MST)
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MST t = new MST(); // Create a new MST object

        System.out.print("Enter the number of vertices: ");
        t.V = scanner.nextInt(); // Get the number of vertices

        int graph[][] = new int[t.V][t.V]; // Create an adjacency matrix for the graph
        System.out.println("Enter the adjacency matrix (enter 0 for no edge):");

        // Read the adjacency matrix from the user
        for (int i = 0; i < t.V; i++) {
            for (int j = 0; j < t.V; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        scanner.close(); // Close the scanner

        t.primMST(graph); // Find and print the Minimum Spanning Tree (MST) using Prim's algorithm
    }
}
