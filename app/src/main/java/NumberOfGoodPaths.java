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
        var walkedS = new HashMap<Integer, Integer>();
        var walkedE = new HashMap<Integer, Integer>();
        walkedS.put(start, vals[start]);
        walkedE.put(end, vals[end]);
        var qs = new PriorityQueue<Integer>((o1, o2) -> {
            var v1 = walkedS.get(o1);
            var v2 = walkedS.get(o2);
            return Integer.compare(v1, v2);
        });
        var qe = new PriorityQueue<Integer>((o1, o2) -> {
            var v1 = walkedE.get(o1);
            var v2 = walkedE.get(o2);
            return Integer.compare(v1, v2);
        });
        qs.add(start);
        qe.add(end);
        while (!qs.isEmpty() || !qe.isEmpty()) {
            iters++;
            Integer nodeMax;
            nodeMax = walkQ(qs, walkedS, walkedE, vals, edgeMap);
            if (nodeMax != null) return nodeMax;
            nodeMax = walkQ(qe, walkedE, walkedS, vals, edgeMap);
            if (nodeMax != null) return nodeMax;
        }
        return -1;
    }

    Integer walkQ(PriorityQueue<Integer> qs, HashMap<Integer, Integer> walkedS, HashMap<Integer, Integer> walkedE,
                  int[] vals, Map<Integer, List<Integer>> edgeMap) {
        if (qs.isEmpty()) return null;
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
            walkedS.put(n, valMax);
            qs.add(n);
        });
        return null;
    }
}
