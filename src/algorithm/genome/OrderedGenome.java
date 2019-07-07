package algorithm.genome;

import java.util.*;

public class OrderedGenome extends AbstractGenome {

    /*
    Rules for ordered Genome:
    1. The total amount of nodes is constant.
    2. The amount of nodes per level is constant.
    3. The coefficient of connections and nodes can and most likely will change!
     */

    int hiddenNodes;
    int hiddenLayers;

    public OrderedGenome(Map<Integer, Node> InputNodes, int hiddenNodes, int hiddenLayers) {
        super(InputNodes);
        this.hiddenNodes = hiddenNodes;
        this.hiddenLayers = hiddenLayers;
        addHidden(hiddenNodes);
    }

    private void addHidden(int hiddenNodes) {
        int nodeNum = this.getNodes().size();
        for (int i = nodeNum; i <= nodeNum + hiddenLayers * hiddenNodes - 1; i++) {
            getNodes().put(i,new Node(0, NodeType.HIDDEN, 1));
        }
    }

    @Override
    public void count() {

    }

    @Override
    public AbstractGenome copy() {
        return null;
    }
}
