import java.util.*;
import java.util.function.Predicate;

/**
 * https://leetcode.com/problems/number-of-good-paths/
 *
 * @author Alexey Shurygin
 */
public class NumberOfGoodPaths {
    static int calls;
    static int iters;

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
//        System.out.printf("Mem:%d\n", Runtime.getRuntime().maxMemory());
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
        for (List<Integer> nodes : val2node.values()) {
            if (nodes.size() >= 2) {
                //handle it
                for (int i = 0; i < nodes.size() - 1; i++) {
                    for (int j = i + 1; j < nodes.size(); j++) {
                        Integer start = nodes.get(i);
                        int maxVal = findPaths(start, nodes.get(j), vals, edgeMap);
                        if (maxVal >= 0 && maxVal <= vals[start]) {
                            r++;
                            System.out.printf("Calls:%d, iterations:%d\n", calls, iters);
                        }
                    }
                }
            }
        }
        return r;
    }

    int findPaths(int start, int end, int[] vals, Map<Integer, List<Integer>> edgeMap) {
        calls++;
        //BFS
        var q = new LinkedHashMap<Integer, Integer>();
        var walked = new HashSet<Integer>();
        q.put(start, vals[start]);
        walked.add(start);
        while (!q.isEmpty()) {
            iters++;
            Integer node = q.keySet().iterator().next();
            final Integer nodeMax = q.remove(node);
            assert nodeMax >= 0;
            assert walked.contains(node);
            if (end == node)
                return nodeMax;
            //defensive
            edgeMap.getOrDefault(node, List.of()).stream().filter(Predicate.not(walked::contains)).forEach(n -> {
                q.put(n, Math.max(nodeMax, vals[n]));
                walked.add(n);
            });
        }
        return -1;
    }
}
