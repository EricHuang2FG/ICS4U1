public class Fraction {

    public int numerator;
    public int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public String toString() {
        if (numerator % denominator == 0) {
            return "" + (numerator / denominator);
        }
        return numerator + "/" + denominator;
    }

    public void reduce() {
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
    }

    public void subtract(Fraction other) {
        numerator = numerator * other.denominator - denominator * other.numerator;
        denominator = denominator * other.denominator;
    }

    public void reciprocal() {
        int temp = numerator;
        numerator = denominator;
        denominator = temp;
    }

    public double value() {
        return ((1.0 * numerator) / (1.0 * denominator));
    }

    public String toMixed() {
        int front = numerator / denominator;
        int num = numerator % denominator;
        return front + " + "+ num + "/"+denominator;
    }
}
