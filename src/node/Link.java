package node;

/**
 * A link to/from a node.
 */
public class Link {

    /**
     * Source/destination node.
     */
    public TreeNode node;

    /**
     * Cost to/from the node in this link.
     */
    public double cost;

    public Link(TreeNode node, double cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return node.stringFormat() + ", " + cost;
        // return node.stringFormat();
    }

}
