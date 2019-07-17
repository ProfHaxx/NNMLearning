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

    public void addConnectionMutation(Random r, int tryes){
        List<Node> inputNodes = new ArrayList<>(), outputNodes = new ArrayList<>();
        int inputCounter = 0, outputCounter = 0;
        for(Integer i: super.getNodes().keySet()) {
            switch (super.getNodes().get(i).getType()) {
                case HIDDEN:
                    inputNodes.add(super.getNodes().get(i));
                    outputNodes.add(super.getNodes().get(i));
                    inputCounter ++; outputCounter ++;
                    break;
                case OUTPUT:
                    outputNodes.add(super.getNodes().get(i));
                    outputCounter ++;
                    break;
                case INPUT:
                    inputNodes.add(super.getNodes().get(i));
                    inputCounter ++;
                    break;
            }
        }

        Node currentInput, currentOutput;
        for(int i = 0; i < tryes; i++){
            int inputID = r.nextInt(inputCounter);
            int outputID = r.nextInt(outputCounter);
            currentInput = inputNodes.get(inputID);
            currentOutput = outputNodes.get(outputID);
            if(inputID == outputID){
                continue;
            }

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
