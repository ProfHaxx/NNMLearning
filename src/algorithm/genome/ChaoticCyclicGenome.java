package algorithm.genome;

import java.util.*;

public class ChaoticCyclicGenome extends AbstractGenome {

    public static final ConnectionComparator connectionComparator = new ConnectionComparator();
    //helperMap is used in order to save the next step values
    public Map<Integer, Double> helperMap = new HashMap<>();

    //both constructors
    //the second one may be not used at all and only will be useful in recreating the save Genome

    public ChaoticCyclicGenome(Map<Integer, Node>  InputNodes, Map<Integer, Node>  OutputNodes){
        super(InputNodes, OutputNodes);
    }

    public ChaoticCyclicGenome(Map<Integer, Node> HiddenNodes, List<Connection> connections, Map<Integer, Node>  InputNodes, Map<Integer, Node>  OutputNodes){
        super(HiddenNodes, connections, InputNodes, OutputNodes);
    }

    //private add functions in order to change Genome structure during it's mutations

    private void addHiddenNodes(Node... newHiddenNodes){
        int Offset = super.getInputNodes().size() + super.getOutputNodes().size();
        for(Node node: newHiddenNodes){
            super.getHiddenNodes().put(Offset + getHiddenNodes().size(), node);
        }
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
        int newNodeID = super.getHiddenNodes().size() + super.getInputNodes().size() + super.getOutputNodes().size();
        super.getConnections().remove(connectionID);
        addHiddenNodes(new Node(0, NodeType.HIDDEN, 0));
        super.getConnections().add(new Connection(1.0, c.getInputNodeID(), newNodeID));
        super.getConnections().add(new Connection(c.getWeight(),newNodeID,c.getOutputNodeID()));
    }

    /*
    * Connection mutation:
    *     One node ID is being chosen from input node IDs or hidden node IDs
    *     The second node ID is being chosen from output node IDs or hidden node IDs
    */

    public void addConnectionMutation(Random r){
        Set<Integer> inputNodeIDs = super.getHiddenNodes().keySet();
        Set<Integer> outputNodeIDs = super.getHiddenNodes().keySet();
        inputNodeIDs.addAll(super.getInputNodes().keySet());
        outputNodeIDs.addAll(super.getOutputNodes().keySet());
        Integer[] inputNodeID = inputNodeIDs.toArray(new Integer[inputNodeIDs.size()]);
        Integer[] outputNodeID = inputNodeIDs.toArray(new Integer[inputNodeIDs.size()]);
        Connection newConnection = new Connection(1.0, inputNodeID[r.nextInt(inputNodeID.length)], outputNodeID[r.nextInt(outputNodeID.length)]);
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
        for(Node node: super.getHiddenNodes().values()){
            if(mutationChance < r.nextDouble()){
                node.setQ((r.nextDouble() * 2f - 1f) * mutationRatio + node.getQ());
            }
        }
    }

    //count function is used to find out the number this Genome produces at certain point of time

    @Override
    public void count() {
        for(Connection connection: super.getConnections()){

        }
    }

    @Override
    public AbstractGenome copy() {
        return new ChaoticCyclicGenome(super.getHiddenNodes(), super.getConnections(), super.getInputNodes(), super.getOutputNodes());
    }

}
