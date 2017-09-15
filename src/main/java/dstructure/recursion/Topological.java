package dstructure.recursion;

import java.util.ArrayList;

/**
 * Created by Polylanger on 9/14/2017.
 */
public class Topological {

    public static void main(String[] args) {
        ArrayList<DirectedGraphNode> graph = new ArrayList<DirectedGraphNode>();
        DirectedGraphNode node1 = new DirectedGraphNode(0);
        DirectedGraphNode node2 = new DirectedGraphNode(1);
        DirectedGraphNode node3 = new DirectedGraphNode(2);
        DirectedGraphNode node4 = new DirectedGraphNode(3);
        DirectedGraphNode node5 = new DirectedGraphNode(4);

        node2.neighbors.add(node4);
        node2.neighbors.add(node5);
        node3.neighbors.add(node2);
        node3.neighbors.add(node5);
        node4.neighbors.add(node5);

        graph.add(node1);
        graph.add(node2);
        graph.add(node3);
        graph.add(node4);
        graph.add(node5);

        Topological topological = new Topological();
        System.out.println(topological.topSort(graph));
    }

    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        for (DirectedGraphNode node : graph) {
            node.label = UNVISITED;
        }

        ArrayList<DirectedGraphNode> topList = new ArrayList<DirectedGraphNode>();
        for (DirectedGraphNode node : graph) {
            topHelp(node, topList);
        }
        return topList;
    }

    private void topHelp(DirectedGraphNode node,
                         ArrayList<DirectedGraphNode> topList) {
        if (node.label == UNVISITED) {
            node.label = VISITED;
            for (DirectedGraphNode neighbor : node.neighbors) {
                topHelp(neighbor, topList);
            }
            topList.add(0, node);
        }
    }

    public static final int UNVISITED = 0;
    public static final int VISITED = 1;

}
