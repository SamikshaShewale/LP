// Import required classes and packages
import java.util.Scanner;
import java.util.Arrays;

// Create KruskalAlgorithm class to create minimum spanning tree of the given graph
class KruskalAlgorithm {
    // Create class Edge to represent an edge of the graph that implements the Comparable interface
    class Edge implements Comparable<Edge> {
        int source, destination, weight;

        // Implement the compareTo() method to compare edges based on weight
        public int compareTo(Edge edgeToCompare) {
            return this.weight - edgeToCompare.weight;
        }
    };

    // Create class Subset to represent a subset for union operation
    class Subset {
        int parent, value;
    };

    int vertices, edges; // Variables to store the number of vertices and edges
    Edge edgeArray[]; // Array to store the edges of the graph

    // Constructor to create a graph
    KruskalAlgorithm(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        edgeArray = new Edge[this.edges];
        for (int i = 0; i < edges; ++i)
            edgeArray[i] = new Edge(); // Create an edge for each given edge by the user
    }

    // Method to apply Kruskal's Algorithm and create the minimum spanning tree
    void applyKruskal() {
        Edge finalResult[] = new Edge[vertices]; // Array to store the final MST
        int newEdge = 0;
        int index = 0;
        for (index = 0; index < vertices; ++index)
            finalResult[index] = new Edge();

        Arrays.sort(edgeArray); // Sort the edges in ascending order based on weight

        Subset subsetArray[] = new Subset[vertices]; // Array to represent subsets of vertices

        // Allocate memory for subsets
        for (index = 0; index < vertices; ++index)
            subsetArray[index] = new Subset();

        // Create subsets with single elements
        for (int vertex = 0; vertex < vertices; ++vertex) {
            subsetArray[vertex].parent = vertex;
            subsetArray[vertex].value = 0;
        }
        index = 0;

        // Iterate through the edges and add the smallest edge that doesn't create a cycle
        while (newEdge < vertices - 1) {
            Edge nextEdge = new Edge();
            nextEdge = edgeArray[index++];

            int nextSource = findSetOfElement(subsetArray, nextEdge.source);
            int nextDestination = findSetOfElement(subsetArray, nextEdge.destination);

            // If adding the edge doesn't create a cycle, include it in the result
            if (nextSource != nextDestination) {
                finalResult[newEdge++] = nextEdge;
                performUnion(subsetArray, nextSource, nextDestination);
            }
        }

        // Print the edges of the final MST
        for (index = 0; index < newEdge; ++index)
            System.out.println(finalResult[index].source + " - " + finalResult[index].destination + ": " + finalResult[index].weight);
    }

    // Method to find the set of an element
    int findSetOfElement(Subset subsetArray[], int i) {
        if (subsetArray[i].parent != i)
            subsetArray[i].parent = findSetOfElement(subsetArray, subsetArray[i].parent);
        return subsetArray[i].parent;
    }

    // Method to perform union of two sets
    void performUnion(Subset subsetArray[], int sourceRoot, int destinationRoot) {
        int nextSourceRoot = findSetOfElement(subsetArray, sourceRoot);
        int nextDestinationRoot = findSetOfElement(subsetArray, destinationRoot);

        if (subsetArray[nextSourceRoot].value < subsetArray[nextDestinationRoot].value)
            subsetArray[nextSourceRoot].parent = nextDestinationRoot;
        else if (subsetArray[nextSourceRoot].value > subsetArray[nextDestinationRoot].value)
            subsetArray[nextDestinationRoot].parent = nextSourceRoot;
        else {
            subsetArray[nextDestinationRoot].parent = nextSourceRoot;
            subsetArray[nextSourceRoot].value++;
        }
    }

    // Main method
    public static void main(String[] args) {
        int v, e;
        Scanner sc = new Scanner(System.in); // Create a scanner object to get user input

        System.out.println("Enter the number of vertices: ");
        v = sc.nextInt(); // Read the number of vertices from the user

        System.out.println("Enter the number of edges: ");
        e = sc.nextInt(); // Read the number of edges from the user

        KruskalAlgorithm graph = new KruskalAlgorithm(v, e); // Create the graph

        // Get the details of each edge from the user
        for (int i = 0; i < e; i++) {
            System.out.println("Enter the source value for edge[" + i + "]: ");
            graph.edgeArray[i].source = sc.nextInt();

            System.out.println("Enter the destination value for edge[" + i + "]: ");
            graph.edgeArray[i].destination = sc.nextInt();

            System.out.println("Enter the weight for edge[" + i + "]: ");
            graph.edgeArray[i].weight = sc.nextInt();
        }

        graph.applyKruskal(); // Call the applyKruskal() method to get the MST
    }
}
