package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    int v = 0;
    Queue<Integer> bfs= new Queue<>();

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX,sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
        v = s;
        bfs.enqueue(v);
        // Add more variables here!
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()


        if (v == t) {
            targetFound = true;
        }

        if (targetFound) {
            return;
        }

        bfs.dequeue();
        announce();

        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                bfs.enqueue(w);
                edgeTo[w] = v;
                announce();
                marked[w] = true;
                distTo[w] = distTo[v] + 1;
            }
            if (targetFound) return;
        }

        for (int w: bfs) {
            v = w;
            bfs();
        }


    }


    @Override
    public void solve() {
        bfs();
    }
}
