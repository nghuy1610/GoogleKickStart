package y2020.roundA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Allocation {
    public static void main(String[] args) throws Exception {
//        Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        Scanner scan = new Scanner(new BufferedReader(new FileReader("resources/allocation.txt")));
        int round = scan.nextInt();
        for (int i = 0; i < round; i++) {
            int n = scan.nextInt();
            int b = scan.nextInt();
            int[] prices = new int[n];
            for (int j = 0; j < n; j++) {
                prices[j] = scan.nextInt();
            }
            System.out.printf("Case #%d: %d\n", i+1, findNHouses(prices, b));
        }
    }

    private static int findNHouses(int[] prices, int b) {
        Arrays.sort(prices);
        int count = 0;
        for (int price : prices) {
            if (b >= price) {
                b -= price;
                count++;
            } else {
                break;
            }
        }
        return count;
    }

}
