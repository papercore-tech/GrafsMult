package app;

import app.myClass.CartesianProductGraph;
import app.myClass.Edge;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Вершины графов G1 и G2
        String[] verticesG1 = {"1_A", "2_A", "3_A", "4_A", "5_A"};
        String[] verticesG2 = {"1_B", "2_B", "3_B", "4_B", "5_B"};

        // Рёбра графа G1
        Set<Edge> edgesG1 = CartesianProductGraph.createEdges(new String[][]{
                {"1_A", "2_A"}, {"1_A", "5_A"},
                {"2_A", "3_A"}, {"5_A", "3_A"},
                {"2_A", "4_A"}, {"3_A", "4_A"}
        });

        // Рёбра графа G2
        Set<Edge> edgesG2 = CartesianProductGraph.createEdges(new String[][]{
                {"1_B", "5_B"}, {"1_B", "2_B"},
                {"2_B", "5_B"}, {"2_B", "3_B"},
                {"5_B", "4_B"}, {"3_B", "4_B"}
        });

        // Создание графа декартова произведения
        CartesianProductGraph cartesianGraph = new CartesianProductGraph(verticesG1, verticesG2, edgesG1, edgesG2);

        // Экспорт в формат GraphML
        cartesianGraph.exportToGraphML("cartesian_product_graph.graphml");
    }
}
