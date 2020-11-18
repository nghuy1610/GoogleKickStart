package y2020.roundD;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LockedDoors {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("resources/locked_doors.txt")));
//        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int nRound = scanner.nextInt();
        for (int i = 0; i < nRound; i++) {
            int n = scanner.nextInt();
            int q = scanner.nextInt();
            int[] difficulties = new int[n-1];
            for (int j = 0; j < n-1; j++) {
                difficulties[j] = scanner.nextInt();
            }
            int[][] queries = new int[q][2];
            for (int j = 0; j < q; j++) {
                queries[j][0] = scanner.nextInt();
                queries[j][1] = scanner.nextInt();
            }


            System.out.printf("Case #%d: %s%n", i+1, findRoom(difficulties, queries));
        }
    }

    private static String findRoom(int[] difficulties, int[][] queries) {
        StringBuilder sb = new StringBuilder();
        for (int[] query : queries) {
            int lRoom = query[0];
            int rRoom = query[0];
            int order = query[1];
            int lastRoom = query[0];
            while (order > 1) {
                if (lRoom > 1 && rRoom <= difficulties.length) {
                    if (difficulties[lRoom-2] < difficulties[rRoom-1]) {
                        lRoom--;
                        lastRoom = lRoom;
                    } else {
                        rRoom++;
                        lastRoom = rRoom;
                    }
                } else if (lRoom == 1){
                    rRoom++;
                    lastRoom = rRoom;
                } else {
                    lRoom--;
                    lastRoom = lRoom;
                }
                order--;
            }
            sb.append(" ").append(lastRoom);
        }
        return sb.toString().trim();
    }
}
