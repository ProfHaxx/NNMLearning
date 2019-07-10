package algorithm.genome;

import java.util.*;

public class ChaoticGenome extends AbstractGenome{

    public ChaoticGenome(Map<Integer, Node> Nodes, List<Connection> connections){
        super(Nodes, connections);
    }

    private void addHiddenNodes(Node... newHiddenNodes){
        for(Node node: newHiddenNodes){
            super.getNodes().put(super.getNodes().size(), node);
        }
    }

    private void addConnections(Connection... newConnections){
        super.getConnections().addAll(Arrays.asList(newConnections));
    }

    public void addNodeMutation(Random r){
        int connectionID = r.nextInt(super.getConnections().size());
        Connection c = super.getConnections().get(connectionID);
        int newNodeID = super.getNodes().size();
        super.getConnections().remove(connectionID);
        addHiddenNodes(new Node(0, NodeType.HIDDEN, 0));
        super.getConnections().add(new Connection(1.0, c.getInputNodeID(), newNodeID));
        super.getConnections().add(new Connection(c.getWeight(),newNodeID,c.getOutputNodeID()));
    }

    public void addConnectionMutation(Random r){
        List<>
    }

    @Override
    public void count() {

    }

    @Override
    public AbstractGenome copy() {
        return null;
    }
}
