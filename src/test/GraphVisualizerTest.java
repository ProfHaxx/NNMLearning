package test;

import genome.Graph;
import genome.node.InputNode;
import genome.node.OutputNode;
import org.junit.Test;
import util.Utility;
import visual.GraphVisualizer;

public class GraphVisualizerTest {
    @Test
    public void testVisualization(){
        //Nodes Setup
        InputNode<Integer> in = new InputNode<>(1);
        InputNode<Integer> in1 = new InputNode<>(1);
        OutputNode<Integer> out = new OutputNode<>(2);
        in.connect(out, 1.2);
        //Graph Setup
        Graph graph = new Graph();
        graph.setInputNodes(in, in1);
        graph.setOutputNodes(out);

        new GraphVisualizer(graph);
        Utility.sleep(2000);
    }
}