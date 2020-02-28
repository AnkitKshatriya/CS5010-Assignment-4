package polynomial;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of Polynomial interface. This class uses a singly linked list to store the
 * polynomial terms.
 */
public class PolynomialImpl implements Polynomial {
  private final String errorMsg = "Invalid polynomial or term. Got \"%s\" as input.";
  private List<PolynomialTerm> list;
  private Character variable;

  private BiFunction<PolynomialTerm, PolynomialTerm, PolynomialTerm> updateFunction = (t1, t2) -> {
    t1.setCoefficient(t1.getCoefficient() + t2.getCoefficient());
    return t1;
  };

  private Comparator<PolynomialTerm> comparator = Comparator.comparingInt(PolynomialTerm::getPower);

  private PolynomialImpl(List<PolynomialTerm> list) {
    this.list = list;
    variable = 'x';
  }

  /**
   * Instantiate a new polynomial impl object.
   */
  public PolynomialImpl() {
    list = new SinglyLinkedListImpl<>();
    variable = 'x';
  }

  /**
   * Instantiate a new polynomial impl object.
   */
  public PolynomialImpl(String str) {
    this();

    if (str == null || str.length() == 0) {
      throw new IllegalArgumentException(String.format(errorMsg, str));
    }

    if (str.charAt(0) != '+' && str.charAt(0) != '-') {
      str = '+' + str; // polynomial terms are positive by default.
    }

    String[] terms = str.split(" ");
    for (String term : terms) {
      PolynomialTerm polynomialTerm = validateAndParsePolynomialTerm(term);
      if (this.variable == null) {
        this.variable = polynomialTerm.getVariable();
      } else {
        // make sure the variable provided in the new term is the same as the earlier terms.
        if (this.variable != polynomialTerm.getVariable()) {
          throw new IllegalArgumentException(String.format(errorMsg, str));
        }
      }

      addTerm(polynomialTerm);
    }
  }

  /**
   * Helper method to add polynomial terms to the underlying list implementation.
   *
   * @param term polynomial term to be added.
   */
  private void addTerm(PolynomialTerm term) {
    if (term.getCoefficient() == 0) {
      return;
    }

    list.addOrUpdate(term, comparator, updateFunction);

    // filter terms with coefficient of 0.
    list = list.filter(new Predicate<PolynomialTerm>() {
      @Override
      public boolean test(PolynomialTerm term) {
        return term.getCoefficient() != 0;
      }
    });
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    PolynomialTerm term = new PolynomialTerm(coefficient, variable, power);
    addTerm(term);
  }

  @Override
  public int getDegree() {
    int degree = list.reduce(-1,
            (polynomialTerm, initialDegree) -> Math.max(polynomialTerm.getPower(), initialDegree));
    if (degree == -1) {
      throw new IllegalStateException("Degree of empty polynomial is undefined");
    }

    return degree;
  }

  @Override
  public int getCoefficient(int power) {
    List<PolynomialTerm> polynomialTermList = this.list.filter(term -> term.getPower() == power);

    if (polynomialTermList.size() == 1) {
      return polynomialTermList.get(0).getCoefficient();
    } else {
      return 0;
    }
  }

  @Override
  public double evaluate(double argument) {
    return this.list.map(
            polynomialTerm -> polynomialTerm.evaluate(argument)).reduce(0d, Double::sum);
  }

  @Override
  public Polynomial add(Polynomial polynomial) {
    PolynomialImpl otherPolynomial = (PolynomialImpl) polynomial;

    PolynomialImpl result = new PolynomialImpl();
    this.list.reduce(result, (term, sum) -> {
      sum.addTerm(term.getCoefficient(), term.getPower());
      return sum;
    });

    otherPolynomial.list.reduce(result, (term, sum) -> {
      sum.addTerm(term.getCoefficient(), term.getPower());
      return sum;
    });

    // remove terms with coefficient of 0.
    result.list = result.list.filter(term -> term.getCoefficient() != 0);
    return result;
  }

  @Override
  public Polynomial multiply(Polynomial polynomial) {
    PolynomialImpl otherPolynomial = (PolynomialImpl) polynomial;
    Polynomial result = new PolynomialImpl();

    this.list.reduce(result, (t1, outerProduct) -> {
      otherPolynomial.list.reduce(outerProduct, (t2, innerProduct) -> {
        int c = t1.getCoefficient() * t2.getCoefficient();
        int p = t1.getPower() + t2.getPower();
        innerProduct.addTerm(c, p);
        return innerProduct;
      });
      return outerProduct;
    });

    return result;
  }

  @Override
  public Polynomial derivative() {
    List<PolynomialTerm> derivativeList = this.list.map(term -> {
      if (term.getPower() == 0) {
        return new PolynomialTerm();
      } else {
        int power = term.getPower() - 1;
        int coefficient = term.getCoefficient() * term.getPower();
        return new PolynomialTerm(coefficient, term.getVariable(), power);
      }
    });

    derivativeList = derivativeList.filter(term -> term.getCoefficient() != 0);

    return new PolynomialImpl(derivativeList);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof PolynomialImpl) {
      PolynomialImpl otherPolynomial = (PolynomialImpl) obj;
      return this.list.equals(otherPolynomial.list);
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    List<String> list = this.list.map(PolynomialTerm::toString);

    StringBuilder b = list.reduce(new StringBuilder(), (s, builder) -> builder.append(s));
    if (b.length() != 0) {
      if (b.charAt(0) == '+') {
        return b.substring(1);
      } else {
        return b.toString();
      }
    } else {
      return "0";
    }
  }

  /**
   * Helper method to parse a single polynomial term string and return a PolynomialTerm object.
   *
   * @param polynomialTerm string representing the term.
   * @return the PolynomialTerm representing the term.
   */
  private PolynomialTerm validateAndParsePolynomialTerm(String polynomialTerm) {
    // remove any spaces within or around the term.
    boolean valid = true;
    int coefficient = 0;
    int power = 0;
    char variable = 'x';
    try {
      polynomialTerm = polynomialTerm.replaceAll(" ", "");

      Pattern pattern = Pattern.compile("^([-+]\\d+)([a-zA-Z]\\^-?\\d+)?$");
      Matcher matcher = pattern.matcher(polynomialTerm);

      if (matcher.matches()) {
        if (matcher.group(1) != null) {
          coefficient = Integer.parseInt(matcher.group(1));
        } else {
          valid = false;
        }

        String str = matcher.group(2);
        if (matcher.group(2) != null) {
          String[] tokens = str.split("\\^");
          variable = tokens[0].charAt(0);
          power = Integer.parseInt(tokens[1]);
        }
      } else {
        valid = false;
      }
    } catch (NumberFormatException e) {
      valid = false;
    }

    if (!valid) {
      throw new IllegalArgumentException(String.format(errorMsg, polynomialTerm));
    }
    return new PolynomialTerm(coefficient, variable, power);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
