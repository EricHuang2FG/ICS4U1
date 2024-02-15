public class UsesFractions {
    public static void main(String[] args) {
        Fraction f = new Fraction(11, 3);
        f.add(new Fraction(1, 3));
        f.subtract(new Fraction(1, 5));
        f.reciprocal();
        Fraction g = new Fraction(7, 3);
        Fraction a = new Fraction(5, 2);
        System.out.println(g.toMixed());
        System.out.println(f.toString());
        System.out.println(a.toMixed());
        a.divide(new Fraction(10, 2));
        System.out.println(a.toString());
        System.out.println(a.equal(new Fraction(1, 2)));
        System.out.println(a.greaterThan(new Fraction(1, 3)));
        System.out.println(a.greaterThan(new Fraction(3, 4)));
        a.setNumerator(3);
        a.setDenominator(4);
        System.out.println(a.toString());
        Fraction c = new Fraction(1, 5);
        Fraction d = new Fraction(5, 7);
        System.out.println(Fraction.divide(c, d).toString());
    }
}
