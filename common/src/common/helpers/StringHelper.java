package common.helpers;

public class StringHelper {

//    This checks if a string is null or empty and returns true otherwise it returns false.
    public static boolean isNullOfEmpty(String s) {
        return s == null || s.length() == 0;
    }
}
