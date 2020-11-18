package y2020.roundA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Plates {
    public static void main(String[] args) throws Exception {
//        Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        Scanner scan = new Scanner(new BufferedReader(new FileReader("resources/plates.txt")));
        int round = scan.nextInt();
        for (int i = 0; i < round; i++) {
            int n = scan.nextInt();
            int k = scan.nextInt();
            int p = scan.nextInt();
            int[][] beauty = new int[n][k];
            for (int j = 0; j < n; j++) {
                for (int m = 0; m < k; m++) {
                    beauty[j][m] = scan.nextInt();
                }
            }
            System.out.printf("Case #%d: %d\n", i+1, solution4(p, beauty));
        }
    }

    // Only work for small test
    private static int solution3(int q, int[][] beauty) {
        for (int i = 0; i < beauty.length; i++) {
            for (int j = 1; j < beauty[0].length; j++) {
                beauty[i][j] += beauty[i][j-1];
            }
        }
        int[] combine = new int[beauty.length];
        List<int[]> allCombine = findCombine(q, beauty.length, beauty[0].length, combine);
        int max = 0;
        for (int[] aComb : allCombine) {
            int tmp = 0;
            for (int j = 0; j < aComb.length; j++) {
                if (aComb[j] == 0) {
                    tmp += 0;
                } else {
                    tmp += beauty[j][aComb[j] - 1];
                }
            }
            if (tmp > max) {
                max = tmp;
            }
        }
        return max;
    }

    private static List<int[]> findCombine(int remainingPlates, int remainingStack, int maxStackSize, int[] currentCombination) {
        if (remainingStack == 1) {
            if (remainingPlates <= maxStackSize && remainingPlates >= 0) {
                currentCombination[remainingStack - 1] = remainingPlates;
                return Collections.singletonList(currentCombination.clone());
            } else {
                return new ArrayList<>();
            }
        } else {
            List<int[]> rs = new ArrayList<>();
            for (int i = 0; i <= maxStackSize; i++) {
                currentCombination[remainingStack-1] = i;
                rs.addAll(findCombine(remainingPlates-i, remainingStack - 1, maxStackSize, currentCombination));
            }
            return rs;
        }
    }

    private static int solution4(int q, int[][] beauty) {
        int[][] prefixSum = new int[beauty.length][beauty[0].length + 1];
        int nRow = prefixSum.length;
        int nCol = prefixSum[0].length;
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (j == 0) {
                    prefixSum[i][j] = 0;
                } else {
                    prefixSum[i][j] = prefixSum[i][j-1] + beauty[i][j-1];
                }
            }
        }

        // Table with row index + 1 = remaining stack (count from last) and col index = remaining plates not chosen
        int[][] table = new int[nRow][q+1];
        for (int i = 0; i < nRow; i++) {
            table[i][0] = 0;
        }
        for (int i = 0; i <= q; i++) {
            if (i >= nCol) {
                table[0][i] = prefixSum[0][nCol-1];
            } else {
                table[0][i] = prefixSum[0][i];
            }
        }
        for (int j = 1; j <= q; j++) {
            for (int i = 1; i < nRow; i++) {
                int max = table[i-1][j];
                for (int k = 1; k <= j && k < nCol; k++) {
                    max = Math.max(max, prefixSum[i][k] + table[i-1][j-k]);
                }
                table[i][j] = max;
            }
        }
        return table[nRow-1][q];
    }

}
