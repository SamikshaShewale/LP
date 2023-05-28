import java.util.*;

class GraphColoring {
    private int numVertices; // Number of vertices in the graph
    private int[][] graph; // Adjacency matrix representation of the graph
    private int[] colors; // Array to store the assigned colors for each vertex
    private int numColors; // Number of colors used for coloring the graph

    public GraphColoring(int numVertices) {
        this.numVertices = numVertices;
        this.graph = new int[numVertices][numVertices]; // Initialize the adjacency matrix
        this.colors = new int[numVertices]; // Initialize the colors array
        Arrays.fill(this.colors, -1); // Set all colors to -1 (unassigned)
        this.numColors = 0; // Initialize the number of colors used to 0
    }

    public boolean isSafe(int vertex, int color) {
        // Check if assigning 'color' to 'vertex' violates any color conflicts with its adjacent vertices
        for (int i = 0; i < numVertices; i++) {
            if (graph[vertex][i] == 1 && colors[i] == color) {
                return false; // Color conflict found
            }
        }
        return true; // No color conflict
    }

    public int getHeuristic(int vertex) {
        // Calculate the heuristic value for a vertex (number of available colors for its adjacent vertices)
        Set<Integer> usedColors = new HashSet<>();
        for (int i = 0; i < numVertices; i++) {
            if (graph[vertex][i] == 1 && colors[i] != -1) {
                usedColors.add(colors[i]);
            }
        }
        return numColors - usedColors.size(); // Available colors = total colors - used colors
    }

    public boolean backtracking(int vertex) {
        if (vertex == numVertices) {
            return true; // Base case: All vertices have been assigned colors
        }

        for (int color = 0; color < numColors; color++) {
            if (isSafe(vertex, color)) {
                colors[vertex] = color; // Assign 'color' to 'vertex'

                if (backtracking(vertex + 1)) {
                    return true; // Recursive call for the next vertex
                }

                colors[vertex] = -1; // Backtrack: Reset color assignment for 'vertex'
            }
        }
        return false; // Backtrack: No color assignment possible for 'vertex'
    }

    public void branchAndBound() {
        numColors = 1; // Start with one color

        while (!backtracking(0)) {
            numColors++; // Increase the number of colors until a valid coloring is found
        }
    }

    public void solve() {
        branchAndBound();

        System.out.println("Graph Coloring Solution:");
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Vertex " + (i + 1) + " => Color " + (colors[i] + 1));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();

        GraphColoring problem = new GraphColoring(numVertices);

        System.out.println("Enter the adjacency matrix of the graph (0 for no edge, 1 for an edge):");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                problem.graph[i][j] = scanner.nextInt();
            }
        }

        problem.solve(); // Solve the graph coloring problem
    }
}
