package node;

public class SampleTrees {

    public static TreeNode getSampleTree1() {
        TreeNode s = new TreeNode("S", null);
        TreeNode a = new TreeNode("A", null);
        TreeNode b = new TreeNode("B", null);
        TreeNode c = new TreeNode("C", null);
        TreeNode d = new TreeNode("D", null);
        TreeNode e = new TreeNode("E", null);
        TreeNode f = new TreeNode("F", null);
        TreeNode g = new TreeNode("G", null);
        TreeNode h = new TreeNode("H", null);

        s.links = new Link[]{new Link(a, 5), new Link(b, 2), new Link(c, 4)};
        a.links = new Link[]{new Link(d, 9), new Link(e, 4)};
        b.links = new Link[]{new Link(g, 6)};
        c.links = new Link[]{new Link(f, 2)};
        d.links = new Link[]{new Link(h, 7)};
        e.links = new Link[]{new Link(g, 6)};
        f.links = new Link[]{new Link(g, 1)};

        return s;
    }

    public static TreeNode getSampleTree2() {
        TreeNode s = new TreeNode("S", null);
        TreeNode a = new TreeNode("A", null);
        TreeNode b = new TreeNode("B", null);
        TreeNode c = new TreeNode("C", null);
        TreeNode d = new TreeNode("D", null);
        TreeNode e = new TreeNode("E", null);
        TreeNode f = new TreeNode("F", null);
        TreeNode g = new TreeNode("G", null);

        s.links = new Link[]{new Link(a, 1), new Link(b, 5), new Link(c, 8)};
        a.links = new Link[]{new Link(d, 3), new Link(e, 7), new Link(g, 9)};
        b.links = new Link[]{new Link(g, 4)};
        c.links = new Link[]{new Link(g, 5)};

        return s;
    }

    public static TreeNode getSampleTree3() {
        TreeNode n8 = new TreeNode(8, null);
        TreeNode n9 = new TreeNode(9, null);
        TreeNode n10 = new TreeNode(10, null);
        TreeNode n11 = new TreeNode(11, null);
        TreeNode n12 = new TreeNode(12, null);
        TreeNode n13 = new TreeNode(13, null);
        TreeNode n14 = new TreeNode(14, null);
        TreeNode n15 = new TreeNode(15, null);

        TreeNode n4 = new TreeNode(4, new Link[]{new Link(n8, 1), new Link(n9, 1)});
        TreeNode n5 = new TreeNode(5, new Link[]{new Link(n10, 1), new Link(n11, 1)});
        TreeNode n6 = new TreeNode(6, new Link[]{new Link(n12, 1), new Link(n13, 1)});
        TreeNode n7 = new TreeNode(7, new Link[]{new Link(n14, 1), new Link(n15, 1)});

        TreeNode n2 = new TreeNode(2, new Link[]{new Link(n4, 1), new Link(n5, 1)});
        TreeNode n3 = new TreeNode(3, new Link[]{new Link(n6, 1), new Link(n7, 1)});

        return new TreeNode(1, new Link[]{new Link(n2, 1), new Link(n3, 1)});
    }

    public static TreeNode getSampleTree4(){
        TreeNode s = new TreeNode("S", null);
        TreeNode a = new TreeNode("A", null);
        TreeNode b = new TreeNode("B", null);
        TreeNode c = new TreeNode("C", null);
        TreeNode d = new TreeNode("D", null);
        TreeNode e = new TreeNode("E", null);
        TreeNode g = new TreeNode("G", null);

        s.links = new Link[]{new Link(a, 2), new Link(b, 1), new Link(g, 9)};
        a.links = new Link[]{new Link(c, 2), new Link(d, 0)};
        b.links = new Link[]{new Link(d, 2), new Link(e, 3)};
        c.links = new Link[]{new Link(g, 4)};
        d.links = new Link[]{new Link(g, 4)};

        return s;
    }

}
