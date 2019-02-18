import java.util.Arrays;

class Node {

    String[][] state;
    Node parent;
    int depth;
    int x;
    int y;

    Node(Node node) {
        this (node.state, node.parent, node.depth, node.x, node.y);
    }

    Node(String[][] state, Node parent, int depth, int x, int y) {
        this.state = state;
        this.parent = parent;
        this.depth = depth;
        this.x = x;
        this.y = y;
    }

    private static boolean equalFunction(String[][] state_1, String[][] state_2) {
        for (int i = 0; i < state_1.length; i++) {
            if (!Arrays.equals(state_1[i], state_2[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Node node = (Node) object;
            if (equalFunction(this.state, node.state)) {
                result = true;
            }
        }
        return result;
    }
}

class HeuristicNode extends Node {
    int heuristic;

    HeuristicNode(Node node, int heuristic) {
        super(node);
        this.heuristic = heuristic;
    }
}
