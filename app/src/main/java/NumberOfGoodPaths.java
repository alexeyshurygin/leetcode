import java.util.*;
import java.util.function.Predicate;

/**
 * https://leetcode.com/problems/number-of-good-paths/
 *
 * @author Alexey Shurygin
 */
public class NumberOfGoodPaths {
    static int cacheHit;
    static int calls;

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        System.out.printf("Mem:%d\n", Runtime.getRuntime().maxMemory());
        Objects.requireNonNull(vals, "vals null");
        Objects.requireNonNull(edges, "edges null");
        int r = vals.length;
        var val2node = new HashMap<Integer, List<Integer>>();
        var edgeMap = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < vals.length; i++) val2node.computeIfAbsent(vals[i], k -> new ArrayList<>()).add(i);
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
                        int maxVal = findPaths(start, nodes.get(j), vals, edgeMap, cache);
                        if (maxVal >= 0 && maxVal <= vals[start])
                            r++;
                    }
                }
//                System.out.printf("Cache hits:%d, #calls:%d, cache:%d\n", cacheHit, calls, cache.size());
            }
        }
        return r;
    }

    int findPaths(int start, int end, int[] vals, Map<Integer, List<Integer>> edgeMap, HashMap<CacheKey, Integer> cache) {
        calls++;
        //BFS
        var q = new LinkedHashMap<Integer, Integer>();
        var walked = new HashSet<Integer>();
        q.put(start, vals[start]);
        while (!q.isEmpty()) {
            Integer node = q.keySet().iterator().next();
            final Integer nodeMax = q.remove(node);
            assert nodeMax >= 0;
//            cache.putIfAbsent(new CacheKey(start, node), nodeMax);
            if (end == node)
                return nodeMax;
            Integer restVal = cache.get(new CacheKey(node, end));
            if (restVal != null) {
                cacheHit++;
                int val = restVal < 0 ? restVal : Math.max(nodeMax, restVal);
                cache.putIfAbsent(new CacheKey(start, end), val);
                return val;
            }
            walked.add(node);
            //defensive
            edgeMap.getOrDefault(node, List.of()).stream().filter(Predicate.not(walked::contains)).forEach(n -> q.put(n, Math.max(nodeMax, vals[n])));
        }
        cache.putIfAbsent(new CacheKey(start, end), -1);
        return -1;
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
            assert this.start <= this.end;
        }
    }
}
