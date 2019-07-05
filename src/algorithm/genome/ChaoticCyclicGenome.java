package algorithm.genome;

import java.util.Arrays;
import java.util.List;

public class ChaoticCyclicGenome extends AbstractGenome {

    //both constructors
    //the second one may be not used at all and only will be useful in recreating the save Genome

    public ChaoticCyclicGenome(Node[] InputNodes, Node[] OutputNodes){
        super(InputNodes, OutputNodes);
    }

    public ChaoticCyclicGenome(List<Node> HiddenNodes, List<Connection> connections, Node[] InputNodes, Node[] OutputNodes){
        super(HiddenNodes, connections, InputNodes, OutputNodes);
    }

    //private add functions in order to change Genome structure during it's mutations

    private void addHiddenNodes(Node... newHiddenNodes){
        super.getHiddenNodes().addAll(Arrays.asList(newHiddenNodes));
    }

    private void addConnections(Connection... newConnections){
        super.getConnections().addAll(Arrays.asList(newConnections));
    }

    //count function is used to find out the number this Genome produces at certain point of time

    @Override
    public void count() {

    }

    @Override
    public Genome copy() {
        return null;
    }

}
