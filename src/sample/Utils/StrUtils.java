package sample.Utils;

public class StrUtils {
    public final static String titleCase(String str) {
        String[] strs = str.toLowerCase().split("");
        strs[0] = strs[0].toUpperCase();
        return String.join("", strs);
    }
}
