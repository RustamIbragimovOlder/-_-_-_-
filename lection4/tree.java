package lection4;

import java.util.ArrayList;
import java.util.List;

public class tree {
    public Node root; // базовое поле, начало дерева

    // Варианты обхода дерева
    
    // 1. Обход в глубину (рекурсивный обход узлов дерева)

    // поиск нужной ноды
    private Node find_in_depth(Node node, int value) {
        if (node.value == value) {
            return node;
        } else {
            for (Node child : node.children) {
                Node result = find_in_depth(child, value);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
    // отображение результата поиска
    public boolean exist_in_depth(int value) {
        if (root != null) {
            Node node = find_in_depth(root, value);
            if (node != null) {
                return true;
            }
        }
        return false;
    }


    // 2. Обход в ширину (без рекурсии)

    // поиск нужной ноды
    private Node find_in_width(int value) {
        List<Node> line = new ArrayList<>();
        line.add(root);
        while (line.size() > 0) {
            List<Node> nextLine = new ArrayList<>();
            for (Node node : line) {
                if (node.value == value) {
                    return node;
                }
                nextLine.addAll(node.children);
            }
            line = nextLine;
        }
        return null;
    }
    // отображение результата поиска
    public boolean exist(int value) {
        if (root != null) {
            Node node = find_in_width(value);
            if (node != null) {
                return true;
            }
        }
        return false;
    }

    public class Node {             // класс Узел
        public int value;           // значение узла
        public List<Node> children; // дети узла
    }

}