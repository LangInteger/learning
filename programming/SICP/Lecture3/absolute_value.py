from operator import floordiv, mod

def absolute_value(x):
    """Return the absolute value of x
    >>> a = absolute_value(-1)
    >>> a
    1
    >>> b = absolute_value(1)
    >>> b
    1
    >>> c = absolute_value(0)
    >>> c
    0
    """
    if x < 0:
        return -x
    elif x == 0:
        return 0
    else:
        return x