# Abstract Data Type for Polynomial representation
Code for demonstrating how to design a custom ADT (a singly linked list in this case) to implement operations on polynomials with one variable.

Supported polynomial operations:
1. addTerm(coefficient, degree) - Add a term to an existing polynomial. A term is of the form ax^b, where a is the coefficient, and b is the degree of the term.
2. getDegree() - Returns the degree of the polynomial, which is the highest exponent for the variable in the polynomial.
3. getCoefficient(power) - Returns the coefficient for the term with the given power.
4. evaluate(value) - Returns the value of the polynomial by substituting the variable with the given value.
5. add(polynomial) - Adds the polynomial represented by the current instance with another polynomial, provided as an argument.
6. multiply(polynomial) - Multiplies the polynomial represented by the current instance with another polynomial, provided as an argument.
7. derivative() - Returns the polynomial representing the derivative of the current polynomial.
