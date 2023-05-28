import java.util.Arrays;
import java.util.Scanner;

public class DijkstraMSTGreedy {
    private static final int MAX_VALUE = Integer.MAX_VALUE;

    private int numVertices; // Number of vertices in the graph
    private int[][] graph; // Adjacency matrix representing the graph
    private boolean[] visited; // Array to track visited vertices
    private int[] parent; // Array to store the parent of each vertex in the MST
    private int[] distance; // Array to store the minimum weight edges

    public void findMST() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices in the graph: ");
        numVertices = scanner.nextInt();

        graph = new int[numVertices][numVertices];
        visited = new boolean[numVertices];
        parent = new int[numVertices];
        distance = new int[numVertices];

        System.out.print("Enter the adjacency matrix of the graph:\n");

        // Input the adjacency matrix of the graph
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter the starting vertex: ");
        int startVertex = scanner.nextInt();

        dijkstraMST(startVertex);

        System.out.println("Minimal Spanning Tree (MST):");

        // Print the edges and distances of the Minimal Spanning Tree (MST)
        for (int i = 0; i < numVertices; i++) {
            if (i != startVertex) {
                System.out.println("Edge: " + parent[i] + " - " + i + ", Distance: " + distance[i]);
            }
        }
    }

    private void dijkstraMST(int startVertex) {
        Arrays.fill(visited, false);
        Arrays.fill(distance, MAX_VALUE);

        distance[startVertex] = 0;
        parent[startVertex] = -1;

        for (int i = 0; i < numVertices - 1; i++) {
            int minVertex = findMinDistanceVertex();
            visited[minVertex] = true;

            for (int j = 0; j < numVertices; j++) {
                if (graph[minVertex][j] != 0 && !visited[j] && graph[minVertex][j] < distance[j]) {
                    parent[j] = minVertex;
                    distance[j] = graph[minVertex][j];
                }
            }
        }
    }

    private int findMinDistanceVertex() {
        int minDistance = MAX_VALUE;
        int minVertex = -1;

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minVertex = i;
            }
        }

        return minVertex;
    }

    public static void main(String[] args) {
        DijkstraMSTGreedy dijkstraMST = new DijkstraMSTGreedy();
        dijkstraMST.findMST();
    }
}
