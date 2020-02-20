
def make_adder(n):
    """return a function that take one argument
    K and return K + N
    >>> add_three = make_adder(3)
    >>> add_three(4)
    7
    >>> make_adder(1)(2)
    3
    """
    def adder(k):
        return k + n
    return adder