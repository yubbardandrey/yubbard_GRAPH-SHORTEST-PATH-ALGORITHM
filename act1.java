import java.util.*;

class Graph {
    private int V; // Jumlah node/vertex
    private List<List<Node>> adjList; // Representasi adjacency list untuk menyimpan edge

    // Representasi node dalam graph
    static class Node {
        int vertex, weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public Graph(int V) {
        this.V = V;
        adjList = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Menambahkan edge ke graph
    public void addEdge(int source, int destination, int weight) {
        adjList.get(source).add(new Node(destination, weight));
        adjList.get(destination).add(new Node(source, weight)); // Undirected graph
    }

    // Algoritma Dijkstra untuk mencari shortest path
    public void dijkstra(int startVertex) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startVertex] = 0;
        priorityQueue.add(new Node(startVertex, 0));

        while (!priorityQueue.isEmpty()) {
            int currentVertex = priorityQueue.poll().vertex;

            for (Node neighbor : adjList.get(currentVertex)) {
                int newDistance = distance[currentVertex] + neighbor.weight;

                if (newDistance < distance[neighbor.vertex]) {
                    distance[neighbor.vertex] = newDistance;
                    priorityQueue.add(new Node(neighbor.vertex, newDistance));
                }
            }
        }

        // Print shortest distances
        System.out.println("Shortest distances from vertex " + startVertex + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To vertex " + i + ": " + distance[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int V = 5; // Jumlah vertex/nodes
        Graph graph = new Graph(V);

        // Menambahkan edge ke graph
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 1);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 4);

        int startVertex = 0; // Vertex awal untuk mencari shortest path
        graph.dijkstra(startVertex);
    }
}