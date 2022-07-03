import java.util.Arrays;

/**
 * @author Alexey Shurygin
 */
public class SpiralArray {
    int[][] spiral(int n) {
        int[][] a = new int[n][n];
        int shift = 0;
        int c = 1;
        for (; c <= n * n; shift++) {
            int x = shift;
            for (int i = shift; i <= n - shift - 1; i++, c++) {
                a[x][i] = c;
            }
            x = n - shift - 1;
            for (int i = shift + 1; i <= x; i++, c++) {
                a[i][x] = c;
            }
            x = n - shift - 1;
            for (int i = x - 1; i >= shift; i--, c++) {
                a[x][i] = c;
            }
            x = shift;
            for (int i = n - shift - 2; i > shift; i--, c++) {
                a[i][x] = c;
            }
        }
        printArray(a);
        return a;
    }

    int[][] spiral2(int n) {
        int[][] a = new int[n][n];
        int shift = 0;
        int c = 1;
        int di = 0;
        int dj = 1;
        int i = 0, j = 0;
        for (; ; ) {
            for (; c <= n * n && i >= 0 && i < n && j >= 0 && j < n && a[i][j] == 0; c++) {
                a[i][j] = c;
                i += di;
                j += dj;
            }

            if (c > n * n) {
                break;
            }
            i -= di;
            j -= dj;
            if (di < 0) {
                di = 0;
                dj = -1;
            } else if (di > 0) {
                di = 0;
                dj = 1;
            } else if (dj < 0) {
                di = 1;
                dj = 0;
            } else {
                di = -1;
                dj = 0;
            }
            i += di;
            j += dj;
        }
        printArray(a);
        return a;
    }

    private void printArray(int[][] a) {
        for (int[] ints : a) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void main(String[] args) {
        SpiralArray inst = new SpiralArray();
        inst.spiral2(3);
        inst.spiral2(1);
        inst.spiral2(4);
        inst.spiral2(5);
        inst.spiral2(0);
    }
}
