package gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grid extends JPanel
{
    /**
     * La classe Grid permet de gérer la mise en forme de notre jeu. L'utilisation de Jjava Graphics et Graphics nous
     * permet de dessiner les differentes cellules de la fenêtre.

     *------------------------------------------------------------------------------------------------------------------
     */

    /**
     * La variable MAX permet de definir le nombre de cellules de la grille
     */
    private static final int MAX = 25;

    /**
     * la variable states permet d'enregistrer les états des différentes cellules
     */
    private int[][] states = new int[MAX][MAX];

    /**
     * cells représente les différentes cellules de la grille
     */
    private Cell[][] cells = new Cell[MAX][MAX];

    private class Cell
    {
        /**

        /**
         * le champ Shape représente la forme de la cellule
         */
        private Shape shape;

        /**
         * La couleur de la cellule
         */
        private Color color;

        /**
         * la position de la cellule dans la grille
         */
        private Position position;

        Cell(Shape shape, Color color, int i, int j) {
            this.shape = shape;
            this.color = color;
            this.position = new Position(i, j);
        }

    }


    /**
     * Creation d'un panel pour dessiner la grille à l'intérieur
     */
    public Grid()
    {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                for (Cell line[] : cells) for (Cell c : line) {
                    if (c.shape.contains(mouseEvent.getPoint())) {
                        int x = c.position.getX(), y = c.position.getY();
                        c.color = c.color == Color.BLACK ? Color.WHITE : Color.BLACK;
                        states[x][y] = (c.color == Color.BLACK) ? 0 : 1;
                    }
                }
                repaint();
            }
        });
        this.populateGrid(cells);
    }

    /**
     * la méthode populateGrid permet d'initialiser les differentes cellules vivantes au départ(par l'utilisateur).
     * Il faudra sélectionner un nombre assez nombreux de cellules pour pouvoir observer l'évolution de la vie
     * Chaque cellule selectionné(par un clic) sera colorié en noir
     * Elle prend en paramètre une matrice de cells
     */
    private void populateGrid(Cell[][] cells)
    {
        for (int k = 0, i = 0; k < MAX && i < cells.length * MAX; k++, i += (MAX + 1))
            for (int l = 0, j = 0; l < MAX && j < cells.length * MAX; l++, j += (MAX + 1)) {
                Cell c = new Cell(new Rectangle(j, i, MAX, MAX), Color.WHITE, k, l);
                cells[k][l] = c;
            }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        for (Cell[] line : cells) for (Cell c : line) {
                g2.setColor(c.color);
                g2.fill(c.shape);
                int x = c.position.getX(), y = c.position.getY();
                if (c.color == Color.BLACK) this.states[x][y] = 1;
                else this.states[c.position.getX()][c.position.getY()] = 0;
        }
        g2.dispose();
    }

    /**
     * La méthode est appelée à chaque clic du bouton Next Generation
     * les cellules résultantes de la méthde NextGeneration sera introduits dans le panel

     */
    public void updateGrid(int[][] grid)
    {
        this.states = grid;
        for (Cell line[] : cells) for (Cell c : line) {
                int x = c.position.getX(), y = c.position.getY();
                c.color = states[x][y] == 1 ? Color.BLACK : Color.WHITE;
        }
        repaint();
    }


    public int[][] getStates()
    {
        return states;
    }

}
