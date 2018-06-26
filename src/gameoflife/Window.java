package gameoflife;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private Grid grid;
    private JButton _nextGeneration_;

    public Window(String title, int w, int h) {
        this.setTitle(title);
        this.setSize(w, h);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        grid = new Grid();
        this.add(grid, BorderLayout.CENTER);
        _nextGeneration_ = new JButton("Next generation");
        _nextGeneration_.addActionListener(actionEvent -> {
            int[][] states = grid.getStates();
            boolean flag = true;
            for (int row[] : states) for (int cell : row) if (cell != 0) flag = false;
            if (flag) {
                JOptionPane.showMessageDialog(null, "Veuillez cliquer sur la grille pour choisir un pattern de depart");
            } else {
                Controller controller = new Controller(states);
                controller.nextGeneration();
                this.grid.updateGrid(controller.getCells());
            }
        });
        this.add(_nextGeneration_, BorderLayout.SOUTH);
        this.setVisible(true);
    }

}
