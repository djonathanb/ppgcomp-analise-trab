package algs4;

public class SortUtils {


    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void verify(Comparable[] a) {
        Comparable prev = a[0];
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], prev)) {
                throw new RuntimeException("wrong order");
            }
        }
    }

}
