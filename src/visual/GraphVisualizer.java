package visual;

import genome.Graph;
import genome.node.AbstractNode;
import genome.node.InputNode;
import genome.node.OutputNode;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class GraphVisualizer extends JFrame {

    static final int width = 854;
    static final int height = 480;
    static int distance = 50;
    static final int center = width/2;

    public GraphVisualizer(Graph graph) {
        super("Graph Visualizer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setVisible(true);
        this.add(new GraphDrawer(graph));
        this.repaint();
    }
}

class GraphDrawer extends JPanel {
    private Graph graph;
    private int inputs;
    private int outputs;

    GraphDrawer(Graph graph) {
        this.graph = graph;
        inputs = graph.inSize;
        outputs = graph.outSize;
    }

    @Override
    public void paint(Graphics g) {
        //Adjusting Input Distance
        if(GraphVisualizer.width < inputs*GraphVisualizer.distance) {
            GraphVisualizer.distance = GraphVisualizer.width / (inputs + 6);
        }

        //Switching to Input Color
        g.setColor(Color.GREEN);

        //Setting x-Coordinate Input Iterator
        int inIterator = GraphVisualizer.center - ((inputs-1)/2)*GraphVisualizer.distance;

        //Iterating through every input node
        for(InputNode node:graph.getInputNodes()) {
            g.fillOval(inIterator-10, 20, 25, 25);
            g.setColor(Color.BLACK);
            g.drawString("?", inIterator, 35);
            g.setColor(Color.GREEN);
            inIterator += GraphVisualizer.distance;

            /*
            //Handling Hidden Layers and Connections
            node.getConnections().keySet().forEach((K) -> {
                if(K instanceof OutputNode) {

                } else {

                }
            });
            */
        }

        //Adjusting Output Distance
        if(GraphVisualizer.width < outputs*50) {
            GraphVisualizer.distance = GraphVisualizer.width / (outputs + 6);
        } else {
            GraphVisualizer.distance = 50;
        }

        //Switching to Output Color
        g.setColor(Color.RED);

        //Setting x-Coordinate Input Iterator
        int outIterator = GraphVisualizer.center - ((outputs-1)/2)*GraphVisualizer.distance;

        //Iterating through every input node
        for(OutputNode node:graph.getOutputNodes()) {
            g.fillOval(outIterator-10, GraphVisualizer.height - 70, 25, 25);
            g.setColor(Color.BLACK);
            g.drawString("?", outIterator, GraphVisualizer.height - 55);
            g.setColor(Color.RED);
            outIterator += GraphVisualizer.distance;
        }

        //Reset Distance
        GraphVisualizer.distance = 50;
    }
}
