# Сравнение времени создания ряда Фибоначчи
# 1. Рекурсия
# 2. Без рекурсии

import datetime

# Рекурсия
def fibonacci(n):
    if n in (1, 2):
        return 1
    return fibonacci(n - 1) + fibonacci(n - 2)

start_time = datetime.datetime.now()
print(fibonacci(35))
end_time = datetime.datetime.now()
print(start_time - end_time)

# Линейный алгоритм
start_time = datetime.datetime.now()
fib1, fib2 = 0, 1
n = 35
for i in range(n):
    x = fib1 + fib2
    fib1 = fib2
    fib2 = x
print(fib1)
end_time = datetime.datetime.now()
print(start_time - end_time)