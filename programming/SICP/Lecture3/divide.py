"""
python -i divide.py
python -m doctest divide.py
python -m doctest -v divide.py
"""

from operator import floordiv, mod

def divide_exact(n, d):
    """Return the quotient and remaninder of dividing N by D.
    
    >>> q, r = divide_exact(2013, 10)
    >>> q
    201
    >>> r
    3
    """
    return floordiv(n, d), mod(n, d)