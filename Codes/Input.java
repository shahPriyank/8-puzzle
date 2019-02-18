import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Input {

    public static int PUZZLE_SIZE = 3;
    public static String[][] initialState;
    public static String[][] finalState = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "*"}};
    public static int blankStateX;
    public static int blankStateY;
    public static int[] dx = {-1,0,1,0};
    public static int[] dy = {0,1,0,-1};
    public static Map<String, Integer> mapX = new HashMap<String, Integer>() {
        {
            put("1",0);
            put("2",0);
            put("3",0);
            put("4",1);
            put("5",1);
            put("6",1);
            put("7",2);
            put("8",2);
        }
    };
    public static Map<String, Integer> mapY = new HashMap<String, Integer>() {
        {
            put("1",0);
            put("2",1);
            put("3",2);
            put("4",0);
            put("5",1);
            put("6",2);
            put("7",0);
            put("8",1);
        }
    };


    public static int acceptInput() {
        Scanner scanner = new Scanner(System.in);
        initialState = new String[PUZZLE_SIZE][PUZZLE_SIZE];
        blankStateX = 0;
        blankStateY = 0;

        System.out.println("Enter the initial state row-wise");

        for (int i = 0; i < PUZZLE_SIZE; i++) {
            for (int j = 0; j < PUZZLE_SIZE; j++) {

                initialState[i][j] = scanner.next();
                if (initialState[i][j].equals("*")) {
                    blankStateX = i;
                    blankStateY = j;
                }
            }
        }

        System.out.println("Enter the search algorithm");
        System.out.println("1)BFS   2)IDS   3)AStar");
        return scanner.nextInt();
    }

    public static void initializeNodes(int typeOfSearch) {
        Node initialStateNode = new Node(Input.initialState, null, 0,
                Input.blankStateX, Input.blankStateY);
        Node finalStateNode = new Node(Input.finalState, null, 0,
                Input.PUZZLE_SIZE - 1, Input.PUZZLE_SIZE - 1);

        if (finalStateNode.equals(initialStateNode)) {
            System.out.println("Number of moves : 0");
            System.out.println("Number of states enqueued : 1");
            printFunction(initialStateNode.state);
        } else {
            switch (typeOfSearch) {
                case 1:
                    BFS.breadthFirstSearch(initialStateNode, finalStateNode);
                    break;
                case 2:
                    IDS.iterativeDeepeningSearch(initialStateNode, finalStateNode);
                    break;
                case 3:
                    System.out.println("Please select the heuristic function");
                    System.out.println("1) Incorrect Place 2) Manhattan Distance");
                    Scanner scanner = new Scanner(System.in);
                    int answer = scanner.nextInt();
                    switch (answer) {
                        case 1:
                            AStar.aStarWithHeuristics("aStarWithIncorrectPlaceHeuristic",
                                    initialStateNode, finalStateNode);
                            break;
                        case 2:
                            AStar.aStarWithHeuristics("aStarWithManhattanDistanceHeuristic",
                                    initialStateNode, finalStateNode);
                            break;
                    }
                    break;
            }
        }
    }

    private static void printFunction(String[][] state) {
        for (int i = 0; i< PUZZLE_SIZE; i++) {
            for (int j = 0; j< PUZZLE_SIZE; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printState(Node node, int moves, int enqueued) {
        if (node == null) {
            System.out.println("Number of moves : " + moves);
            System.out.println("Number of states enqueued : " + enqueued);
            return;
        }
        printState(node.parent, moves+1, enqueued);
        printFunction(node.state);
        System.out.println();
    }

    public static boolean validDimension(int a, int b) {
        return a >= 0 && a < PUZZLE_SIZE && b >= 0 && b < PUZZLE_SIZE;
    }

    public static String[][] swapBlankState(String[][] state,
                                            int x, int y, int newX, int newY) {
        String[][] newState = Arrays.stream(state).
                map(r -> r.clone()).toArray(String[][]::new);
        String temp;

        temp = newState[x][y];
        newState[x][y] = newState[newX][newY];
        newState[newX][newY] = temp;

        return newState;
    }

    public static Node getChildNode(Node node, int i) {
        String[][] newState = Input.swapBlankState(node.state, node.x,node.y,
                node.x+Input.dx[i], node.y+Input.dy[i]);
        return new Node(newState,node,node.depth+1,
                node.x+Input.dx[i],node.y+Input.dy[i]);
    }

    public static boolean isMaxLimitReached (int depth) {
        if (depth>10) {
            System.out.println("MAX DEPTH LIMIT REACHED - FAILURE");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int answer = acceptInput();
        initializeNodes(answer);
    }
}
