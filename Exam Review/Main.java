public class Main {

    public static void main(String[] args) {
        
    } 

    public static int countChar(String str, char ch) {
        if (str.length() == 0) {
            return 0;
        } else if (str.length() == 1) {
            if (str.charAt(0) == ch) {
                return 1;
            } else {
                return 0;
            }
        } else if (str.charAt(0) == ch) {
            return 1 + countChar(str.substring(1), ch);
        } else {
            return countChar(str.substring(1), ch);
        }
    }

}