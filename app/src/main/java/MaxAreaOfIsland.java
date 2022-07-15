import java.util.*;

/**
 * https://leetcode.com/problems/max-area-of-island/
 *
 * @author Alexey Shurygin
 */
class MaxAreaOfIsland {
    record K(int i, int j) {
    }

    public int maxAreaOfIsland(int[][] a) {
        //BFS
        int max = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[i].length; j++)
                max = Math.max(walk(a, i, j), max);
        return max;
    }

    private int walk(int[][] a, int i, int j) {
        if (a[i][j] != 1)
            return 0;
        Queue<K> q = new ArrayDeque<>();
        if (a[i][j] == 1) {
            a[i][j] = 2;
            q.add(new K(i, j));
        }
        int size = 0;
        while (!q.isEmpty()) {
            K e = q.remove();
            size++;
            if (e.i - 1 >= 0 && a[e.i - 1][e.j] == 1) {
                a[e.i - 1][e.j] = 2;
                q.add(new K(e.i - 1, e.j));
            }
            if (e.i + 1 < a.length && a[e.i + 1][e.j] == 1) {
                a[e.i + 1][e.j] = 2;
                q.add(new K(e.i + 1, e.j));
            }
            if (e.j - 1 >= 0 && a[e.i][e.j - 1] == 1) {
                a[e.i][e.j - 1] = 2;
                q.add(new K(e.i, e.j - 1));
            }
            if (e.j + 1 < a[e.i].length && a[e.i][e.j + 1] == 1) {
                a[e.i][e.j + 1] = 2;
                q.add(new K(e.i, e.j + 1));
            }
        }
        return size;
    }
}
