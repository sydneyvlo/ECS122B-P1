import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
/**
 * Created by sydneylo on 4/27/16.
 */
public class mergesort_stats {
    public static int recursive_calls = 0;
    public static int transitions = 0;
    public static int compares = 0;
    public static void main(String args[]) {
        // Command line arguments.
        String fileName = args[0];

        // Create a file object.
        File f = new File(fileName);
        Vector<Integer> data = new Vector<Integer>();

        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextInt()) {
                data.add(sc.nextInt());
            }
            sc.close();
        } catch(IOException FileNotFoundException) {
            System.out.println("Could not find the specified male image file. " +
                    "Please use absolute paths. Make sure it is correct.");
        }

//        System.out.println(fileName);
//        int k = 10;
//        int[] a = {5,3,2,10,-1,8,1,3,100,-1000,0,8,15,26,19,39,27,3};
//        System.out.println("Before sort");
//        System.out.println(data.toString());
//        System.out.println("After sort");
        mergesort(data, 0, data.size() - 1);
//        System.out.println(data.toString());

//        for (int i = 0; i < data.size(); i++) {
//            System.out.println(data.get(i));
//        }

        System.out.println("Recursive Calls: " + mergesort_stats.recursive_calls);
        System.out.println("Transitions: " + mergesort_stats.transitions);
        System.out.println("Compares: " + mergesort_stats.compares);
    }

    public static void insertionsort(Vector<Integer> a, int start, int end) {
        int temp = 0;
        for (int i = start+1; i <= end; i++) {
            int j = i;
            while (j > start && (a.get(j-1) > a.get(j))) {
                mergesort_stats.compares++;
                temp = a.get(j-1);
                a.set(j-1, a.get(j));
                a.set(j, temp);
                j--;
            }
        }
    }

    public static void mergesort(Vector<Integer> a, int lo, int hi) {
        mergesort_stats.recursive_calls++;
        // base case
        if ((hi-lo + 1) < 3)  {
            insertionsort(a, lo, hi);
            return;
        }

        if (hi <= lo) {
            mergesort_stats.compares++;
            return;
        }

        // Otherwise tripartition the array.
        int size = (hi - lo) + 1;
        int low0 = lo;
        int low1, low2, hi0, hi1;
        int hi2 = hi;
        if (size % 3 == 0) {
            hi0 = (size/3) - 1 + lo;
            low1 = (size/3) + lo;
            hi1 = (size/3) + (size/3) - 1 + lo;
            low2 = (size/3) + (size/3) + lo;
        } else if (size % 3 == 1) {
            hi0 = size/3 + lo;
            low1 = (size/3) + 1 + lo;
            hi1 = (size/3) + (size/3) + lo;
            low2 = (size/3) + (size/3) + 1 + lo;
        } else {
            hi0 = (size/3) + lo;
            low1 = (size/3) + 1 + lo;
            hi1 = (size/3) + (size/3) + 1 + lo;
            low2 = (size/3) + (size/3) + 2 + lo;
        }

        mergesort(a, low0, hi0);
        mergesort(a, low1, hi1);
        mergesort(a, low2, hi2);

        Vector<Integer> b = new Vector<Integer>();
        Vector<Integer> c = new Vector<Integer>();
        Vector<Integer> d = new Vector<Integer>();

        for (int k = low0; k <= hi0; k++) {
            b.add(k-low0, a.get(k));
        }

        for (int k = low1; k <= hi1; k++) {
            c.add(k-low1, a.get(k));
        }

        for (int k = low2; k <= hi2; k++) {
            d.add(k-low2, a.get(k));
        }

        // sentinel values
        b.add(Integer.MAX_VALUE);
        c.add(Integer.MAX_VALUE);
        d.add(Integer.MAX_VALUE);

        int i = 0, j = 0 , l = 0;

        String last = "";

        for (int k = lo; k <= hi; k++) {
            // smallest value is in the b vector.
            if (b.get(i) <= c.get(j) && b.get(i) <= d.get(l)) {
                mergesort_stats.compares++;
                if (last.compareTo("b") != 0) {
                    mergesort_stats.transitions++;
                    last = "b";
                }
                mergesort_stats.compares++;
                a.set(k, b.get(i++));
            } else if (c.get(j) <= b.get(i) && c.get(j) <= d.get(l)) {
                mergesort_stats.compares++;
                if (last.compareTo("c") != 0) {
                    mergesort_stats.transitions++;
                    last = "c";
                }
                mergesort_stats.compares++;
                // smallest value is in the c vector.
                a.set(k, c.get(j++));
            } else if (d.get(l) <= b.get(i) && d.get(l) <= c.get(j)) {
                mergesort_stats.compares++;
                if (last.compareTo("d") != 0) {
                    mergesort_stats.transitions++;
                    last = "d";
                }
                // smallest value is in the d vector
                a.set(k, d.get(l++));
            }
        }
    }
}
