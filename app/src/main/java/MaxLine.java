import java.util.*;

/**
 * https://leetcode.com/explore/interview/card/top-interview-questions-hard/123/math/873/
 *
 * @author Alexey Shurygin
 */
public class MaxLine {
    class Tuple {
        final double a;
        final double b;

        Tuple(double a, double b) {
            this.a = a;
            this.b = b;
        }

        public boolean equals(Object other) {
            boolean eq = other instanceof Tuple t && t.a == a && t.b == b;
            System.out.printf("Tuple.equals(%s, %s)=%b\n", this, other, eq);
            return eq;
        }

        public int hashCode() {
            return (int) (a + b * 1000);
        }

        public String toString() {
            return String.format("Tuple a: %g, b: %g", a, b);
        }
    }

    class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object other) {
            boolean eq = other instanceof Point p && p.x == x && p.y == y;
            System.out.printf("Point.equals(%s, %s)=%b\n", this, other, eq);
            return eq;
        }

        public int hashCode() {
            return x ^ 31 & y;
        }

        public String toString() {
            return String.format("Point x: %d, y: %d", x, y);
        }
    }

    public int maxPoints(int[][] points) {
        switch (points.length) {
            case 0:
                return 0;
            case 1:
                return 1;
        }
        Map<Tuple, Set<Point>> c = new HashMap<>();
        for (int i = 0; i < points.length - 1; i++) {
            /*
        y1=ax1+b
        y2-y1=a(x2-x1)
        a=(y2-y1)/(x2-x1)
        b=y1-ax1
         */
            for (int j = i + 1; j < points.length; j++) {
//                a=(y2-y1)/(x2-x1)
//                b=y1-ax1
                double a;
                double b;
                if (points[j][0] - points[i][0] != 0.0) {
                    a = ((double) (points[j][1] - points[i][1])) / ((double) (points[j][0] - points[i][0]));
                    b = points[i][1] - a * points[i][0];
                } else {
                    a = Double.POSITIVE_INFINITY;
                    b = points[i][0];
                }

                var t = new Tuple(a, b);
                System.out.println(t);
                var line = c.get(t);
                if (line == null) {
                    c.put(t, new HashSet<>(Set.of(new Point(points[i][0], points[i][1]), new Point(points[j][0], points[j][1]))));
                } else {
                    System.out.printf("Here!i:%d,j:%d\n", i, j);
                    line.add(new Point(points[j][0], points[j][1]));
                }
            }
        }
        int max = c.values().stream().map(v -> v.size()).max(Integer::compareTo).orElse(0);
        return max;
    }

    public static void main(String[] args) {
        System.out.println("Result:" + new MaxLine().maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}));
        System.out.println("Result:" + new MaxLine().maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        System.out.println("Result:" + new MaxLine().maxPoints(new int[][]{}));
        System.out.println("Result:" + new MaxLine().maxPoints(new int[][]{{1, 1}}));
        System.out.println("Result:" + new MaxLine().maxPoints(new int[][]{{0, 0}}));
        System.out.println("Result:" + new MaxLine().maxPoints(new int[][]{{1, 1}, {2, 1}, {3, 1}}));
    }
}
