package algorithm;

import algorithm.greedy_algorithm.GreedyAlgorithm;

import java.util.Arrays;

public class AppAlgorithm {

    public static void mainAlgorithms() {
//        sort();
//        GreedyAlgorithm.method();
        tree();
    }

    private static void sort() {
        var sort = new SimpleSort();
//        int[] array = new int[]{2, 1, 3, 7, 6, 5, 4};
//        int[] array = new int[]{3, 6, 1, 8, 2, 0, -1};
        int[] array = new int[]{6, 5, 1, 3, 8, 4, 7, 9, 2};
        System.out.println("До сортировки: " + Arrays.toString(array));

//        sort.bubbleSort(array); //O(n^2)
//        sort.selectionSort(array); //O(n^2)
//        sort.insertionSort(array); //O(n^2)
//        sort.ShellSort(array);
//        sort.quickSort(array, 0, array.length - 1); //O(n*log(n))
        sort.mergeSort(array, 0, array.length - 1); //O(n*log(n))

        System.out.println("После сортировки: " + Arrays.toString(array));
    }

    private static void tree() {
        System.out.println("================ Tree ================");
        Tree tree = new Tree();
        tree.depthAndWidthSearch();

        System.out.println("================ BinaryTree ================");
        BinaryTree bt = new BinaryTree();
        bt.binarySortedSearchTree();
    }
}
