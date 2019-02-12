import java.util.LinkedList;

public class JSON {
    public static Double max;
    public static Double min;
    public static Double sum;
    public static Double mult;
    public static LinkedList<Double> list;


    public static String getJSON() {
        String temp =
                "{" +
                        "max : " + max +
                        ", min : " + min +
                        ", sum : " + sum +
                        ", mult : " + mult +
                        ", list : " + list +
                        "}";
        return temp;
    }
}