package algorithm.genome;

public class OrderedGenome extends AbstractGenome {

    /*
    Rules for ordered Genome:
    1. The total amount of nodes is constant.
    2. The amount of nodes per level is constant.
    3. The coefficient of connections and nodes can and most likely will change!
     */

    int hiddenNodes;
    int hiddenLayers;

    public OrderedGenome(Node[] InputNodes, int hiddenNodes, Node[] OutputNodes, int hiddenLayers) {
        super(InputNodes, OutputNodes);
        this.hiddenNodes = hiddenNodes;
        this.hiddenLayers = hiddenLayers;
        addHidden(hiddenNodes);
    }

    private void addHidden(int hiddenNodes) {
        int nodeNum = this.getInputNodes().length + this.getOutputNodes().length;
        for (int i = nodeNum; i <= nodeNum + hiddenLayers * hiddenNodes - 1; i++) {
            getHiddenNodes().add(new Node(1, i, NodeType.HIDDEN));
        }
    }

    @Override
    public void count() {

    }

    @Override
    public Genome copy() {
        return null;
    }
}
