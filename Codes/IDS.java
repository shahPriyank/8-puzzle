import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class IDS {

    private static int ENQUEUED = 0;

    private static boolean depthLimitedSearch(Node initialStateNode, Node finalStateNode,
                                              int depth) {
        Stack<Node> stack = new Stack<>();
        List<Node> explored = new ArrayList<>();
        stack.add(initialStateNode);

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (node.depth < depth) {
                explored.add(node);

                for (int i=0;i<Input.dx.length;i++) {
                    if (Input.validDimension(node.x + Input.dx[i], node.y + Input.dy[i])) {
                        Node childNode = Input.getChildNode(node, i);
                        if (!explored.contains(childNode) && !stack.contains(childNode)) {
                            if (childNode.equals(finalStateNode)) {
                                Input.printState(childNode, -1, ENQUEUED);
                                return true;
                            }
                            stack.push(childNode);
                            ENQUEUED++;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void iterativeDeepeningSearch(Node initialStateNode, Node finalStateNode) {
        for (int i=1;i<=10;i++) {
            ENQUEUED++;
            if (depthLimitedSearch(initialStateNode, finalStateNode, i)) {
                return;
            }
        }
        System.out.println("MAX DEPTH LIMIT REACHED - FAILURE");
    }
}
