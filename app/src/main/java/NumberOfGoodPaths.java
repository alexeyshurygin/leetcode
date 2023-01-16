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
        var qs = new LinkedHashSet<Integer>();
        var qe = new LinkedHashSet<Integer>();
        var walkedS = new HashMap<Integer, Integer>();
        var walkedE = new HashMap<Integer, Integer>();
        qs.add(start);
        walkedS.put(start, vals[start]);
        qe.add(end);
        walkedE.put(end, vals[end]);
        while (!qs.isEmpty() || !qe.isEmpty()) {
            iters++;
            Integer nodeMax;
            if (!qs.isEmpty()) {
                nodeMax = walkQ(qs, walkedS, walkedE, vals, edgeMap);
                if (nodeMax != null) return nodeMax;
            }
            if (!qe.isEmpty()) {
                nodeMax = walkQ(qe, walkedE, walkedS, vals, edgeMap);
                if (nodeMax != null) return nodeMax;
            }
        }
        return -1;
    }

    Integer walkQ(LinkedHashSet<Integer> qs, HashMap<Integer, Integer> walkedS, HashMap<Integer, Integer> walkedE,
                  int[] vals, Map<Integer, List<Integer>> edgeMap) {
        assert !qs.isEmpty();
        Iterator<Integer> iter = qs.iterator();
        Integer node = iter.next();
        iter.remove();
        assert walkedS.containsKey(node);
        final Integer nodeSMax = walkedS.get(node);
        assert nodeSMax >= 0;
        Integer nodeEMax = walkedE.get(node);
        if (nodeEMax != null)
            return Math.max(nodeSMax, nodeEMax);
        //defensive
        edgeMap.getOrDefault(node, List.of()).stream().filter(Predicate.not(walkedS::containsKey)).forEach(n -> {
            int valMax = Math.max(nodeSMax, vals[n]);
            qs.add(n);
            walkedS.put(n, valMax);
        });
        return null;
    }
}
