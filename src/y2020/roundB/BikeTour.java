package y2020.roundB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class BikeTour {
    public static void main(String[] args) throws Exception {
//        Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
         Scanner scan = new Scanner(new BufferedReader(new FileReader("resources/bike_tour.txt")));
        int nr = scan.nextInt();
        for (int i = 0; i < nr; i++) {
            int n = scan.nextInt();
            int[] mh = new int[n];
            for (int j = 0; j < n; j++) {
                mh[j] = scan.nextInt();
            }
            System.out.printf("Case #%d: %d\n", i+1, solve(mh));
        }
    }

    private static int solve(int[] mh) {
        int len = mh.length;
        int count = 0;
        for (int i = 1; i <= len - 2; i++) {
            if (mh[i] > mh[i - 1] && mh[i] > mh[i + 1]) {
                count++;
            }
        }
        return count;
    }
}
