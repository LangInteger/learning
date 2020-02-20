
def summation(n, term):
    """Sum the first N terms of a sequence."""
    total, k = 0, 1
    while k <= n:
        total, k = total + term(k), k + 1
    return total

def sum_naturals(n):
    """
    >>> sum_naturals(5)
    15
    """
    return summation(n, lambda x: x)

def sum_cubes(n):
    """
    >>> sum_cubes(5)
    225
    """
    return summation(n, lambda x: pow(x, 3))