package app.productGraphs.logic;

import app.productGraphs.model.Edge;
import lombok.Getter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Getter
public class CartesianProductGraph {
    private final Set<String> vertices;
    private final Set<Edge> edges;

    public CartesianProductGraph(String[] verticesG1, String[] verticesG2, Set<Edge> edgesG1, Set<Edge> edgesG2) {
        this.vertices = generateCartesianVertices(verticesG1, verticesG2);
        this.edges = generateCartesianEdges(verticesG1, verticesG2, edgesG1, edgesG2);
    }

    public static Set<Edge> createEdges(String[][] edgePairs) {
        Set<Edge> edges = new HashSet<>();
        for (String[] pair : edgePairs) {
            if (pair.length == 2) {
                edges.add(new Edge(pair[0], pair[1]));
            }
        }
        return edges;
    }

    private Set<String> generateCartesianVertices(String[] verticesG1, String[] verticesG2) {
        Set<String> vertices = new HashSet<>();
        for (String v1 : verticesG1) {
            for (String v2 : verticesG2) {
                vertices.add(v1 + "_" + v2);
            }
        }
        return vertices;
    }

    private Set<Edge> generateCartesianEdges(String[] verticesG1, String[] verticesG2, Set<Edge> edgesG1, Set<Edge> edgesG2) {
        Set<Edge> edges = new HashSet<>();

        for (Edge edgeG1 : edgesG1) {
            for (String v2 : verticesG2) {
                String vertex1 = edgeG1.start() + "_" + v2;
                String vertex2 = edgeG1.end() + "_" + v2;
                edges.add(new Edge(vertex1, vertex2));
            }
        }

        for (String v1 : verticesG1) {
            for (Edge edgeG2 : edgesG2) {
                String vertex1 = v1 + "_" + edgeG2.start();
                String vertex2 = v1 + "_" + edgeG2.end();
                edges.add(new Edge(vertex1, vertex2));
            }
        }

        return edges;
    }

    public void exportToGraphML(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\"\n");
            writer.write("         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
            writer.write("         xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns\n");
            writer.write("         http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd\">\n");
            writer.write("  <graph id=\"G\" edgedefault=\"undirected\">\n");

            for (String vertex : vertices) {
                writer.write("    <node id=\"" + vertex + "\"/>\n");
            }

            int edgeIndex = 1;
            for (Edge edge : edges) {
                writer.write("    <edge id=\"e" + edgeIndex++ + "\" source=\"" + edge.start() +
                        "\" target=\"" + edge.end() + "\"/>\n");
            }

            writer.write("  </graph>\n</graphml>");
            System.out.println("Файл успешно создан: " + fileName);

        } catch (IOException e) {
            System.out.println("Ошибка записи файла: " + e.getMessage());
        }
    }
}
