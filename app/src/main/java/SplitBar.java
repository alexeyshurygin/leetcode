import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Alexey Shurygin
 */
public class SplitBar {
    class Result {
        private final int[] bar;
        private final int[] boundaries;

        public Result(int[] bar) {
            this.bar = bar.clone();
            boundaries = new int[0];
        }

        public Result(int[] bar, final int... boundaries) {
            this.bar = bar.clone();
            if (boundaries.length % 2 != 0)
                throw new IllegalArgumentException("...");
            this.boundaries = boundaries.clone();
        }

        public int getK() {
            return boundaries.length;
        }

        int getMinSegment() {
            int min = Integer.MIN_VALUE;
            for (int b = 0; b < boundaries.length - 1; b++) {
                int sum = 0;
                for (int seg = boundaries[b]; seg < boundaries[b + 1] - 1; seg++) {
                    sum += bar[seg];
                }
                min = Math.min(min, sum);
            }
            return min;
        }
    }

    List<Result> breakBar(int[] bar, int k, int n) {
        if (k == 0) {
            return Collections.singletonList(new Result(bar));
        } else if (k == 1) {
            return Collections.singletonList(new Result(bar, 1, n));
        } else {
            List<Result> subResults = breakBar(bar, k - 1, n);
            return breakResults(subResults, bar, k, n);
        }
    }

    private List<Result> breakResults(List<Result> subResults, int[] bar, int k, int n) {
        List<Result> results = new ArrayList<>();
        for (Result sub : subResults) {
            results.addAll(breakResult(sub, bar, k, n));
        }
        return results;
    }

    private List<Result> breakResult(Result subResult, int[] bar, int k, int n) {
        List<Result> results = new ArrayList<>();
        for (int i = 0; i < bar.length; i++) {

        }
        return results;
    }

//
//    Result min;
//    Result interim;
//    void breakIn(int[] a, int k, int l, int preSum) {
//        if (l >= a.length) {
//            if (// TODO
//            return // TODO;
//        }
//        int sum = preSum;
//        for (int i=l; i < a.length; i++) {
//            sum+=a[i];
//            estimateRestOfSolution();
//            breakIn(a, k-1, i, sum);
//            if (interim.min > min.min) {
//                min = interim;
//            }
//        }
//    }
//    Result break(int[] a, int k) {
//        min = new Result();
//        interim = new Result();
//        breakIn(a, k, 0, 0);
//        return min;
//    }
}
