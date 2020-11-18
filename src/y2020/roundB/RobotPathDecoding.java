package y2020.roundB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class RobotPathDecoding {
    public static void main(String[] args) throws Exception {
//        Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
         Scanner scan = new Scanner(new BufferedReader(new FileReader("resources/robot_path_decoding.txt")));
        int nr = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < nr; i++) {
            String cmd = scan.nextLine();
            int size = 1000000000;
            pos= 0;
            long[] result = solveV4(cmd);
            long posX = result[0];
            while(posX < 0) {posX += size;}
            posX = posX % size;
            long posY = result[1];
            while(posY < 0) {
                posY += size;
            }
            posY = posY % size;
            System.out.printf("Case #%d: %d %d\n", i + 1, posX + 1, posY + 1);
        }
    }

    static int pos = 0;
    private static long[] solveV4(String cmd) {
        long rx = 0, ry = 0;
        while (pos < cmd.length()) {
            char dir = cmd.charAt(pos);
            pos++;
            if (dir == '(') {
                continue;
            } else if (dir <= '9' && dir >= '2') {
                int repeat = dir - '0';
                long[] subRes = solveV4(cmd);
                rx += repeat * subRes[0];
                ry += repeat * subRes[1];
            } else if (dir == 'N') {
                ry--;
            } else if (dir == 'S') {
                ry++;
            } else if (dir == 'W') {
                rx--;
            } else if (dir == 'E') {
                rx++;
            } else {
                break;
            }
        }
        return new long[] {rx, ry};
    }

    private static long[] solveV3(String cmd, int size) {
        long sx = 0, sy = 0;
        for (; pos < cmd.length(); pos++) {
            char dir = cmd.charAt(pos);
            if (dir == ')') {
                break;
            } else if (dir >= '2' && dir <= '9') {
                int round = dir - '0';
                pos += 2;
                long[] result = solveV3(cmd, size);
                sx += round * result[0];
                sy += round * result[1];
            } else if (dir == 'W') {
                sx--;
            } else if (dir == 'E') {
                sx++;
            } else if (dir == 'S') {
                sy++;
            } else if (dir == 'N') {
                sy--;
            }
        }
        return new long[]{sx, sy};
    }

    private static int[] solve(String cmd, int size, int sx, int sy) {
        for (int i = 0; i < cmd.length(); i++) {
            char dir = cmd.charAt(i);
            if (dir >= '2' && dir <= '9') {
                int start = cmd.indexOf('(', i);
                int end = findPair(cmd, start + 1);
                int round = dir - '0';
                String subCmd = cmd.substring(start + 1, end);
                for (int j = 0; j < round; j++) {
                    int[] result = solve(subCmd, size, sx, sy);
                    sx = result[0];
                    sy = result[1];
                }
                i = end;
            } else {
                int[] result = move(dir, sx, sy, size);
                sx = result[0];
                sy = result[1];
            }
        }
        return new int[]{sx, sy};
    }

    private static long[] solveV2(String cmd) {
        long sx = 0, sy = 0;
        for (int i = 0; i < cmd.length(); i++) {
            char dir = cmd.charAt(i);
            if (dir >= '2' && dir <= '9') {
                int start = cmd.indexOf('(', i);
                int end = findPair(cmd, start + 1);
                int round = dir - '0';
                String subCmd = cmd.substring(start + 1, end);
                long[] result = solveV2(subCmd);
                sx += round * result[0];
                sy += round*result[1];
                i = end;
            } else if (dir == 'W') {
                sx--;
            } else if (dir == 'E') {
                sx++;
            } else if (dir == 'S') {
                sy++;
            } else if (dir == 'N') {
                sy--;
            }
        }
        return new long[]{sx, sy};
    }

    static int[] move(char cmd, int x, int y, int size) {
        if (cmd == 'W') {
            x--;
        } else if (cmd == 'E') {
            x++;
        } else if (cmd == 'S') {
            y++;
        } else {
            y--;
        }
        x = (x + size) % size;
        y = (y + size) % size;
        return new int[] {x, y};
    }
    static int findPair(String s, int start) {
        int count = 1;
        int i = start;
        for (; i < s.length() && count > 0; i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                count--;
            }
        }
        return i-1;
    }
}
