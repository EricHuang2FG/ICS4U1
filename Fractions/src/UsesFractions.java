public class UsesFractions {
    public static void main(String[] args) {
        Fraction f = new Fraction(11, 3);
        f.add(new Fraction(1, 3));
        f.subtract(new Fraction(1, 5));
        f.reduce();
        f.reciprocal();
        Fraction g = new Fraction(7, 3);
        System.out.println(g.toMixed());
    }
}
