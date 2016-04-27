import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.Vector;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.io.FileNotFoundException;
import java.util.Vector;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

/**
 * Created by sydneylo on 4/27/16.
 */
public class quicksort_stats {
    public static int partitioning_stages = 0;
    public static int exchanges = 0;
    public static int compares = 0;

    public static void main(String[] args) {
        // Command line arguments.
        String fileName = args[0];
        int k = Integer.parseInt(args[1]);

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
//        System.out.println(k);
//        int k = 10;
//        int[] a = {5,3,2,10,-1,8,1,3,100,-1000,0,8,15,26,19,39,27,3};
//        System.out.println("Before sort");
//        System.out.println(data.toString());
//        System.out.println("After sort");
        quicksort(data, 0, data.size()-1, k);
//        System.out.println(data.toString());

//        for (int i = 0; i < data.size(); i++) {
//            System.out.println(data.get(i));
//        }

        System.out.println("Partitioning Stages: " + quicksort_stats.partitioning_stages);
        System.out.println("Exchanges: " + quicksort_stats.exchanges);
        System.out.println("Compares: " + quicksort_stats.compares);

    }

    public static void insertionsort(Vector<Integer> a, int start, int end) {
        int temp = 0;
        for (int i = start+1; i <= end; i++) {
            quicksort_stats.compares++;
            int j = i;
            while (j > start && (a.get(j-1) > a.get(j))) {
                quicksort_stats.compares++;
                quicksort_stats.exchanges++;
                temp = a.get(j-1);
                a.set(j-1, a.get(j));
                a.set(j, temp);
                j--;
            }
        }
    }

    public static void quicksort(Vector<Integer> a, int lo, int hi, int k) {

        if (((hi - lo) + 1) <= k) {
            quicksort_stats.compares++;
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
        int t, v = a.get(pivotIndex);

        // Swap pivot with last item
        a.set(pivotIndex, a.get(hi));
        quicksort_stats.exchanges++;
        a.set(hi, v);

        // This is the partitioning step.
        while (true) {
            quicksort_stats.partitioning_stages++;
            // Compare the elements from the left to the last element.
            // Keep incrementing as long as these elements are less than the last element.
            while (a.get(++i) < v) {quicksort_stats.compares++;};

            // Compare the elements starting from the right hand side.
            // Keep decrementing as long as the elements on the right hand side are greater than
            // the last element.
            while (v < a.get(--j)) {
                quicksort_stats.compares++;
                if (j == lo) {
                    break;
                }
            }

            // Checks to see that the i and j indices crossed.
            if (i >= j) {
                quicksort_stats.compares++;
                break;
            }

            quicksort_stats.exchanges++;
            t = a.get(i);
            a.set(i, a.get(j));
            a.set(j, t);
        }

        quicksort_stats.exchanges++;
        t = a.get(i);
        a.set(i, a.get(hi));
        a.set(hi, t);

        quicksort(a, lo, i-1, k);
        quicksort(a, i+1, hi, k);
    }

}
