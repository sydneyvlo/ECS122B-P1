import java.util.Arrays;
import java.util.Vector;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        int k = 4; // temp value
        int[] a = {5,3,2,10,-1,8,1,3,100,-1000,0,8,15,26,19,39,27,3};
        System.out.println("Before sort");
        System.out.println(Arrays.toString(a));
        System.out.println("After sort");
        quicksort(a, 0, 17, k);
        System.out.println(Arrays.toString(a));

    }

    public static void insertionsort(int[] a, int start, int end) {
        int temp = 0;
        for (int i = start+1; i <= end; i++) {
            int j = i;
            while (j > 0 && (a[j-1] > a[j])) {
                temp = a[j-1];
                a[j-1] = a[j];
                a[j] = temp;
                j--;
            }
        }
    }

    public static void quicksort(int[] a, int lo, int hi, int k) {

        if (((hi - lo) + 1) <= k) {
            insertionsort(a, lo, hi);
            return;
        }

//        if (hi <= lo) {
//            return;
//        }

        // i holds the index before the first element in the array.
        // j holds the index of the last element in the array.
        int i = lo-1, j = hi;

        // t and v = the value of the last element in the array.
        // t is used as a variable to store intermediate values while performing a swap.
        // TODO: Don't just choose the last element as the pivot. Randomly choose one instead.
        int pivotIndex = ThreadLocalRandom.current().nextInt(lo, (hi+1));
        int t, v = a[pivotIndex];

        // Swap pivot with last time
        a[pivotIndex] = a[hi];
        a[hi] = v;

        // This is the partitioning step.
        while (true) {
            // Compare the elements from the left to the last element.
            // Keep incrementing as long as these elements are less than the last element.
            while (a[++i] < v);

            // Compare the elements starting from the right hand side.
            // Keep decrementing as long as the elements on the right hand side are greater than
            // the last element.
            while (v < a[--j]) {
                if (j == lo) {
                    break;
                }
            }

            // Checks to see that the i and j indices crossed.
            if (i >= j) {
                break;
            }

            t = a[i];
            a[i] = a[j];
            a[j] = t;
        }

        t = a[i];
        a[i] = a[hi];
        a[hi] = t;

        quicksort(a, lo, i-1, k);
        quicksort(a, i+1, hi, k);
    }
}
