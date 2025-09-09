package algorithm;

import java.util.Objects;

public class BinaryTree {

    /**
     * Алгоритм поиска в бинарном(отстортированном и 2 узла) дереве(графе без цикла) - O(log(n))
     */
    public void binarySortedSearchTree() {
        Node2 node0 = fillBinaryTree();

        System.out.println("Search element: ");
        System.out.println(binarySearchTree(node0, 7));

        System.out.println("Add element: ");
        addInBinaryTree(node0, 11, 7, null);

        System.out.println("Search element: ");
        System.out.println(binarySearchTree(node0, 7));

        System.out.println("Remove element: ");
        removeInBinaryTree(node0, 6);

        System.out.println("Search element: ");
        System.out.println(binarySearchTree(node0, 7));
    }

    private int binarySearchTree(Node2 node, Integer value) {
        if (Objects.nonNull(node) && value.equals(node.value)) {
            return node.id;
        } else {
            if (Objects.isNull(node)) {
                return -100;
            }
            if (value > node.value) {
                System.out.println("Node: " + node.id);
                return binarySearchTree(node.right, value);
            } else {
                System.out.println("Node: " + node.id);
                return binarySearchTree(node.left, value);
            }
        }
    }

    private void addInBinaryTree(Node2 node, int id, Integer value, Node2 nodePrev) {
        if (Objects.isNull(node)) {
            node = new Node2(id, value);
            if (value > nodePrev.value) {
                nodePrev.right = node;
            } else {
                nodePrev.left = node;
            }
            System.out.println(node.id + " " + node.value);
        } else {
            if (value > node.value) {
                System.out.println("Node: " + node.id);
                addInBinaryTree(node.right, id, value, node);
            } else {
                System.out.println("Node: " + node.id);
                addInBinaryTree(node.left, id, value, node);
            }
        }
    }

    private Node2 removeInBinaryTree(Node2 node, Integer value) {
        if (node == null) {
            return null;
        }

        // Найти узел для удаления
        if (value < node.value) {
            node.left = removeInBinaryTree(node.left, value);
        } else if (value > node.value) {
            node.right = removeInBinaryTree(node.right, value);
        } else {
            // Найден узел для удаления

            // Случай 1: Узел — лист
            if (node.left == null && node.right == null) {
                return null;
            }

            // Случай 2: Узел с одним ребёнком
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            // Случай 3: Узел с двумя детьми
            // Найти минимальное значение в правом поддереве
            Node2 minRightSubtree = findMin(node.right);
            // Заменить значение текущего узла
            node.value = minRightSubtree.value;
            node.id = minRightSubtree.id;
            // Удалить минимальный узел в правом поддереве
            node.right = removeInBinaryTree(node.right, minRightSubtree.value);
        }
        return node;
    }

    private Node2 findMin(Node2 node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    static class Node2 {
        int id;
        Integer value;
        Node2 left;
        Node2 right;

        Node2(int id, Integer value) {
            this.id = id;
            this.value = value;
        }
    }

    private Node2 fillBinaryTree() {
        Node2 node0 = new Node2(0, 8);
        Node2 node1 = new Node2(1, 3);
        Node2 node7 = new Node2(7, 12);
        node0.left = node1;
        node0.right = node7;

        Node2 node2 = new Node2(2, 2);
        Node2 node4 = new Node2(4, 5);
        node1.left = node2;
        node1.right = node4;

        Node2 node3 = new Node2(3, 1);
        node2.left = node3;

        Node2 node5 = new Node2(5, 4);
        Node2 node6 = new Node2(6, 6);
        node4.left = node5;
        node4.right = node6;

        Node2 node8 = new Node2(8, 9);
        Node2 node10 = new Node2(10, 14);
        node7.left = node8;
        node7.right = node10;

        Node2 node9 = new Node2(9, 10);
        node8.right = node9;

        return node0;
    }
}
