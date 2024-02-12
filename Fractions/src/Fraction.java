public class Fraction {

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        if (denominator == 0) {
            System.out.println("Denominator can't be 0. It is set to 1 instead.");
            this.denominator = 1;
        } else {
            this.denominator = denominator;
        }
        reduce();
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setNumerator(int newNum) {
        numerator = newNum;
        reduce();
    }

    public void setDenominator(int newDen) {
        if (newDen != 0) {
            denominator = newDen;
        } else {
            System.out.println("Denominator can't be 0. It is set to 1 instead.");
            denominator = 1;
        }
        reduce();
    }

    public String toString() {
        if (numerator % denominator == 0) {
            return "" + (numerator / denominator);
        }
        return numerator + "/" + denominator;
    }

    private void reduce() {
        int small = Math.min(denominator, numerator);
        int start = 2;
        while (start <= small) {
            if (numerator % start == 0 && denominator % start == 0) {
                numerator /= start;
                denominator /= start;
                start = 2;
            } else {
                start++;
            }
        }
    }

    public void add(Fraction other) {
        numerator = numerator * other.denominator + denominator * other.numerator;
        denominator = denominator * other.denominator;
        reduce();
    }

    public void subtract(Fraction other) {
        numerator = numerator * other.denominator - denominator * other.numerator;
        denominator = denominator * other.denominator;
        reduce();
    }

    public void multiply(Fraction other) {
        numerator = numerator * other.numerator;
        denominator = denominator * other.denominator;
        reduce();
    }

    public void divide(Fraction other) {
        numerator = numerator * other.denominator;
        denominator = denominator * other.numerator;
        reduce();
    }

    public void reciprocal() {
        int temp = numerator;
        numerator = denominator;
        denominator = temp;
        reduce();
    }

    public double value() {
        return ((1.0 * numerator) / (1.0 * denominator));
    }

    public String toMixed() {
        int front = numerator / denominator;
        if (numerator % denominator != 0) {
            return front + " + " + (numerator % denominator) + "/" + denominator;
        }
        return "" + front;
    }

    public boolean equal(Fraction other) {
        return (value() == other.value());
    }

    public boolean greaterThan(Fraction other) {
        return (value() > other.value());
    }

    public boolean lessThan(Fraction other) {
        return (value() < other.value());
    }
}
