package visual;

import genome.Graph;

import javax.swing.*;
import java.awt.*;

public class GraphVisualizer extends JFrame {
    public GraphVisualizer(Graph graph) {
        super("Graph Visualizer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(854, 480);
        this.setVisible(true);
    }
}

class GraphDrawer extends JPanel {
    private Graph graph;

    GraphDrawer(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void paint(Graphics g) {

    }
}
