import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/explore/interview/card/top-interview-questions-hard/118/trees-and-graphs/846/
 *
 * @author Alexey Shurygin
 */
public class FriendCircles {
    public int findCircleNum(int[][] am) {
        if (am.length == 0)
            return 0;
        Set<Integer> remaining = new HashSet<>();
        for (int i = 0; i < am.length; i++) {
            remaining.add(i);
        }
        System.out.println(remaining);
        Queue<Integer> process = new ArrayDeque<>();
        int provinces = 0;
        while (!remaining.isEmpty()) {
            int node = remaining.iterator().next();
            remaining.remove(node);
            process.add(node);
            provinces++;
            System.out.println("node" + node);
            while (!process.isEmpty()) {
                int p = process.poll();
                System.out.println(p);
                for (int j = 0; j < am.length; j++) {
                    if (am[p][j] == 1 && remaining.remove(j)) {
                        System.out.println("here" + j);
                        process.add(j);
                    }
                }
            }
        }
        return provinces;
    }
}
