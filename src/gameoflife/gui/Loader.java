package gameoflife.gui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Loader extends JFrame {

    public Loader() {
        this.setSize(600, 600);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JPanel
                mainPanel = new JPanel(new GridBagLayout()),
                titlePanel = new JPanel(),
                descriptionPanel = new JPanel(new BorderLayout()),
                controlButtonsPanel = new JPanel(),
                copyrightPanel = new JPanel();

        JLabel
                title = new JLabel("JEU DE LA VIE"),
                copyright = new JLabel("Elias Waly BA");

        JTextArea description = new JTextArea(readTextFile("help.txt"));
        description.setEditable(false);
        title.setFont(new Font(null, Font.BOLD, 35));
        copyright.setFont(new Font(null, Font.ITALIC, 14));
        copyrightPanel.add(copyright);
        descriptionPanel.add(description, BorderLayout.CENTER);
        descriptionPanel.setFont(new Font(null, Font.PLAIN, 20));
        titlePanel.add(title);
        titlePanel.setBackground(Color.WHITE);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(descriptionPanel, BorderLayout.CENTER);
        this.add(copyrightPanel, BorderLayout.SOUTH);
        this.setBackground(Color.WHITE);
        this.setVisible(true);
    }

    public static void main(String []args) {
        new Loader();
    }

    private static String readTextFile(String file) {
        String line;
        String content = "";
        try {
            // FileReader allows to easily read a text file in java with the default encoding system.
            FileReader fileReader = new FileReader(file);

            // It is always safer to wrap a FileReader object in a BufferedReader one
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Writing to the default out stream system the content of the file
            while ((line = bufferedReader.readLine()) != null) {
                content += "\n" + line;
            }

            // Always close our BufferedReader object for memory safety.
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
