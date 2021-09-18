def fact(n):
    factorial = 1
    if n < 0:
        return None

    for i in range(1, n+1):
        factorial *= i

    return factorial


total = 0
for n in range(1, 51):
    total += fact(n)

print(total)
