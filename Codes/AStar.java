import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {

    public static int calculateHeuristic(String type, String[][] initial, String[][] goal) {
        if (type.equals("aStarWithIncorrectPlaceHeuristic")) {
            return calculateIncorrectPlaceHeuristic(initial, goal);
        } else if (type.equals("aStarWithManhattanDistanceHeuristic")){
            return calculateManhattanDistanceHeuristic(initial);
        }
        return 0;
    }

    public static int calculateManhattanDistanceHeuristic(String[][] node) {
        int heuristic = 0;
        for (int i=0;i<Input.PUZZLE_SIZE;i++) {
            for (int j=0;j<Input.PUZZLE_SIZE;j++) {
                if (!node[i][j].equals("*")) {
                    heuristic += Math.abs(
                            (i-Input.mapX.get(node[i][j]))+(j-Input.mapY.get(node[i][j])));
                }
            }
        }
        return heuristic;
    }

    public static int calculateIncorrectPlaceHeuristic(String[][] node, String[][] goalNode) {
        int heuristic = 0;
        for (int i=0;i<Input.PUZZLE_SIZE;i++) {
            for (int j=0;j<Input.PUZZLE_SIZE;j++) {
                if (!node[i][j].equals("*")) {
                    if (!node[i][j].equals(goalNode[i][j])) {
                        heuristic++;
                    }
                }
            }
        }
        return heuristic;
    }
    public static void aStarWithHeuristics(String type, Node initialStateNode, Node finalStateNode) {
        int heuristics;
        int enqueued = 1;
        PriorityQueue<HeuristicNode> priorityQueue = new PriorityQueue<>(
                (a,b) -> a.heuristic+a.depth - b.heuristic - b.depth);
        List<HeuristicNode> explored = new ArrayList<>();
        heuristics = calculateHeuristic(type, initialStateNode.state,finalStateNode.state);
        HeuristicNode initialHeuristicNode = new HeuristicNode(initialStateNode, heuristics);

        priorityQueue.add(initialHeuristicNode);

        while (!priorityQueue.isEmpty()) {
            HeuristicNode heuristicNode = priorityQueue.poll();
            explored.add(heuristicNode);

            for (int i=0;i<Input.dx.length;i++) {
                if (Input.validDimension(heuristicNode.x+Input.dx[i],
                        heuristicNode.y+Input.dy[i])) {
                    Node childNode = Input.getChildNode(heuristicNode, i);

                    if (Input.isMaxLimitReached(childNode.depth)) {
                        return;
                    }

                    if (!explored.contains(childNode) && !priorityQueue.contains(childNode)) {
                        if (childNode.equals(finalStateNode)) {
                            Input.printState(childNode, -1, enqueued);
                            return;
                        }
                        heuristics = calculateHeuristic(type, childNode.state, finalStateNode.state);
                        HeuristicNode hn = new HeuristicNode(childNode, heuristics);
                        priorityQueue.add(hn);
                        enqueued++;
                    }
                }
            }
        }
        System.out.println("FAILURE");

    }
}
