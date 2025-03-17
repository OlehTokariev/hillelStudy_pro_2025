package app;
import java.util.*;

public class Graph {
    private final Map<Integer, Set<Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    public void removeVertex(int vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return;
        }
        for (Integer adjacent : adjacencyList.get(vertex)) {
            adjacencyList.get(adjacent).remove(vertex);
        }
        adjacencyList.remove(vertex);
    }

    public void removeEdge(int source, int destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source);
        }
    }

    public boolean hasVertex(int vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public boolean hasEdge(int source, int destination) {
        if (adjacencyList.containsKey(source)) {
            return adjacencyList.get(source).contains(destination);
        }
        return false;
    }

    public void printGraph() {
        for (Integer vertex : adjacencyList.keySet()) {
            System.out.print(vertex + ": ");
            for (Integer adjacent : adjacencyList.get(vertex)) {
                System.out.print(adjacent + " ");
            }
            System.out.println();
        }
    }
}