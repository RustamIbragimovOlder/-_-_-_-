package lection2;

public class sort {

    public static void main(String[] args) {
        int[] array = new int[] { 4, 7, 2, 5, 9, 1, 0, 3, 6, 8 };
        for (int i = 0; i < array.length; i++) { // вывод исходного массива
            System.out.print(array[i] + " ");
        }
        System.out.println();
        // bubbleSort(array);
        // directSort(array);
        // insertSort(array);
        // quickSort(array, 0, array.length - 1);
        pyramidSort(array);
        for (int i = 0; i < array.length; i++) { // вывод массива после сортировки
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // Функция пузырьковой сортировки
    // сложность - O(n^2)
    public static void bubbleSort(int[] array) {
        boolean needSort;
        do {
            needSort = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    needSort = true;
                }
            }
        } while (needSort);
    }

    // Функция сортировки выбором
    // сложность - O(n^2)
    public static void directSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minPosition = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minPosition]) {
                    minPosition = j;
                }
            }
            if (minPosition != i) {
                int temp = array[i];
                array[i] = array[minPosition];
                array[minPosition] = temp;
            }
        }
    }

    // Функция сортировки вставками
    // сложность - O(n^2)
    public static void insertSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    // Функция быстрой сортировки
    // сложность - O(n * log n)
    public static void quickSort(int[] array, int startPosition, int endPosition) {
        int leftPosition = startPosition;
        int rightPosition = endPosition;
        int pivot = array[(startPosition + endPosition) / 2];
        do {
            while (array[leftPosition] < pivot) {
                leftPosition++;
            }
            while (array[rightPosition] > pivot) {
                rightPosition--;
            }
            if (leftPosition <= rightPosition) {
                if (leftPosition < rightPosition) {
                    int temp = array[leftPosition];
                    array[leftPosition] = array[rightPosition];
                    array[rightPosition] = temp;
                }
                leftPosition++;
                rightPosition--;
            }

        } while (leftPosition <= rightPosition);

        if (leftPosition < endPosition) {
            quickSort(array, leftPosition, endPosition);
        }
        if (startPosition < rightPosition) {
            quickSort(array, startPosition, rightPosition);
        }
    }

    // Функция пирамидальной сортировки (сортировка кучей)
    // сложность - O(n * log n)
    public static void pyramidSort(int[] array) {
        // Построение кучи (перегруппировка массива)
        for (int i = array.length / 2 -1; i >= 0; i--) {
            heapify(array, array.length, i);
        }

        // Один за другим извлекаем элементы из кучи
        for (int i = array.length - 1; i >=0; i--) {
            // Перемещаем текущий корень в конец
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Вызываем процедуру heapify на уменьшенной куче
            heapify(array, i, 0);
        }
    }

    // Процедура (функция) просеивания
    private static void heapify(int[] array, int heapSize, int rootIndex) {
        // Инициализируем наибольший элемент как корень
        int largest = rootIndex;
        int leftChild = 2 * rootIndex + 1; // левый дочерний элемент
        int rightChild = 2 * rootIndex + 2; // правый дочерний элемент

        // Если левый дочерний элемент больше корня
        if (leftChild < heapSize && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (rightChild < heapSize && array[rightChild] > array[largest]) {
            largest = rightChild;
        }
        // Если самый большой элемент не корень
        if (largest != rootIndex) {
            int temp = array[rootIndex];
            array[rootIndex] = array[largest];
            array[largest] = temp;

            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(array, heapSize, largest);
        }
    }


}
