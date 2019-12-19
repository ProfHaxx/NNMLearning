package genome;

import genome.node.InputNode;
import genome.node.OutputNode;

public class Graph {
    private InputNode[] inputNodes;
    private OutputNode[] outputNodes;

    public Graph() {

    }

    public Graph(InputNode[] inputNodes, OutputNode[] outputNodes) {
        this.inputNodes = inputNodes;
        this.outputNodes = outputNodes;
    }

    public void setInputNodes(InputNode... inputNodes) {
        this.inputNodes = inputNodes;
    }

    public void setOutputNodes(OutputNode... outputNodes) {
        this.outputNodes = outputNodes;
    }
}
