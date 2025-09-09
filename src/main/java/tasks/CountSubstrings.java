package tasks;

public class CountSubstrings {
    
    /*
    Даны s1, s2. Посчитать количество вхождений строки s2 в строку s1.
     */

    public int countSubstrings(String sFull, String sShort) {

        if (sFull.isEmpty() || sShort.isEmpty()) {
            return 0;
        }

        int res = 0;
        int lengthFull = sFull.length();
        int lengthShort = sShort.length();
        int i = 0;

        while (i <= lengthFull && i + lengthShort <= lengthFull) {

            if ((sFull.substring(i, i + lengthShort))
                    .equalsIgnoreCase(sShort)) {
                res++;
                i++;
            } else {
                i++;
            }
        }

        return res;
    }
}
