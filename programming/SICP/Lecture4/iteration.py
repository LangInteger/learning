
def accumulator(x):
    """
    >>> a = accumulator(3)
    >>> a
    6
    """
    i, total = 0, 0
    while i < x:
        i = i + 1
        total = total + i
    return total
