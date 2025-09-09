package algorithm;

import java.util.*;

public class Tree {

    public void depthAndWidthSearch() {
        Node node0 = fillTree();

        System.out.println("Алгоритм поиска в глубину: ");
        System.out.println(depthFirstSearch(node0, 6));

        System.out.println("Алгоритм поиска в ширину: ");

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node0);

        System.out.println(breathFirstSearch(queue, 6));
    }

    /**
     * Алгоритм поиска в глубину - O(n)
     */
    private String depthFirstSearch(Node node, int id) {
        if (id == node.id) {
            return node.value;
        } else {
            for (Node child : node.children) {
                System.out.println("Node : " + child.id + " " + child.value);
                String res = depthFirstSearch(child, id);
                if (!res.equals("")) {
                    return res;
                }
            }
            return "";
        }
    }

    /**
     * Алгоритм поиска в ширину - O(n)
     */
    private String breathFirstSearch(Queue<Node> queue, int id) {
        if (!queue.isEmpty()) {
            Node first = queue.peek(); //возвращает голову очереди, не удаляя
            System.out.println("Node = " + first.id + " " + first.value);
            if (id == first.id) {
                return first.value;
            } else {
                queue.addAll(first.children);
                queue.remove(first);

                String res = breathFirstSearch(queue, id);
                if (!res.equals("")) {
                    return res;
                }
            }
        } else {
            return "";
        }
        return "";
    }

    static class Node {
        int id;
        String value;
        List<Node> children = new ArrayList<>();

        Node(int id, String value) {
            this.id = id;
            this.value = value;
        }
    }

    private Node fillTree() {
        Node node0 = new Node(0, "A");

        Node node1 = new Node(1, "B");
        Node node7 = new Node(7, "C");
        Node node8 = new Node(8, "D");

        node0.children.addAll(List.of(node1, node7, node8));

        Node node2 = new Node(2, "E");
        Node node4 = new Node(4, "G");

        node1.children.addAll(List.of(node2, node4));

        Node node3 = new Node(3, "F");
        node2.children.add(node3);

        Node node5 = new Node(5, "M");
        Node node6 = new Node(6, "N");
        node4.children.addAll(List.of(node5, node6));

        Node node9 = new Node(9, "H");
        node8.children.add(node9);

        Node node10 = new Node(10, "L");
        node9.children.add(node10);

        return node0;
    }
}