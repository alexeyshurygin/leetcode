import java.util.*;

/**
 * https://leetcode.com/problems/number-of-good-paths/
 *
 * @author Alexey Shurygin
 */
public class NumberOfGoodPaths {
    static int cacheHit;
    static int calls;

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        Objects.requireNonNull(vals, "vals null");
        Objects.requireNonNull(edges, "edges null");
        int r = vals.length;
        var val2node = new HashMap<Integer, List<Integer>>();
        var edgeMap = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < vals.length; i++) val2node.computeIfAbsent(vals[i], k -> new ArrayList<>()).add(i);
//        for (int i = 0; i < vals.length; i++) {
//            edgeMap.put(i, new ArrayList<>());
//        }
        for (int[] edge : edges) {
            edgeMap.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            edgeMap.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        var cache = new HashMap<CacheKey, Integer>();
        for (List<Integer> nodes : val2node.values()) {
            if (nodes.size() >= 2) {
                //handle it
                for (int i = 0; i < nodes.size() - 1; i++) {
                    for (int j = i + 1; j < nodes.size(); j++) {
                        Integer start = nodes.get(i);
                        int maxValue = findPaths(start, nodes.get(j), vals, edgeMap, new HashSet<>(), cache, Integer.MIN_VALUE);
                        if (maxValue <= vals[start])
                            r++;
                    }
                }
                System.out.printf("Cache hits:%d #calls:%d\n", cacheHit, calls);
            }
        }
        return r;
    }

    int findPaths(int start, int end, int[] vals, Map<Integer, List<Integer>> edgeMap, Set<Integer> we, HashMap<CacheKey, Integer> cache,
                  int maxValue) {
        calls++;
        Integer max = cache.get(new CacheKey(start, end));
        if (max != null) {
            cacheHit++;
            return max;
        }
        if (start == end)
            return Math.max(maxValue, vals[end]);
        we.add(start);
        List<Integer> nodes = edgeMap.get(start);
        if (nodes != null) {
            //defensive
            for (int node : nodes) {
                if (vals[node] <= vals[end] && !we.contains(node)) {
                    r += findPaths(node, end, vals, edgeMap, we, cache, Math.max(maxValue, vals[node]));
                }
            }
        }
        we.remove(start);
        if (cache.containsKey(new CacheKey(start, end)))
            throw new AssertionError("Wrong cache state");
        cache.put(new CacheKey(start, end), r);
        return r;
    }

    record CacheKey(int start, int end) {
        CacheKey(int start, int end) {
            if (start <= end) {
                this.start = start;
                this.end = end;
            } else {
                this.start = end;
                this.end = start;
            }
        }
    }
}
