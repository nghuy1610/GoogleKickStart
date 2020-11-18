package y2020.roundD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RecordBreaker {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("resources/record_breaker.txt")));
//        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int nT = scanner.nextInt();
        for (int j = 0; j < nT; j++) {
            int day = scanner.nextInt();
            int[] records = new int[day];
            for (int i = 0; i < day; i++) {
                records[i] = scanner.nextInt();
            }
            System.out.printf("Case #%d: %d\n", j+1, findBreakingRecords(records));
        }
    }

    private static int findBreakingRecords(int[] records) {
        int count = 0;
        if (records.length == 1) {
            return 1;
        }
        if (records[0] > records[1]) {
            count += 1;
        }
        int max = records[0];
        for (int i = 1; i < records.length - 1; i++) {
            if (records[i] > max) {
                max = records[i];
                if (records[i] > records[i+1]) {
                    count++;
                }
            }
        }
        if (records[records.length - 1] > max) {
            count += 1;
        }
        return count;
    }

}
