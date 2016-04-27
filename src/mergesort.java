import java.util.Arrays;
import java.util.Vector;
/**
 * Created by sydneylo on 4/27/16.
 */
public class mergesort {
    public static void main(String args[]) {
        int k = 4; // temp value
        int[] a = {5,3,2,4,-1, 100, 45, 45, 1000, -4532, 89, -56,-4532,-4532,-1,-1, 100000, -984683, 100, 100, 100};
        //5,3,2,4,-1, 100, 45
        System.out.println("Before sort");
        System.out.println(Arrays.toString(a));
        System.out.println("After sort");
        mergesort(a, 0, 20);
        System.out.println(Arrays.toString(a));
    }

    public static void insertionsort(int[] a, int start, int end) {
        int temp = 0;
        for (int i = start+1; i <= end; i++) {
            int j = i;
            while (j > start && (a[j-1] > a[j])) {
                temp = a[j-1];
                a[j-1] = a[j];
                a[j] = temp;
                j--;
            }
        }
    }

    public static void mergesort(int[] a, int lo, int hi) {
        // base case
        if ((hi-lo + 1) < 3)  {
            insertionsort(a, lo, hi);
            return;
        }

        if (hi <= lo) {
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
            b.add(k-low0, a[k]);
        }

        for (int k = low1; k <= hi1; k++) {
            c.add(k-low1, a[k]);
        }

        for (int k = low2; k <= hi2; k++) {
            d.add(k-low2, a[k]);
        }

        // sentinel values
        b.add(Integer.MAX_VALUE);
        c.add(Integer.MAX_VALUE);
        d.add(Integer.MAX_VALUE);

        int i = 0, j = 0 , l = 0;

        for (int k = lo; k <= hi; k++) {
            // smallest value is in the b vector.
            if (b.get(i) <= c.get(j) && b.get(i) <= d.get(l)) {
                a[k] = b.get(i++);
            } else if (c.get(j) <= b.get(i) && c.get(j) <= d.get(l)) {
                // smallest value is in the c vector.
                a[k] = c.get(j++);
            } else if (d.get(l) <= b.get(i) && d.get(l) <= c.get(j)) {
                // smallest value is in the d vector
                a[k] = d.get(l++);
            }
        }
    }
}
