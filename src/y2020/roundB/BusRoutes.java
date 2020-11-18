package y2020.roundB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class BusRoutes {
    public static void main(String[] args) throws Exception {
//        Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
         Scanner scan = new Scanner(new BufferedReader(new FileReader("resources/bus_routes.txt")));
        int nr = scan.nextInt();
        for (int i = 0; i < nr; i++) {
            int n = scan.nextInt();
            long d = scan.nextLong();
            long[] rd = new long[n];
            for (int j = 0; j < n; j++) {
                rd[j] = scan.nextLong();
            }
            System.out.printf("Case #%d: %d\n", i+1, solve(rd, d));
        }
    }

    private static long solve(long[] rd, long d) {
        long min = d;
        for (int j = rd.length - 1; j >= 0; j--) {
            long temp = min / rd[j];
            min = temp * rd[j];
        }
        return min;
    }
}
