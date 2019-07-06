package algorithm.genome;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChaoticCyclicGenome extends AbstractGenome {

    public static final ConnectionComparator connectionComparator = new ConnectionComparator();

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
    * Connection mutation:
    *     One node ID is being chosen from input node IDs or hidden node IDs
    *     The second node ID is being chosen from output node IDs or hidden node IDs
    */

    public void addConnectionMutation(Random r){
        List<Node> inputNodes = super.getHiddenNodes();
        inputNodes.addAll(Arrays.asList(super.getInputNodes()));
        List<Node> outputNodes = super.getHiddenNodes();
        outputNodes.addAll(Arrays.asList(super.getOutputNodes()));
        int inputNodeID = inputNodes.get(r.nextInt(inputNodes.size())).getID();
        int outputNodeID = outputNodes.get(r.nextInt(outputNodes.size())).getID();
        Connection newConnection = new Connection(1.0, inputNodeID, outputNodeID);
        boolean changed = false;
        for(Connection connection: super.getConnections()){
            if(connectionComparator.compare(connection, newConnection) == 0){
                changed = true;
                break;
            }
        }
        if(!changed){
            addConnections(newConnection);
        }
    }

    /**
     * @param mutationChance: chance that selected Connection would mutate, it has to be smaller than 1 and bigger than 0
     * @param mutationRatio: Number that sets the scale of the change
     */

    public void ConnectionWeightMutation(double mutationChance, double mutationRatio, Random r){
        for(Connection connection: super.getConnections()){
            if(mutationChance < r.nextDouble()){
                connection.setWeight((r.nextDouble() * 2f - 1f) * mutationRatio + connection.getWeight());
            }
        }
    }

    /**
     * @param mutationChance: chance that selected Node would mutate, it has to be smaller than 1 and bigger than 0
     * @param mutationRatio: Number that sets the scale of the change
     */

    public void NodeValueMutation(double mutationChance, double mutationRatio, Random r){
        for(Node node: super.getHiddenNodes()){
            if(mutationChance < r.nextDouble()){
                node.setValue((r.nextDouble() * 2f - 1f) * mutationRatio + node.getValue());
            }
        }
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
