package polynomial;

/**
 * A polynomial is made of several terms, each term having a coefficient and a variable raised to a
 * power. The degree of a polynomial is defined as the highest power of the variable in a term.
 * The polynomials we deal with have only non-negative whole numbers as powers. The coefficients of
 * our polynomials are integral numbers.
 * Each polynomial supports add, multiply, derivative operations. Also, one can get the degree of
 * the polynomial, the coefficients of terms and evaluate the polynomial by providing input.
 * This interface only supports polynomials with one variable.
 */
public interface Polynomial {
  /**
   * The method takes a coefficient (integral number) and a power (whole number) and adds
   * the resulting term to this polynomial.
   *
   * @param coefficient the coefficient.
   * @param power the power.
   * @throws IllegalArgumentException if the term is not valid.
   */
  void addTerm(int coefficient, int power) throws IllegalArgumentException;

  /**
   * Returns the degree of the polynomial. The degree of a polynomial is defined as the highest
   * power of the variable in a term.
   *
   * @return the degree of the polynomial.
   */
  int getDegree();

  /**
   * Returns the coefficient of the term with the requested power.
   *
   * @param power power of the term for which the coefficient should be returned.
   * @return coefficient of the term.
   */
  int getCoefficient(int power);

  /**
   * Returns a double-precision result which is the evaluation of this polynomial using this
   * argument’s value.
   *
   * @param argument input for the polynomial.
   * @return value of the polynomial for this input.
   */
  double evaluate(double argument);

  /**
   * Returns the polynomial obtained by adding the two polynomials. Any implementation ensures
   * that this method does not mutate either polynomial.
   * The addition is performed by combining all the terms and adding the coefficients of the terms
   * with the same power.
   *
   * @param polynomial polynomial to add.
   * @return resulting polynomial.
   */
  Polynomial add(Polynomial polynomial);

  /**
   * Returns the polynomial obtained by multiplying the two polynomials. Any implementation
   * ensures that this method does not mutate either polynomial.
   * To multiply two polynomials 𝑓(𝑥) and 𝑔(𝑥) we multiply 𝑔(𝑥) with each term in 𝑓(𝑥) to
   * produce new polynomials and then add them together.
   *
   * @param polynomial polynomial to multiply.
   * @return the polynomial representing the product.
   */
  Polynomial multiply(Polynomial polynomial);

  /**
   * Returns the polynomial obtained by differentiating this polynomial.
   * The derivative of a polynomial (from differential calculus) is a polynomial made up of the
   * derivative of each term. The derivative of a term 𝑎𝑥𝑏 is a term with coefficient 𝑎𝑏 with
   * the variable raised to the power 𝑏−1. The derivative of a constant term (𝑥0) is 0.
   *
   * @return the polynomial representing the derivative.
   */
  Polynomial derivative();
}
