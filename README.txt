Instructions to Run the 8-puzzle code

Files:
1)	Input.java
The file contains the main function, and all the common functions and parameters required by other files
2)	BFS.java
The file contains the main logic of BFS algorithm
3)	IDS.java
The file contains the main logic of IDS algorithm
4)	AStar.java
The file contains the main logic of AStar algorithm. The two heuristic functions used are Incorrect Place and Manhattan Distance
5)	Node.java
The node which is used for the search algorithms. It contains all the information of the state

Assumptions:
1)	The tree can go to maximum depth of 10 (Change it to whatever you want in Input.java)
2)	If the child of the node is the goal state, then the search will return the path from root to child
3)	Number of states enqueued denotes the states that are added to queue/stack
4)	User will enter the numbers row-wise in the range 1-8 without repeating any number and one blank space which denotes *
5)	The code is only for solving 8-puzzle problems (change the parameter PUZZLE_SIZE if you want to change to 15-puzzle and so on)
6)	User will always enter the correct number when asked about which algorithm or which heuristic should be used

Run:
1)	Compile all the given files
		javac *.java
2)	Run the Input.java which has the main function
		java Input
3)	Enter the position of numbers as asked
4)	Enter the search algorithm number you wish to perform
5)	Enter the heuristic number if you want to perform A*