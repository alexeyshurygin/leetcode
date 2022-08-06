import java.util.*;
import java.util.function.Consumer;

/**
 * https://leetcode.com/contest/biweekly-contest-84/problems/merge-similar-items/
 *
 * @author Alexey Shurygin
 */
public class MergeSimilar {
    public List<List<Integer>> mergeSimilarItems(int[][] i1, int[][] i2) {
        SortedMap<Integer, Integer> m = new TreeMap<>();
        Consumer<int[]> action = x -> {
            int k = x[0];
            m.put(k, m.getOrDefault(k, 0) + x[1]);
        };
        Arrays.stream(i1).forEach(action);
        Arrays.stream(i2).forEach(action);
        List<List<Integer>> r = new ArrayList<>();
        m.forEach((k, v) -> {
            ArrayList<Integer> a = new ArrayList<>();
            a.add(k);
            a.add(v);
            r.add(a);
        });
        return r;
    }
}
