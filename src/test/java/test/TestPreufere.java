package test;

import app.codePreufer.logic.Preufer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPreufere {
    @Test
    public void testPreufere() {
        String pruferCodeStr = "7 7 7 7 7";

        Preufer tree = new Preufer(pruferCodeStr);
        tree.buildTree();

        Map<Integer, List<Integer>> expectedAdjacencyList = new TreeMap<>();
        expectedAdjacencyList.put(1, List.of(7));
        expectedAdjacencyList.put(2, List.of(7));
        expectedAdjacencyList.put(3, List.of(7));
        expectedAdjacencyList.put(4, List.of(7));
        expectedAdjacencyList.put(5, List.of(7));
        expectedAdjacencyList.put(6, List.of(7));
        expectedAdjacencyList.put(7, List.of(1, 2, 3, 4, 5, 6));

        assertEquals(expectedAdjacencyList, tree.getAdjacencyList());
    }
}
