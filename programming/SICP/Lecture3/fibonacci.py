
def fibonacci(x):
    """
    >>> a = fibonacci(3)
    >>> a
    1
    >>> b = fibonacci(10)
    >>> b
    34
    """
    if x == 1 or x == 2:
        return x - 1
    else:
        return fibonacci(x - 1) + fibonacci(x - 2)