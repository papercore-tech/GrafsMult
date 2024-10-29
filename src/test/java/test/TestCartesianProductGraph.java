package test;

import app.productGraphs.logic.CartesianProductGraph;
import app.productGraphs.model.Edge;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCartesianProductGraph {

    @Test
    public void testGenerateCartesianVertices() {
        String[] verticesG1 = {"1_A", "2_A"};
        String[] verticesG2 = {"1_B", "2_B"};
        Set<Edge> edgesG1 = CartesianProductGraph.createEdges(new String[][]{
                {"1_A", "2_A"}
        });
        Set<Edge> edgesG2 = CartesianProductGraph.createEdges(new String[][]{
                {"1_B", "2_B"}
        });

        CartesianProductGraph graph = new CartesianProductGraph(verticesG1, verticesG2, edgesG1, edgesG2);
        Set<String> expectedVertices = Set.of("1_A_1_B", "1_A_2_B", "2_A_1_B", "2_A_2_B");

        assertEquals(expectedVertices, graph.vertices(), "Вершины декартова произведения не совпадают с ожидаемыми.");
    }
}
