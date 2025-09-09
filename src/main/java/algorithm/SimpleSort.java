package algorithm;

import java.util.Arrays;

public class SimpleSort {

    /**
     * Сортировка пузырьком - O(n^2)
     * <p>
     * Он работает путем многократного прохода по массиву,
     * сравнивая попарно соседние элементы и меняя их местами,
     * если они стоят в неправильном порядке.
     */
    public void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                //Элемент слева больше элемента справа?
                if (arr[j] > arr[j + 1]) {
                    // Меняем элементы местами
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    System.out.println(Arrays.toString(arr));
                }
            }
        }
    }

    /**
     * Сортировка методом выбора - O(n^2)
     * <p>
     * Последовательный перебор всех чисел и выбор наименьшего (или наибольшего) элемента,
     * который мы возьмём и поменяем местами
     * с крайним элементом слева (0 элементом) (или справа (n-1 элементом)).
     */
    public void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_index = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_index]) {
                    min_index = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min_index];
            arr[min_index] = temp;

            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * Сортировка методом вставки - O(n^2)
     * <p>
     * Строит отсортированный массив по одному элементу за раз,
     * вставляя каждый новый элемент в нужную позицию
     * среди уже отсортированных элементов.
     */
    public void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i]; // делаем копию помеченного элемента

            int j = i;
            while (j > 0 && arr[j - 1] >= temp) { // пока не будет найден меньший элемент
                arr[j] = arr[j - 1]; // сдвигаем элементы вправо
                --j;
            }
            arr[j] = temp;   // вставляем отмеченный элемент, в положеное ему место
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * Сортировка Шелла
     */
    public void ShellSort(int[] arr) {
        int n = arr.length;
        int step = n / 2;
        while (step > 0) {
            for (int numberOfGroup = 0; numberOfGroup < n - step; numberOfGroup++) {
                int j = numberOfGroup;
                while (j >= 0 && arr[j] > arr[j + step]) {
                    int temp = arr[j];
                    arr[j] = arr[j + step];
                    arr[j + step] = temp;
                    j--;
                }
            }
            step = step / 2;
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * Быстрая сортировка - O(n*log(n))
     * <p>
     * Выбирается опорный элемент — по сути любой элемент,
     * относительно которого нужно отсортировать остальные значения.
     * Значения меньше его — слева, значения больше — справа.
     * <p>
     * Далее у правой и левой части также выбирается по опорному элементу
     * и происходит то же самое: и так до тех пор,
     * пока мы не получим отсортированный ряд.
     * (Повторяем рекурсивно для левой и правой частей, пока массив не будет отсортирован.)
     */
    public void quickSort(int[] array, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            // Определяем индекс опорного элемента после разбиения
            int pivotIndex = partition(array, startIndex, endIndex);

            // Рекурсивно применяем быструю сортировку к частям массива
            quickSort(array, startIndex, pivotIndex - 1);  // Левая часть
            quickSort(array, pivotIndex + 1, endIndex); // Правая часть
        }
    }

    // Метод для разбиения массива и возврата индекса опорного элемента
    private int partition(int[] array, int startIndex, int endIndex) {
        int pivot = array[endIndex];  // Выбираем последний элемент как опорный
        int wall = startIndex - 1; // Индекс меньшего элемента

        for (int j = startIndex; j < endIndex; j++) {
            // Если текущий элемент меньше или равен опорному
            if (array[j] <= pivot) {
                wall++;
                // Меняем местами элементы array[wall] и array[j]
                int temp = array[wall];
                array[wall] = array[j];
                array[j] = temp;
            }
        }

        // Меняем местами опорный элемент и первый элемент на стеной (wall + 1)
        int temp = array[wall + 1];
        array[wall + 1] = array[endIndex];
        array[endIndex] = temp;

        System.out.println(Arrays.toString(array));

        return wall + 1; // Возвращаем индекс опорного элемента (где стена сейчас)
    }

    /**
     * Быстрая слиянием - O(n*log(n))
     * <p>
     * Рекурсивно делит массив на две половины,
     * сортирует их и сливает обратно в отсортированном порядке.
     * <p>
     * 1) Разделяем массив пополам, пока не останутся одиночные элементы.
     * 2) Сортируем каждую часть рекурсивно.
     * 3) Объединяем (сливаем) два отсортированных подмассива в один.
     * <p>
     * Пример:
     * int[] arr = {8, 4, 6, 2, 9, 3, 5, 1};
     * <p>
     * Разбиваем массив:
     * [8, 4, 6, 2]   |   [9, 3, 5, 1]
     * [8, 4] [6, 2]  |   [9, 3] [5, 1]
     * [8] [4] [6] [2] |   [9] [3] [5] [1]
     * Сортируем и сливаем:
     * [4, 8] [2, 6]  |  [3, 9] [1, 5]
     * [2, 4, 6, 8]   |  [1, 3, 5, 9]
     * [1, 2, 3, 4, 5, 6, 8, 9] ✅
     */

    public void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            // Находим среднюю точку массива
            int middle = (left + right) / 2;

            // Рекурсивно сортируем первую и вторую половины массива
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            // Сливаем отсортированные половины
            merge(array, left, middle, right);
        }
    }

    // Метод для слияния двух подмассивов
    private void merge(int[] array, int left, int middle, int right) {
        // Определяем размеры временных массивов
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Создаем временные массивы
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Копируем данные во временные массивы
        for (int i = 0; i < n1; ++i)
            leftArray[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            rightArray[j] = array[middle + 1 + j];

        // Индексы для временных массивов и основного массива
        int i = 0, j = 0;
        int k = left;

        // Сливаем временные массивы в основной
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Копируем оставшиеся элементы, если они есть
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
        System.out.println(Arrays.toString(array));
    }

}