package polynomial;

/**
 * Simple container class for representing the terms in a polynomial. Each term consists of the
 * variable, power and coefficient.
 */
class PolynomialTerm {
  private char variable;
  private int power;
  private int coefficient;

  /**
   * Instantiates a new polynomial term.
   *
   * @param coefficient coefficient in the term.
   * @param variable variable in the term.
   * @param power power of the term.
   * @throws IllegalArgumentException if the power is negative.
   */
  PolynomialTerm(int coefficient, char variable, int power) {
    if (power < 0) {
      throw new IllegalArgumentException("Polynomial term cannot have power less than 0");
    }
    this.variable = variable;
    this.power = power;
    this.coefficient = coefficient;
  }

  /**
   * Instantiates an empty polynomial term with 0 coefficient and 0 power.
   */
  PolynomialTerm() {
    this.variable = 'x'; // frankly, this doesn't even matter.
    this.coefficient = 0;
    this.power = 0;
  }

  /**
   * Returns the power of the polynomial term.
   *
   * @return power of polynomial term
   */
  int getPower() {
    return this.power;
  }

  /**
   * Returns the variable of the polynomial term.
   *
   * @return variable of polynomial term
   */
  char getVariable() {
    return this.variable;
  }

  /**
   * Returns the coefficient of the polynomial term.
   *
   * @return coefficient of polynomial term
   */
  int getCoefficient() {
    return this.coefficient;
  }

  /**
   * Sets the power of the polynomial term.
   *
   * @param coefficient the new coefficient value.
   */
  void setCoefficient(int coefficient) {
    this.coefficient = coefficient;
  }

  /**
   * Evaluate the term by substituting the input value.
   *
   * @param input the input value.
   * @return result of the term.
   */
  double evaluate(double input) {
    return Math.pow(input, power) * coefficient;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (obj instanceof PolynomialTerm) {
      PolynomialTerm otherTerm = (PolynomialTerm) obj;
      return otherTerm.coefficient == this.coefficient && otherTerm.power == this.power
              && otherTerm.variable == this.variable;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    if (power == 0) {
      return String.format("%+d", coefficient);
    } else {
      return String.format("%+d%c^%d", coefficient, variable, power);
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
