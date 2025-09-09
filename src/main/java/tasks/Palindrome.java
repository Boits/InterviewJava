package tasks;

public class Palindrome {

    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return false;
        }

        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}
