def split(n):
    return n // 10, n % 10

def sum_digits(n):
    """
    >> sum_digits(10)
    1
    >> sum_digits(123)
    6
    """
    if (n < 10):
        return n
    else:
        all_but_last, last = split(n)
        return sum_digits(all_but_last) + last
