import java.util.*;

class Edge implements Comparable<Edge> {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class Graph {
    int V, E; // V-> no. of vertices & E->no.of edges
    List<Edge> edges;

    public Graph(int V, int E) {
        this.V = V;
        this.E = E;
        edges = new ArrayList<>();
    }

    // Add an edge to the graph
    void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    // Find set of an element i
    int find(int[] parent, int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    // Union of two sets of x and y
    void union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    // Kruskal's algorithm
    void kruskalMST() {
        Edge[] result = new Edge[V];
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        Collections.sort(edges);

        int e = 0;
        int i = 0;
        while (e < V - 1 && i < E) {
            Edge next_edge = edges.get(i++);
            int x = find(parent, next_edge.source);
            int y = find(parent, next_edge.destination);

            if (x != y) {
                result[e++] = next_edge;
                union(parent, x, y);
            }
        }

        int totalWeight = 0;
        for (i = 0; i < e; ++i) {
            totalWeight += result[i].weight;
            System.out.println(result[i].source + " - " + result[i].destination + " : " + result[i].weight);
        }
        System.out.println("Total weight of Minimum Spanning Tree: " + totalWeight);
    }
}

public class Assignment_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int V, E;

        System.out.print("Enter the number of vertices: ");
        V = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        E = scanner.nextInt();

        Graph graph = new Graph(V, E);

        for (int i = 0; i < E; i++) {
            System.out.println("Enter details for edge " + (i + 1) + ":");
            System.out.print("Source: ");
            int source = scanner.nextInt();
            System.out.print("Destination: ");
            int destination = scanner.nextInt();
            System.out.print("Weight: ");
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Calculate total weight of Minimum Spanning Tree");
            System.out.println("2. Display one specific Minimum Spanning Tree");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    graph.kruskalMST();
                    break;
                case 2:
                    // Displaying minimum spanning tree
                    System.out.println("Minimum Spanning Tree:");
                    graph.kruskalMST();
                    break;
                case 3:
                    System.out.println("Exiting program!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        } while (choice != 3);

        scanner.close();
    }
}
