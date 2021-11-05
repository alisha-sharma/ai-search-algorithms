package node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Data structure to hold a node information (node value and node links).
 */
public class TreeNode implements Comparable<TreeNode> {

    /**
     * Value stored in the node.
     */
    public Object value;

    /**
     * All links from the node.
     */
    public Link[] links;

    /**
     * All links from the start node to this node.
     */
    public LinkedList<Link> pathFromParent = new LinkedList<>();

    public TreeNode(Object value, Link[] links) {
        this.value = value;
        this.links = links;
    }

    /**
     * @return the cost to reach this node from the start node.
     */
    public double getCost() {
        double cost = 0;
        for (Link link : pathFromParent) {
            cost += link.cost;
        }
        return cost;
    }

    /**
     * @return the depth of this node in the tree.
     */
    public int getDepth() {
        return pathFromParent.size();
    }

    @Override
    public String toString() {
        return Objects.toString(value);
    }

    public String stringFormat() {
        return "[" + value + " -> " + Arrays.toString(links) + "]";
    }

    // method used internally for sorting in the priority queue.
    @Override
    public int compareTo(TreeNode other) {
        double cost = getCost();
        double otherCost = other.getCost();
        if (cost < otherCost) return -1;
        if (cost > otherCost) return 1;

        return String.valueOf(value).compareTo(String.valueOf(other.value));
    }

}
