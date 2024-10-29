package app.codePreufer.logic;

import java.util.*;

public class Preufer {
    private final int[] pruferCode;
    private final int n; // Количество вершин
    private final List<List<Integer>> adjacencyList;

    public Preufer(String pruferCodeStr) {
        String[] codeParts = pruferCodeStr.trim().split("\\s+");
        this.pruferCode = Arrays.stream(codeParts).mapToInt(Integer::parseInt).toArray();
        this.n = pruferCode.length + 2;

        this.adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public Map<Integer, List<Integer>> getAdjacencyList() {
        Map<Integer, List<Integer>> result = new TreeMap<>();
        for (int i = 0; i < adjacencyList.size(); i++) {
            List<Integer> neighbors = adjacencyList.get(i);
            Collections.sort(neighbors);  // Сортируем смежные вершины
            result.put(i + 1, neighbors); // Нумерация вершин с 1
        }
        return result;
    }

    public void buildTree() {
        int[] degree = new int[n];
        Arrays.fill(degree, 1);

        // Увеличиваем степени вершин, которые встречаются в коде Прюфера
        for (int node : pruferCode) {
            if (node - 1 < 0 || node - 1 >= n) {  // Проверяем, что node в пределах допустимых значений
                throw new IllegalArgumentException("Недопустимое значение в коде Прюфера: " + node);
            }
            degree[node - 1]++;
        }

        // Строим дерево
        for (int node : pruferCode) {
            for (int i = 0; i < n; i++) {
                if (degree[i] == 1) {
                    // Добавляем ребро между `node` и `i + 1`
                    adjacencyList.get(i).add(node);
                    adjacencyList.get(node - 1).add(i + 1);
                    degree[i]--;
                    degree[node - 1]--;
                    break;
                }
            }
        }

        // Добавляем последнее ребро между двумя оставшимися листьями
        int u = -1, v = -1;
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                if (u == -1) u = i;
                else v = i;
            }
        }

        // Обновляем список смежности для последних вершин
        adjacencyList.get(u).add(v + 1);
        adjacencyList.get(v).add(u + 1);
    }


    public void printAdjacencyList() {
        for (int i = 0; i < adjacencyList.size(); i++) {
            Collections.sort(adjacencyList.get(i));
            System.out.print((i + 1) + ": ");
            for (int neighbor : adjacencyList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}
