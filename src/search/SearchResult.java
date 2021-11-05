package search;

import node.TreeNode;

import java.util.List;

/**
 * Data structure to hold the result of the search.
 */
public class SearchResult {

    public TreeNode result;
    public List<TreeNode> expandedNodes;
    public List<TreeNode> path;
    public double cost;

    /**
     * Used by IDS to check if search was stopped because the depth-limit had reached.
     * If the value is false, we can stop the iteration.
     */
    public boolean limitExceeded;

    public SearchResult(TreeNode result, List<TreeNode> expandedNodes, List<TreeNode> path, double cost) {
        this.result = result;
        this.expandedNodes = expandedNodes;
        this.path = path;
        this.cost = cost;
    }

}
