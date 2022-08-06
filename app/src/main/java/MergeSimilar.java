import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;

/**
 * https://leetcode.com/contest/biweekly-contest-84/problems/merge-similar-items/
 *
 * @author Alexey Shurygin
 */
public class MergeSimilar {
    public List<List<Integer>> mergeSimilarItems(int[][] i1, int[][] i2) {
        var m = new TreeMap<Integer, Integer>();
        Consumer<int[]> action = x -> {
            m.merge(x[0], x[1], Integer::sum);
        };
        Arrays.stream(i1).forEach(action);
        Arrays.stream(i2).forEach(action);
        var r = new ArrayList<List<Integer>>();
        m.forEach((k, v) -> r.add(List.of(k, v)));
        return r;
    }
}
