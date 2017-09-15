package dstructure.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Polylanger on 9/12/2017.
 */
public class LevelOrder {

    /*
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> values = new ArrayList<List<Integer>>();
        Queue<TreeNode> prevLevel = new LinkedList<TreeNode>();
        prevLevel.offer(root);
        while (!prevLevel.isEmpty()) {
            Queue<TreeNode> nextLevel = new LinkedList<TreeNode>();
            List<Integer> valueLevel = new ArrayList<Integer>();
            // 所有上一层的子节点加入下一层中
            while (!prevLevel.isEmpty()) {
                TreeNode node = prevLevel.poll();
                valueLevel.add(node.val);
                if (node.left != null) nextLevel.offer(node.left);
                if (node.right != null) nextLevel.offer(node.right);
            }
            values.add(valueLevel);
            prevLevel.addAll(nextLevel);
        }
        return values;
    }

}
