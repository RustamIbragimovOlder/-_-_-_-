// Реализовать алгоритм пирамидальной сортировки (сортировка кучей).

package practical_tasks;

import java.util.Random;

public class practical_tasks_1 {

    private static final Random random = new Random();

    public static void main(String[] args) {
        
        // Проверка работы пирамидальной сортировки

        int[] arr = prepareArray(10); // подготовка массива длиной 10 элементов
        printArray(arr);                     // вывод исходного массива 
        pyramidSort(arr);                    // пирамидальная сортировка массива
        printArray(arr);                     // вывод отсортированного массива
        System.out.println();                // раздел пустой строкой

        // Сравнение быстродействия пирамидальной сортировки и быстрой сортировки
        
        int[] testArr = prepareArray(10_000_000); // создание тестового массива

        // Быстродействие пирамидальной сортировки

        long startTime = System.currentTimeMillis(); // задание времени старта
        pyramidSort(testArr.clone());                // запуск пирамидальной сортировки
        long endTime = System.currentTimeMillis();   // задание времени окончания
        long processingTime = endTime - startTime;   // расчет времени сортировки
        // вывод результатов
        System.out.printf("Время выполнения пирамидальной сортировки: %d мс.\n", processingTime);

        System.out.println(); // раздел пустой строкой

        // Быстродействие быстрой сортировки

        startTime = System.currentTimeMillis();       // задание времени старта
        quickSort(testArr.clone(), 0, testArr.length - 1); // запуск быстрой сортировки
        endTime = System.currentTimeMillis();         // задание времени окончания
        processingTime = endTime - startTime;         // расчет времени сортировки
        // вывод результатов
        System.out.printf("Время выполнения быстрой сортировки: %d мс.\n", processingTime);

    }
    
    // Функция пирамидальной сортировки (сортировка кучей)
    public static void pyramidSort(int[] array) {          // pyramidSort - имя функции
                                                           // int[] array - массив на вход функции
        
        // Построение кучи (перегруппировка массива)
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, array.length, i);
        }

        // Один за другим извлекаем элементы из кучи
        for (int i = array.length - 1; i >= 0; i--) {
            // Перемещаем текущий корень в конец
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Вызываем процедуру просеивания на уменьшенной куче
            heapify(array, i, 0);
        }
    }

    // Процедура (функция) просеивания
    private static void heapify(int[] array, int heapSize, int rootIndex) {

        int largest = rootIndex;            // определение наибольшего элемента как корень
        int leftChild = 2 * rootIndex + 1;  // определение левого дочернего элемента
        int rightChild = 2 * rootIndex + 2; // определение правого дочернего элемента
        
        // Сравнение корня с дочерними элементами

        // Если левый дочерний элемент больше корня
        // то корнем становится левый дочерний элемент
        if (leftChild < heapSize && array[leftChild] > array[largest]) {
            largest = leftChild;
        }
        
        // Если правый дочерний элемент больше корня (самый большой элемент на данный момент)
        // то корнем становится правый дочерний элемент
        if (rightChild < heapSize && array[rightChild] > array[largest]) {
            largest = rightChild;
        }
        
        // Если самый большой элемент не корень, то меняем их местами
        // и снова запускаем функцию просеивания
        if (largest != rootIndex) {
            int temp = array[rootIndex];
            array[rootIndex] = array[largest];
            array[largest] = temp;
            
            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(array, heapSize, largest);
        }
    }

    // Функция быстрой сортировки
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
     
    // Функция подготовки массива
    static int[] prepareArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(101) - 50;
        }
        return arr;
    }

    // Функция вывода массива
    static void printArray(int[] arr) {
        for (int e : arr) {
            System.out.printf("%d\t", e);
        }
        System.out.println();
    }

}