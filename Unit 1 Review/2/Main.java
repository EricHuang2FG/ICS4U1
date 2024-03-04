public class Main {
    public static void main(String[] args) {
        System.out.println(reverse("math"));
    }

    public static String reverse(String str) {
        if (str.length() <= 1) {
            return str;
        } else {
            return reverse(str.substring(1)) + str.substring(0, 1);
        }
    } 
}