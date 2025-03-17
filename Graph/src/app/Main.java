package app;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        System.out.println("Graph after adding vertices 1, 2, 3:");
        graph.printGraph();

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        System.out.println("Graph after adding edges between 1-2 and 2-3:");
        graph.printGraph();

        System.out.println("Graph contains vertex 2: " + graph.hasVertex(2));
        System.out.println("Graph contains edge between 1 and 2: " + graph.hasEdge(1, 2));
        System.out.println("Graph contains edge between 1 and 3: " + graph.hasEdge(1, 3));

        graph.removeEdge(1, 2);
        System.out.println("Graph after removing the edge between 1 and 2:");
        graph.printGraph();

        graph.removeVertex(2);
        System.out.println("Graph after removing vertex 2:");
        graph.printGraph();
    }
}