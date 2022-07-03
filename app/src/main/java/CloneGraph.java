//import java.util.HashMap;
//
///**
// * Definition for undirected graph.
// * class UndirectedGraphNode {
// * int label;
// * List<UndirectedGraphNode> neighbors;
// * UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
// * };
// */
//public class MySolution {
//    private java.util.Map<UndirectedGraphNode> cloned = new HashMap<>();
//
//    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
//        cloned.clear();
//        return cloneNodes(node);
//    }
//
//    public UndirectedGraphNode cloneNodes(UndirectedGraphNode node) {
//        if (cloned.contains(node))
//            return cloned.get(node);
//        UndirectedGraphNode newNode = new UndirectedGraphNode();
//        cloned.add(node, newNode);
//
//        for (UndirectedGraphNode n:node.neighbors) {
//            newNode.neighbors.add(cloneNodes(node));
//        }
//        return newNode;
//    }
//}
