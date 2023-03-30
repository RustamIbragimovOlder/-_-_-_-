package lection2;

public class find {

    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(findIndex(5, array));
        System.out.println(binarySearch(array, 7, 0, array.length - 1));
    }



    // Функция поиска простым перебором
    // сложность O(n)
    public static int findIndex(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // Функция бинарного поиска
    // сложность O(log n)
    public static int binarySearch(int[] array, int value, int min, int max) {
        int midpoint;
        if (max < min) {
            return -1;
        }
        else {
            midpoint = (max - min) / 2 + min;
        }

        if (array[midpoint] < value) {
            return binarySearch(array, value, midpoint + 1, max);
        }
        else {
            if (array[midpoint] > value) {
                return binarySearch(array, value, min, midpoint - 1);
            }
            else {
                return midpoint;
            }
        }
    }
    
}
