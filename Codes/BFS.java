import java.util.*;

public class BFS {

    public static void breadthFirstSearch(Node initialStateNode, Node finalStateNode) {
        Queue<Node> frontier = new LinkedList<>();
        List<Node> explored = new ArrayList<>();

        int enqueued = 1;

        frontier.add(initialStateNode);

        while (!frontier.isEmpty()){
            Node node = frontier.poll();
            explored.add(node);

            for (int i=0;i<Input.dx.length;i++) {
                if (Input.validDimension(node.x+Input.dx[i],node.y+Input.dy[i])) {
                    Node child = Input.getChildNode(node, i);

                    if (Input.isMaxLimitReached(child.depth)) {
                        return;
                    }

                    if(!explored.contains(child) && !frontier.contains(child)) {
                        if (child.equals(finalStateNode)) {
                            Input.printState(child, -1, enqueued);
                            return;
                        }
                        frontier.add(child);
                        enqueued++;

                    }
                }
            }
        }
        System.out.println("FAILURE");
    }

}
