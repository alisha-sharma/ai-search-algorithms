package search;

import node.Link;
import node.TreeNode;

import java.util.*;

/**
 * This class contains helper methods to perform different uninformed search algorithms.
 */
public class Uninformed {

    /**
     * Perform BFS.
     *
     * @param tree the start node of the search tree/graph.
     * @param goal the goal node.
     * @return the result of search that include goal node, expanded nodes, path, and cost.
     */
    public static SearchResult bfs(TreeNode tree, Object goal) {
        // Initialize lists and add start node to the frontier list.
        List<TreeNode> expanded = new LinkedList<>();
        // BFS uses a FIFO queue.
        Queue<TreeNode> frontier = new LinkedList<>();
        frontier.add(tree);

        // Pick a node from the frontier until it is empty.
        while (!frontier.isEmpty()) {
            TreeNode node = frontier.remove();
            // Check if the goal has been reached.
            if (String.valueOf(goal).equals(String.valueOf(node.value))) {
                // if goal matches, return search result with the goal node.
                return getSearchResult(expanded, node);
            }

            // No need to expand if already expanded.
            if (expanded.contains(node)) continue;
            expanded.add(node);
            if (node.links == null) continue;

            for (Link link : node.links) {
                // Iterate all outgoing links (child nodes).
                if (!frontier.contains(link.node)) {
                    // copy path from parent node to store path from start node.
                    copyPathFromParent(node, link);
                    // add the node to the frontier list.
                    frontier.add(link.node);
                }
            }
        }

        // If goal is not found, return search result with null result.
        return new SearchResult(null, expanded, null, 0);
    }

    /**
     * Perform UCS.
     *
     * @param tree the start node of the search tree/graph.
     * @param goal the goal node.
     * @return the result of search that include goal node, expanded nodes, path, and cost.
     */
    public static SearchResult ucs(TreeNode tree, Object goal) {
        // Initialize lists and add start node to the frontier list.
        List<TreeNode> expanded = new LinkedList<>();
        // UCS uses a Priority queue.
        PriorityQueue<TreeNode> frontier = new PriorityQueue<>();
        frontier.add(tree);

        // Pick a node from the frontier until it is empty.
        while (!frontier.isEmpty()) {
            TreeNode node = frontier.remove();
            // Check if the goal has been reached.
            if (String.valueOf(goal).equals(String.valueOf(node.value))) {
                // if goal matches, return search result with the goal node.
                return getSearchResult(expanded, node);
            }

            // No need to expand if already expanded.
            if (expanded.contains(node)) continue;
            expanded.add(node);
            if (node.links == null) continue;

            for (Link link : node.links) {
                // Iterate all outgoing links (child nodes).
                if (!frontier.contains(link.node) || node.getCost() + link.cost < link.node.getCost()) {
                    // copy path from parent node to store path from start node.
                    copyPathFromParent(node, link);
                }
                // add the node to the frontier list.
                frontier.add(link.node);
            }
        }

        // If goal is not found, return search result with null result.
        return new SearchResult(null, expanded, null, 0);
    }

    /**
     * Perform DFS.
     *
     * @param tree the start node of the search tree/graph.
     * @param goal the goal node.
     * @return the result of search that include goal node, expanded nodes, path, and cost.
     */
    public static SearchResult dfs(TreeNode tree, Object goal) {
        // Initialize lists and add start node to the frontier list.
        List<TreeNode> expanded = new LinkedList<>();
        // DFS uses a stack.
        Stack<TreeNode> frontier = new Stack<>();
        frontier.add(tree);

        // Pick a node from the frontier until it is empty.
        while (!frontier.isEmpty()) {
            TreeNode node = frontier.pop();
            // Check if the goal has been reached.
            if (String.valueOf(goal).equals(String.valueOf(node.value))) {
                // if goal matches, return search result with the goal node.
                return getSearchResult(expanded, node);
            }

            // No need to expand if already expanded.
            if (expanded.contains(node)) continue;
            expanded.add(node);
            if (node.links == null) continue;

            Link[] links = node.links;
            // Iterate all outgoing links (child nodes) in reverse order.
            for (int i = links.length - 1; i >= 0; i--) {
                Link link = links[i];
                if (!frontier.contains(link.node)) {
                    // copy path from parent node to store path from start node.
                    copyPathFromParent(node, link);
                    // add the node to the frontier list.
                    frontier.add(link.node);
                }
            }
        }

        // If goal is not found, return search result with null result.
        return new SearchResult(null, expanded, null, 0);
    }

    /**
     * Perform DLS.
     *
     * @param tree  the start node of the search tree/graph.
     * @param goal  the goal node.
     * @param limit the limit up to which search needs to be performed.
     * @return the result of search that include goal node, expanded nodes, path, and cost.
     */
    public static SearchResult dls(TreeNode tree, Object goal, int limit) {
        // Initialize lists and add start node to the frontier list.
        List<TreeNode> expanded = new LinkedList<>();
        // DLS uses a stack.
        Stack<TreeNode> frontier = new Stack<>();
        frontier.add(tree);

        boolean childrenRemaining = false;

        // Pick a node from the frontier until it is empty.
        while (!frontier.isEmpty()) {
            TreeNode node = frontier.pop();
            // Check if the goal has been reached.
            if (String.valueOf(goal).equals(String.valueOf(node.value))) {
                // if goal matches, return search result with the goal node.
                return getSearchResult(expanded, node);
            }

            // No need to expand if already expanded.
            if (expanded.contains(node)) continue;
            expanded.add(node);

            if (node.getDepth() >= limit) {
                // Used by IDS - If depth limit is reached, check if any more children are remaining.
                childrenRemaining |= node.links != null && node.links.length > 0;
                continue;
            }

            if (node.links == null) continue;

            Link[] links = node.links;
            // Iterate all outgoing links (child nodes) in reverse order.
            for (int i = links.length - 1; i >= 0; i--) {
                Link link = links[i];
                if (!frontier.contains(link.node)) {
                    // copy path from parent node to store path from start node.
                    copyPathFromParent(node, link);
                    // add the node to the frontier list.
                    frontier.add(link.node);
                }
            }
        }

        // If goal is not found, return search result with null result.
        SearchResult searchResult = new SearchResult(null, expanded, null, 0);
        // Used by IDS - flag to check how the search was stopped - limit or no results.
        // If there is any child node remaining, IDS need to increment limit and search again.
        // Otherwise, if no child is left, It needs to stop searching.
        searchResult.limitExceeded = childrenRemaining;
        return searchResult;
    }

    /**
     * Perform IDS.
     *
     * @param tree the start node of the search tree/graph.
     * @param goal the goal node.
     * @return the result of search that include goal node, expanded nodes, path, and cost.
     */
    public static SearchResult ids(TreeNode tree, Object goal) {
        // Start with limit of 1.
        int limit = 1;
        // Initialize a list to store all expanded nodes in all iterations.
        List<TreeNode> expandedNodes = new ArrayList<>();
        // Loop indefinitely
        while (true) {
            // Try to search using DLS with current limit, then increase the limit for next iteration.
            SearchResult searchResult = dls(tree, goal, limit++);
            // Add all expanded nodes in the current iteration to the list of expanded nodes.
            expandedNodes.addAll(searchResult.expandedNodes);

            // If goal is not found and the search was stopped because the depth limit exceeded,
            // continue to next iteration.
            if (searchResult.result == null && searchResult.limitExceeded) continue;

            // If the search was not stopped because of depth limit
            // (either goal is found, or if there are no more children), return the search result.
            return new SearchResult(searchResult.result, expandedNodes, searchResult.path, searchResult.cost);
        }
    }

    /**
     * Internal method to copy the path from start node to a given node.
     *
     * @param parent parent node.
     * @param link   link from the parent node to a given node.
     */
    private static void copyPathFromParent(TreeNode parent, Link link) {
        TreeNode node = link.node;
        // clear existing links
        node.pathFromParent.clear();
        // copy all links to reach parent node from start node.
        node.pathFromParent.addAll(parent.pathFromParent);
        // add a link from parent node to the given node.
        node.pathFromParent.add(new Link(parent, link.cost));
    }

    /**
     * Internal method to create an instance of SearchResult after the search is executed.
     *
     * @param expanded the list of all expanded nodes.
     * @param result   the result node.
     * @return an instance of search result.
     */
    private static SearchResult getSearchResult(List<TreeNode> expanded, TreeNode result) {
        List<TreeNode> path = new ArrayList<>();
        for (Link link : result.pathFromParent) {
            path.add(link.node);
        }
        path.add(result);
        return new SearchResult(result, expanded, path, result.getCost());
    }

}
