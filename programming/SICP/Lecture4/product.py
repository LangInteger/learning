def square(x):
    return x * x

def identity(x):
    return x

triple = lambda x: 3 * x

increment = lambda x: x + 1

add = lambda x, y: x + y

mul = lambda x, y: x * y

def product(n, term):
    """Return the product of the first n terms in a sequence.
    n    -- a positive integer
    term -- a function that takes one argument

    >>> product(3, identity)  # 1 * 2 * 3
    6
    >>> product(5, identity)  # 1 * 2 * 3 * 4 * 5
    120
    >>> product(3, square)    # 1^2 * 2^2 * 3^2
    36
    >>> product(5, square)    # 1^2 * 2^2 * 3^2 * 4^2 * 5^2
    14400
    >>> product(3, increment) # (1+1) * (2+1) * (3+1)
    24
    >>> product(3, triple)    # 1*3 * 2*3 * 3*3
    162
    """
    "*** YOUR CODE HERE ***"
    assert n > 0, 'n must greater than zero'
    total, k = 1, 1
    while k <= n:
        total, k = total * term(k), k + 1
    return total

def factorial(n):
    """Return n factorial for n >= 0 by calling product.
    >>> factorial(4)
    24
    >>> factorial(6)
    720
    """
    "*** YOUR CODE HERE ***"
    assert n >= 0, 'n must no lower than zero'
    return product(n, identity) 