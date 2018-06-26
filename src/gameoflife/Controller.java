package gameoflife;

public class Controller
{
    /**

     * Dans cette classe, on utilise une matrice "booleenne" composé que de 1 et de 0 pour représenter les cells
     * de la grille(1 pour en vie(ALIVE) et 0 pour mort(DEAD)
     * Au début, toutes les cells sont initialisés à DEAD. après l'initialisation, on modifie les valeurs de celles
     * selectionnéesen ALIVE.
     *
     * La structure de la classe est la suivante:
     *      - int[][] cells : matrice représentant les cellules.
     *      - class Neighborhood : une classe interne représentant le voisinage. Elle contient
     *                             int[][] neighbors : une matrice représentant les cellules du voisinage
     *                             Position target : la position de la cellule concernée .

     *  les méthodes implémentées:
     *      - boolean isAlive(int e) {
     *              renvoie l'état de la cellule(dead ou alive);
     *        }
     *      - boolean isInCorner(Position p) {
     *              permet de verifier si la cellule dont la position est passée en paramètre est positioné sur
     *              un corner(coin) de la grille;
     *        }
     *      - boolean isInBorder(Position p) {
     *               permet de verifier si la cellule dont la position est passée en paramètre est positioné sur
     *              une bordure(border) de la grille;;
     *        }
     *      - int whichCorner(Position p) {
     *            permet de verifier sur quelle corner la cellule dont la position est passée en paramètre est positioné
     *        }
     *      - int whichBorder(Position p) {
     *               permet de verifier sur quelle border la cellule dont la position est passée en paramètre est
     *               positioné
     *             elle retourne BORDER_TOP, BORDER_RIGHT, BORDER_BOTTOM or BOTTOM_LEFT
     *        }
     *
     *      - void changeState(Position p, int n) {
     *              elle change l'état (ALIVE/DEAD) de la cellule qui a n voisins vivants
     *              et p qui représente sa position
     *        }
     *      - Neighborhood extractNeighborhood(int i, int j) {
     *            elle permet de recupérer un tableau contenant les voisins de la cellule de coordonnées i et j
     *        }
     *      - void nextGeneration() {
     *              C'est la méthode "principale" du jeu qui fait appel aux autres méthodes
     *        }
     *------------------------------------------------------------------------------------------------------------------
     */

    private int [][]cells;

    private static final int

            /* The state of a cell: ALIVE or DEAD */
            ALIVE            = 1,
            DEAD             = 0,

            /* The 4 corners in the grid */
            TOP_LEFT         = 0,
            TOP_RIGHT        = 1,
            BOTTOM_RIGHT     = 2,
            BOTTOM_LEFT      = 3,

            /* The 4 borders in the grid */
            BORDER_TOP       = 0,
            BORDER_RIGHT     = 1,
            BORDER_BOTTOM    = 2,
            BORDER_LEFT      = 3,

            /* The 3 different number of neighbors possible in the grid */
            CORNER_NEIGHBORS = 3,
            BORDER_NEIGHBORS = 5,
            CENTER_NEIGHBORS = 8;

    private final int
            MIN_BOUND,
            MAX_BOUND;

    private class Neighborhood
    {
        /**
         *Positin de la cellule dont nous voulons extraire les voisins */
        private Position target;

        private int[][] neighbors;

        public Neighborhood(){}

    }

    public Controller(int [][]cells)
    {
        this.cells = cells;
        this.MIN_BOUND = 0;
        this.MAX_BOUND = this.cells.length - 1;
    }

    public int[][] getCells() {
        return cells;
    }


    public void setCells(int[][] cells) {
        this.cells = cells;
    }


    public void nextGeneration() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                Position position = new Position(i, j);
                int cpt = 0;
                Neighborhood neighborhood = extractNeighborhood(position);
                for (int k = 0; k < neighborhood.neighbors.length; k++) {
                    for (int l = 0; l < neighborhood.neighbors.length; l++) {
                        Position p = neighborhood.target;
                        if (!(p.getX() == k && p.getY() == l)) {
                            if (neighborhood.neighbors[k][l] == ALIVE) {
                                cpt++;
                            }
                        }
                    }
                }
                this.changeState(position, cpt);

    }}}

    private void changeState(Position p, int n)
    {
        if (isAlive(cells[p.getX()][p.getY()]) && (n < 2 || n > 3))
            this.cells[p.getX()][p.getY()] = DEAD;
        else if ((!isAlive(cells[p.getX()][p.getY()]) && n == 3))
            this.cells[p.getX()][p.getY()] = ALIVE;
    }


    private Neighborhood extractNeighborhood(Position p)
    {
        int neighbors[][] = new int[3][3], i = p.getX(), j = p.getY();
        Neighborhood neighborhood = new Neighborhood();
        int []A;
        int []B;
        int n = cells.length-1;
        switch (evaluateNumberOfNeighbors(p)) {
            case CORNER_NEIGHBORS:
                //neighbors = new int[CORNER_NEIGHBORS-1][CORNER_NEIGHBORS-1];
                switch (whichCorner(p)) {
                    case TOP_LEFT:
                        A = new int[]{n, 0, 1};
                        neighborhood.target = new Position(0, 0);
                        //for (int k = 0; k < a.length; k++) for (int l = 0; l < a.length; l++)
                            //a[k][l] = cells[i+k][j+l];
                        for (int k = 0, a = 0; k < neighbors.length && a < A.length; k++, a++) {
                            for (int l = 0, b = 0; l < neighbors.length && b < A.length; l++, b++) {
                                neighbors[k][l] = cells[A[a]][A[b]];
                            }
                        }
                        break;
                    case TOP_RIGHT:
                        A = new int[]{n, 0, 1};
                        B = new int[]{n-1, n, 0};
                        neighborhood.target = new Position(0, 1);
                        //for (int k = 0; k < neighbors.length; k++) for (int l = 0; l < neighbors.length; l++)
                            //neighbors[k][l] = cells[i+k][j-(1-l)];
                        for (int k = 0, a = 0; k < neighbors.length && a < A.length; k++, a++) {
                            for (int l = 0, b = 0; l < neighbors.length && b < B.length; l++, b++) {
                                neighbors[k][l] = cells[A[a]][B[b]];
                            }
                        }
                        break;
                    case BOTTOM_RIGHT:
                        A = new int[]{n-1, n, 0};
                        neighborhood.target = new Position(1, 1);
                        //for (int k = 0; k < neighbors.length; k++) for (int l = 0; l < neighbors.length; l++)
                            //neighbors[k][l] = cells[i-(1-k)][j-(1-l)];
                        for (int k = 0, a = 0; k < neighbors.length && a < A.length; k++, a++) {
                            for (int l = 0, b = 0; l < neighbors.length && b < A.length; l++, b++) {
                                neighbors[k][l] = cells[A[a]][A[b]];
                            }
                        }
                        break;
                    case BOTTOM_LEFT:
                        A = new int[]{n-1, n, 0};
                        B = new int[]{n, 0, 1};
                        neighborhood.target = new Position(1, 0);
                        for (int k = 0, a = 0; k < neighbors.length && a < A.length; k++, a++) {
                            for (int l = 0, b = 0; l < neighbors.length && b < B.length; l++, b++) {
                                neighbors[k][l] = cells[A[a]][B[b]];
                            }
                        }
                        break;
                    default: return null;
                }
                break;
            case BORDER_NEIGHBORS:
                switch (whichBorder(p)) {
                    case BORDER_TOP:
                        A = new int[]{n, 0, 1};
                        B = new int[]{j-1, j, j+1};

                        neighborhood.target = new Position(0, 1);
                        for (int k = 0, a = 0; k < neighbors.length && a < A.length; k++, a++) {
                            for (int l = 0, b = 0; l < neighbors.length && b < B.length; l++, b++) {
                                neighbors[k][l] = cells[A[a]][B[b]];
                            }
                        }
                        break;
                    case BORDER_RIGHT:
                        A = new int[]{i - 1, i, i + 1};
                        B = new int[]{n-1, n, 0};
                        neighborhood.target = new Position(1, 1);
                        for (int k = 0, a = 0; k < neighbors.length && a < A.length; k++, a++) {
                            for (int l = 0, b = 0; l < neighbors.length && b < B.length; l++, b++) {
                                neighbors[k][l] = cells[A[a]][B[b]];
                            }
                        }
                        break;
                    case BORDER_BOTTOM:
                        A = new int[]{n-1, n, 0};
                        B = new int[]{j-1, j, j+1};
                        neighborhood.target = new Position(1, 1);
                        for (int k = 0, a = 0; k < neighbors.length && a < A.length; k++, a++) {
                            for (int l = 0, b = 0; l < neighbors.length && b < B.length; l++, b++) {
                                neighbors[k][l] = cells[A[a]][B[b]];
                            }
                        }
                        break;
                    case BORDER_LEFT:
                        B = new int[]{n, 0, 1};
                        A = new int[]{i-1, i, i+1};
                        neighborhood.target = new Position(1, 0);
                        for (int k = 0, a = 0; k < neighbors.length && a < A.length; k++, a++) {
                            for (int l = 0, b = 0; l < neighbors.length && b < B.length; l++, b++) {
                                neighbors[k][l] = cells[A[a]][B[b]];
                            }
                        }
                        break;
                    default: return null;
                }
                break;
            case CENTER_NEIGHBORS:
                neighborhood.target = new Position(1, 1);
                for (int k = 0; k < neighbors.length; k++) for (int l = 0; l < neighbors.length; l++) neighbors[k][l] = cells[i-(1-k)][j-(1-l)];
                break;
            default: return null;
        }
        neighborhood.neighbors = neighbors;
        return neighborhood;
    }

    /**
     * permet de verifier si une cellules avec la psition en paramètre est sur un corner ou pas

     */
    private boolean isInCorner(Position p)
    {
        int i = p.getX(), j = p.getY();
        return (i == MIN_BOUND && j == MIN_BOUND) || (i == MIN_BOUND && j == MAX_BOUND)
                        || (i == MAX_BOUND && j == MIN_BOUND) || (i == MAX_BOUND && j == MAX_BOUND);
    }

    /**
     *  permet de verifier sur quel  corner la cellule se trouve
     */
    private int whichCorner(Position p)
    {
        int i = p.getX(), j = p.getY();
        if (i == MIN_BOUND && j == MIN_BOUND) return TOP_LEFT;
        else if (i == MIN_BOUND && j == MAX_BOUND) return TOP_RIGHT;
        else if (i == MAX_BOUND && j == MAX_BOUND) return BOTTOM_RIGHT;
        else return BOTTOM_LEFT;
    }

    /**
     *  * permet de verifier si une cellules avec la psition en paramètre est sur un border ou pas

     */
    private boolean isInBorder(Position p)
    {
        int i = p.getX(), j = p.getY();
        return (
                (i == MIN_BOUND || i == MAX_BOUND) && (j > MIN_BOUND && j < MAX_BOUND)
        ) || (
                (j == MIN_BOUND || j == MAX_BOUND) && (i > MIN_BOUND && i < MAX_BOUND)
        );
    }

    /**
     *  permet de verifier sur quel  corner la cellule se trouve

     */
    private int whichBorder(Position p)
    {
        int i = p.getX(), j = p.getY();
        if (i == MIN_BOUND && (j > MIN_BOUND && j < MAX_BOUND)) return BORDER_TOP;
        else if ((i > MIN_BOUND && i < MAX_BOUND) && j == MAX_BOUND) return BORDER_RIGHT;
        else if (i == MAX_BOUND && (j > MIN_BOUND && j < MAX_BOUND)) return BORDER_BOTTOM;
        else return BORDER_LEFT;
    }

    /**
     * Allows to calculate the number of neighbors a given position in the cell has.
     *
     * @param p the position
     * @return int the number of neighbors
     */
    private int evaluateNumberOfNeighbors(Position p)
    {
        if (isInCorner(p)) return CORNER_NEIGHBORS;
        else if (isInBorder(p)) return BORDER_NEIGHBORS;
        else return CENTER_NEIGHBORS;
    }

    /**
     * Allows to test if a cell is alive or not
     *
     * @param e the value of the cell
     * @return boolean true if alive and false if not
     */
    private boolean isAlive(int e)
    {
        return e == ALIVE;
    }

}
