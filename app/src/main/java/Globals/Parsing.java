package Globals;

public class Parsing {
    public static int toInt(Object value) {
        if (value != null) {
            return Integer.parseInt(String.valueOf(value));
        }
        return -1;
    }

    public static double toDouble(Object value) {
        if (value != null) {
            return Double.parseDouble(String.valueOf(value));
        }
        return -1;
    }

    public static float toFloat(Object value) {
        if (value != null) {
            return Float.parseFloat(String.valueOf(value));
        }
        return -1;
    }

    public static boolean toBool(Object value) {
        if (value != null) {
            return Boolean.parseBoolean((String) value);
        }
        return false;
    }
}
