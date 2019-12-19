package genome;

import genome.node.InputNode;
import genome.node.OutputNode;

public class Graph {
    private InputNode[] inputNodes;
    private OutputNode[] outputNodes;

    public int inSize = 0, outSize = 0;

    public Graph() {

    }

    public Graph(InputNode[] inputNodes, OutputNode[] outputNodes) {
        this.inputNodes = inputNodes;
        this.outputNodes = outputNodes;
    }

    public void setInputNodes(InputNode... inputNodes) {
        this.inputNodes = inputNodes;
        this.inSize = inputNodes.length;
    }

    public void setOutputNodes(OutputNode... outputNodes) {
        this.outputNodes = outputNodes;
        this.outSize = outputNodes.length;
    }

    public InputNode[] getInputNodes() {
        return inputNodes;
    }

    public OutputNode[] getOutputNodes() {
        return outputNodes;
    }
}
