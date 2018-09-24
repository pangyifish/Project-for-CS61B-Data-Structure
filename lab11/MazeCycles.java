package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int s;
    private int t;
    private int v;
    private boolean targetFound = false;
    private int[] parents;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = 0;
        t = maze.N() * maze.N() - 1;
        v = s;
        distTo[v] = 0;
        parents = new int[maze.N() * maze.N()];
        parents[v] = 0;

    }

    @Override
    public void solve() {
        // TODO: Your code here!
        marked[v] = true;
        announce();

       if (v == t) {
            targetFound = true;
        }

        if (targetFound) return;

        for (int w : maze.adj(v)) {
            if (marked[w] == true && w != parents[v] && w != 0) {
                edgeTo[w] = v;
                announce();
                return;
            }
        if (!marked[w]) {
            parents[w] = v;
            announce();
            distTo[w] = distTo[v] + 1;
            v = w;
            solve();


            if (targetFound) return;

        }
    }
}



}

    // Helper methods go here

