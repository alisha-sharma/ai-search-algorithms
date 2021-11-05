package search;

import node.SampleTrees;
import node.TreeNode;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            showTreeOptions();
            int num = readNumber();
            TreeNode tree;
            if (num == 1) tree = SampleTrees.getSampleTree1();
            else if (num == 2) tree = SampleTrees.getSampleTree2();
            else if (num == 3) tree = SampleTrees.getSampleTree3();
            else if (num == 4) tree = SampleTrees.getSampleTree4();
            else if (num == 5) return;
            else {
                System.err.println("Invalid choice.");
                main(args);
                return;
            }

            String goal = readGoal();

            showSearchOptions();
            num = readNumber();

            SearchResult searchResult;
            if (num == 1) searchResult = Uninformed.bfs(tree, goal);
            else if (num == 2) searchResult = Uninformed.ucs(tree, goal);
            else if (num == 3) searchResult = Uninformed.dfs(tree, goal);
            else if (num == 4) {
                System.out.println("Enter limit: ");
                int limit = readNumber();
                searchResult = Uninformed.dls(tree, goal, limit);
            } else if (num == 5) searchResult = Uninformed.ids(tree, goal);
            else {
                if (num != 6) System.err.println("Invalid choice.");
                continue;
            }

            System.out.println("Expanded nodes: " + searchResult.expandedNodes);

            if (searchResult.result == null) {
                System.out.println("Goal not found, press enter to restart.");
                new Scanner(System.in).nextLine();
                continue;
            }

            System.out.println("Result: " + searchResult.result);
            System.out.println("Path: " + searchResult.path);
            System.out.println("Cost: " + searchResult.cost);

            System.out.println("Press enter to restart.");
            new Scanner(System.in).nextLine();
        }
    }

    private static void showTreeOptions() {
        System.out.println("\nSelect a tree: \n" +
                "1. Sample tree 1: " + SampleTrees.getSampleTree1().stringFormat() + "\n" +
                "2. Sample tree 2: " + SampleTrees.getSampleTree2().stringFormat() + "\n" +
                "3. Sample tree 3: " + SampleTrees.getSampleTree3().stringFormat() + "\n" +
                "4. Sample tree 4: " + SampleTrees.getSampleTree4().stringFormat() + "\n" +
                "5. Exit."
        );
    }

    private static int readNumber() {
        Scanner in = new Scanner(System.in);
        String numStr = in.nextLine();
        try {
            return Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            System.err.println("Unable to parse '" + numStr + "'. Please try again.");
            return readNumber();
        }
    }

    private static String readGoal() {
        System.out.println("Enter goal: ");
        return new Scanner(System.in).nextLine();
    }

    private static void showSearchOptions() {
        System.out.println("\nSelect search algorithm: \n" +
                "1. BFS\n" +
                "2. UCS\n" +
                "3. DFS\n" +
                "4. DLS\n" +
                "5. IDS\n" +
                "6. Back."
        );
    }

}
