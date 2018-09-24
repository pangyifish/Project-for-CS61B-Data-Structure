package hw4.puzzle;

import  edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;



public class Solver {
    private int moves;
    private Stack<WorldState> solution;


    public Solver(WorldState initial) {
        MinPQ<SearchNode> searchNode = new MinPQ<>();
        SearchNode ini = new SearchNode(initial, 0, null);
        searchNode.insert(ini);
        SearchNode out = searchNode.delMin();

        while (!out.w.isGoal()) {
            for (WorldState n : out.w.neighbors()) {
                if (out.previous == null || !n.equals(out.previous.w)) {
                    SearchNode m = new SearchNode(n, out.move + 1, out);
                    searchNode.insert(m);
                }
            }
            out = searchNode.delMin();
        }

        moves = out.move;
        solution = new Stack<>();
        while (out != null) {
            solution.push(out.w);
            out = out.previous;
        }


    }

    private class SearchNode implements Comparable<SearchNode> {
        WorldState w;
        int move;
        SearchNode previous;
        int priority;

        SearchNode (WorldState w, int move, SearchNode previous) {
            this.w = w;
            this.move = move;
            this.previous = previous;
            priority = w.estimatedDistanceToGoal() + move;
        }



        public int compareTo(SearchNode o) {
            return priority - o.priority;
        }
    }


    public int moves() {
        return moves;
    }


    public Iterable<WorldState> solution() {
        return solution;
    }
}