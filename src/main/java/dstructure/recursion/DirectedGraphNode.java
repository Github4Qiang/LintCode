package dstructure.recursion;

import java.util.ArrayList;

/**
 * Created by Polylanger on 9/14/2017.
 */
public class DirectedGraphNode {

    int label;
    ArrayList<DirectedGraphNode> neighbors;

    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }

    @Override
    public String toString() {
        return "DirectedGraphNode{" +
                "label=" + label +
                ", neighbors=" + neighbors +
                '}';
    }
}
