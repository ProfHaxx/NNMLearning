package algorithm.genome;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    /*
    Node mutation:
        One connection from the Genome is chosen in order to be replaced by new Node and
        two connections
    */

    public void addNodeMutation(Random r){
        int connectionID = r.nextInt(super.getConnections().size());
        Connection c = super.getConnections().get(connectionID);
        int newNodeID = super.getHiddenNodes().size() + super.getInputNodes().length + super.getOutputNodes().length;
        super.getConnections().remove(connectionID);
        super.getHiddenNodes().add(new Node(0, newNodeID, NodeType.HIDDEN));
        super.getConnections().add(new Connection(1.0, c.getInputNodeID(), newNodeID));
        super.getConnections().add(new Connection(c.getWeight(),newNodeID,c.getOutputNodeID()));
    }

    /*
    Connection mutation:
        One node ID is being chosen from input node IDs or hidden node IDs
        The second node ID is being chosen from output node IDs or hidden node IDs
    */

    public void addConnectionMutation(Random r){

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
