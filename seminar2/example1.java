package seminar2;

import java.util.Random;

public class example1 {

    private static final Random random = new Random();

    public static void main(String[] args) {

        int[] arr = ArrayUtils.prepareArray();
        ArrayUtils.printArray(arr);
        SortUtils.directSort(arr);
        ArrayUtils.printArray(arr);

        System.out.println();

        arr = ArrayUtils.prepareArray();
        ArrayUtils.printArray(arr);
        SortUtils.insertSort(arr);
        ArrayUtils.printArray(arr);

        System.out.println();

        arr = ArrayUtils.prepareArray();
        ArrayUtils.printArray(arr);
        SortUtils.quickSort(arr);
        ArrayUtils.printArray(arr);

        int[] testArr = ArrayUtils.prepareArray(100000);
        long startTime = System.currentTimeMillis();
        SortUtils.directSort(testArr.clone());
        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;
        System.out.printf("Время выполнения сортировки выбором: %d мс.\n", processingTime);

        System.out.println();

        startTime = System.currentTimeMillis();
        SortUtils.quickSort(testArr.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время выполнения быстрой сортировки: %d мс.\n", processingTime);

        System.out.println();

        startTime = System.currentTimeMillis();
        SortUtils.insertSort(testArr.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время выполнения сортировки вставками: %d мс.\n", processingTime);

        int[] testArr2 = new int[] {-5, 100, -1, 3, 10, 5, -6, 5, 10, 11};
        ArrayUtils.printArray(testArr2);
        SortUtils.quickSort(testArr2);
        ArrayUtils.printArray(testArr2);
        int searchElement = 10;
        int res1 = SearchUtils.binarySearch(testArr2, searchElement);
        System.out.printf("Элемент %d %s\n", searchElement, res1 >= 0 ? String.format("найден в массиве по индексу %d", res1) : "не найден");

    }

    // вспомогательный класс
    static class ArrayUtils {

        // функция подготовки массива
        private static int[] prepareArray() {
            int[] arr = new int[random.nextInt(16) + 5];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(101) - 50;
            }
            return arr;
        }

        // Перегруженная версия предыдущей функции (произвольный размер массива)
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

    static class SortUtils {

        // Сортировка выбором
        static void directSort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int save = i; // сохраняем текущий индекс элемента
                for (int j = i + 1; j < arr.length; j++) { // продолжаем со следующего элемента (i + 1)
                    if (arr[j] < arr[save]) {
                        save = j;
                    }
                }
                if (save != i) {
                    int buf = arr[i];
                    arr[i] = arr[save];
                    arr[save] = buf;
                }
            }
        }

        // Сортировка вставками
        static void insertSort(int[] arr) {
            for (int i = 1; i < arr.length; i++) {
                for (int j = i; j > 0; j--) {
                    if (arr[j - 1] > arr[j]) {
                        int buf = arr[j - 1];
                        arr[j - 1] = arr[j];
                        arr[j] = buf;
                    }
                }
            }
        }

        // Быстрая сортировка
        // Перегруженная функция
        static void quickSort(int[] arr) {
            quickSort(arr, 0, arr.length - 1);
        }

        static void quickSort(int[] arr, int start, int end) {
            int left = start;
            int right = end;
            int middle = arr[(left + right) / 2];

            do {

                while (arr[left] < middle) {
                    left++;
                }

                while (arr[right] > middle) {
                    right--;
                }

                if (left <= right) {
                    if (left < right) {
                        int buf = arr[left];
                        arr[left] = arr[right];
                        arr[right] = buf;
                    }
                    left++;
                    right--;
                }
            }
            while (left <= right);

            if (left < end) {
                quickSort(arr, left, end);
            }
            if (start < right) {
                quickSort(arr, start, right);
            }
        }
    }

    static class SearchUtils {

        static int binarySearch(int[] array, int value) {
            return binarySearch(array, value, 0, array.length - 1);
        }

        static int binarySearch(int[] array, int value, int left, int right) {
            if (right < left) {
                return -1;
            }
            int middle = (left + right) / 2;
            if (array[middle] == value) {
                return middle;
            }
            else if (array[middle] < value) {
                return binarySearch(array, value, middle + 1, right);
            }
            else { // if (array[middle] > value)
                return binarySearch(array, value, left, middle - 1);
            }
        }





    }






}
