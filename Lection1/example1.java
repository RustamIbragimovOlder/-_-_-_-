package Lection1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;;

public class example1 {

    public static void main(String[] args) {
        //List<Integer> availableDivider = findAvailableDivider(12);
        //for (Integer integer : availableDivider) {
        //    System.out.println(integer);
        //}
        //List<Integer> availableDivider = findSimpleNumbers(10000);
        //for (Integer integer : availableDivider) {
        //    System.out.println(integer);
        //}

        AtomicInteger counter = new AtomicInteger(0);
        int fib = fib(40, counter);
        System.out.println("Fib number => " + fib);
        System.out.println("Counter => " + counter.get());
    }

    /*
     * Функция определения делителей числа
     * findAvailableDivider - поиск допустимых делителей
     * Линейная зависимость (один for)
     */

    public static List<Integer> findAvailableDivider(int number) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                result.add(i);
            }
        }
        return result;
    }

    /*
     * Функция нахождения простых чисел
     * findSimpleNumbers - поиск простых чисел
     * Квадратичная зависимость (for в for)
     */

    public static List<Integer> findSimpleNumbers(int max) {
        int counter = 0; // счетчик операций
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            boolean simple = true;
            for (int j = 2; j < i; j++) {
                counter++;
                if (i % j == 0) {
                    simple = false;
                }
            }
            if (simple) {
                result.add(i);
            }
        }
        System.out.println("Количество операций => " + counter);
        return result;
    }

    /*
     * Функция поиска шанса выпадения определенной суммы на 3-х зариках
     * Экспоненциальная зависимость (for в for в for)
     */

     public static double findSum(int sum) {
        int count = 0;
        int successResult = 0;
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (i + j + k == sum) {
                        successResult++;
                    }
                    count++;
                }
            }
        }
        return ((double) successResult) / ((double) count);
    }

    /*
     * Функция вычисления чисел Фибоначчи (рекурсия)
     * AtomicInteger counter - счетчик операций
     */

     public static int fib(int position, AtomicInteger counter) {
        counter.incrementAndGet();
        if (position == 1 || position == 2) {
            return 1;
        }
        return fib(position - 1, counter) + fib(position - 2, counter);
    }

}