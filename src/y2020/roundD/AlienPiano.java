package y2020.roundD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlienPiano {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("resources/alien_piano.txt")));
//        Scanner scanner =  new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int nT = scanner.nextInt();
        for (int i = 0; i < nT; i++) {
            int size = scanner.nextInt();
            int[] notes = new int[size];
            for(int j = 0; j < size; j++) {
                notes[j] = scanner.nextInt();
            }
            System.out.printf("Case #%d: %d\n", i+1, findNBreaking(notes));
            System.out.printf("Case #%d: %d\n", i+1, solution2(notes));
        }

    }

    // Not pass
    private static int findNBreaking(int[] notes) {
        List<Integer> list = new ArrayList<>();
        list.add(notes[0]);
        for (int i = 1; i < notes.length;i++) {
            if (notes[i] != notes[i-1]) {
                list.add(notes[i]);
            }
        }
        int count = 0;
        int nTrend = 2;
        for (int i = 2; i < list.size(); i++) {
            if ((list.get(i) - list.get(i-1)) * (list.get(i-1) - list.get(i-2)) < 0) {
                nTrend = 2;
            } else {
                nTrend++;
            }
            if (nTrend > 4) {
                count++;
                nTrend = 1;
            }
        }
        return count;
    }

    private static int solution2(int[] notes) {
        List<Integer> list = new ArrayList<>();
        list.add(notes[0]);
        for (int i = 1; i < notes.length;i++) {
            if (notes[i] != notes[i-1]) {
                list.add(notes[i]);
            }
        }
        int count = 0;
        int up = 0;
        int down = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > list.get(i-1)) {
                up++;
                down = 0;
            } else {
                down++;
                up = 0;
            }
            if (up > 3 || down > 3) {
                count++;
                up = 0;
                down = 0;
            }
        }
        return count;
    }
}
