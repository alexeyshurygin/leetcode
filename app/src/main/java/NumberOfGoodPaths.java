import java.util.*;
import java.util.function.Predicate;

/**
 * https://leetcode.com/problems/number-of-good-paths/
 *
 * @author Alexey Shurygin
 */
public class NumberOfGoodPaths {
//    static int calls;
//    static int iters;

    static int findPaths(int start, int end, int[] vals, Map<Integer, List<Integer>> edgeMap) {
//        calls++;
        //BFS
        var qs = new PriorityQueue<K>();
        var qe = new PriorityQueue<K>();
        qs.add(new K(start, vals[start]));
        qe.add(new K(end, vals[end]));
        var walkedS = new HashMap<Integer, Integer>();
        var walkedE = new HashMap<Integer, Integer>();
        walkedS.put(start, vals[start]);
        walkedE.put(end, vals[end]);
        while (!qs.isEmpty() || !qe.isEmpty()) {
//            iters++;
            Integer nodeMax = walkQ(qs, walkedS, walkedE, vals, edgeMap);
            if (nodeMax != null) return nodeMax;
            nodeMax = walkQ(qe, walkedE, walkedS, vals, edgeMap);
            if (nodeMax != null) return nodeMax;
        }
        return -1;
    }

    static Integer walkQ(PriorityQueue<K> qs, HashMap<Integer, Integer> walkedS, HashMap<Integer, Integer> walkedE,
                         int[] vals, Map<Integer, List<Integer>> edgeMap) {
        if (qs.isEmpty()) return null;
        Iterator<K> iter = qs.iterator();
        K k = iter.next();
        iter.remove();
//        assert walkedS.containsKey(k.n);
        final int nodeSMax = k.valMax;
//        assert nodeSMax >= 0;
        Integer nodeEMax = walkedE.get(k.n);
        if (nodeEMax != null)
            return Math.max(nodeSMax, nodeEMax);
        //defensive
        edgeMap.getOrDefault(k.n, List.of()).stream().filter(Predicate.not(walkedS::containsKey)).forEach(n -> {
            int valMax = Math.max(nodeSMax, vals[n]);
            qs.add(new K(n, valMax));
            walkedS.put(n, valMax);
        });
        return null;
    }

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
//        System.out.printf("Mem:%d\n", Runtime.getRuntime().maxMemory());
//        Objects.requireNonNull(vals, "vals null");
//        Objects.requireNonNull(edges, "edges null");
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
//                            System.out.printf("Calls:%d, iterations:%d\n", calls, iters);
                        }
                    }
                }
            }
        }
        return r;
    }

    record K(int n, int valMax) implements Comparable<K> {
        @Override
        public int compareTo(K o) {
            return Integer.compare(valMax, o.valMax);
        }
    }
}
