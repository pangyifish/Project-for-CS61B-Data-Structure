package hw4.puzzle;


import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{

        int[] board;
        int N;
        int BLANK = 0;


        public Board(int[][] tiles) {
            N = tiles.length;
            board = new int[N * N];
            int k = 0;
            for (int i = 0; i < N; i += 1) {
                for (int j = 0; j < N; j += 1) {
                    board [k++] = tiles[i][j];
                }
            }
        }

        private Board (int[] tiles, int size) {
            N = size;
            board = new int[N * N];
            for (int i = 0; i < N; i += 1) {
                board[i] = tiles [i];
            }
        }

        public int tileAt(int i, int j) {
            return board[N * i + j];
        }

        public int size() {
            return N;
        }

        @Override
        public Iterable<WorldState> neighbors() {
            Queue<WorldState> neighbors = new Queue<>();
            int hug = size();
            int bug = -1;
            int zug = -1;
            for (int rug = 0; rug < hug; rug++) {
                for (int tug = 0; tug < hug; tug++) {
                    if (tileAt(rug, tug) == BLANK) {
                        bug = rug;
                        zug = tug;
                    }
                }
            }
            int[][] ili1li1 = new int[hug][hug];
            for (int pug = 0; pug < hug; pug++) {
                for (int yug = 0; yug < hug; yug++) {
                    ili1li1[pug][yug] = tileAt(pug, yug);
                }
            }
            for (int l11il = 0; l11il < hug; l11il++) {
                for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                    if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                        ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                        ili1li1[l11il][lil1il1] = BLANK;
                        Board neighbor = new Board(ili1li1);
                        neighbors.enqueue(neighbor);
                        ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                        ili1li1[bug][zug] = BLANK;
                    }
                }
            }
            return neighbors;
        }

        public int hamming() {
            int val = 0;
            for (int i = 0; i < board.length - 1; i ++) {
                int n = board[i];
                if (n != 0 && n != i + 1) val++;
            }
            return val;
        }

        public int manhattan() {
            int val = 0;
            for (int i = 0; i < board.length - 1; i ++) {
                int n = board[i];
                if (i < board.length) val += Math.abs(i + 1 - n);
                if (i == board.length - 1) val += n;
            }
            return val;
        }

        public int estimatedDistanceToGoal() {
            return manhattan();
        }

        public boolean equals(Object y) {
            if (this == y) return true;
            if (this == null) return false;
            if (this.getClass() != y.getClass()) return false;
            Board n = (Board) y;
            if (N != n.N) return false;
            for (int i = 0; i < board.length; i ++) {
                if (board[i] != n.board[i]) return false;
            }
            return true;
        }


        /**
         * Returns the string representation of the board.
         * Uncomment this method.
         */
        public String toString() {
            StringBuilder s = new StringBuilder();
            int N = size();
            s.append(N + "\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    s.append(String.format("%2d ", tileAt(i, j)));
                }
                s.append("\n");
            }
            s.append("\n");
            return s.toString();
        }
    }

