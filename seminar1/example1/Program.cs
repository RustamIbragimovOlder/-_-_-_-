// Напишите программу, которая возвращает максимальное значение из 2-х чисел

int maxNumbers(int a, int b)
{
    if (a > b)
        return a;
    return b;
}

Console.WriteLine($"Результат {maxNumbers(5, 10)}");

// Задача Гипотеза Гольдбаха
// Console.Clear();
Console.Write("Введите число: ");
int n = int.Parse(Console.ReadLine()!);
int countDel = 0, i, j, k, m;
for (i = 3; i <= Math.Sqrt(n) + 1; i+=2)
{
    countDel = 0;
    for (j = 2; j <= Math.Sqrt(i) + 1; j++)
    {
        if (i % j == 0)
            countDel++;
    }
    if (countDel == 0)
    {
        m = n - i;
        for (k = 2; k <= Math.Sqrt(m) + 1; k++)
        {
            if (m % k == 0)
                countDel++;
        }
        if (countDel == 0)
        {
            Console.WriteLine($"{i} {m}");
            // return;
        }
    }
}