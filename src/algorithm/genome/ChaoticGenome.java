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
            ChaoticGenome newChaotic = this.copy();
            newChaotic.addConnections(new Connection(1, inputID,outputID));
            if(ChaoticGenome.Cyclic(newChaotic, new ArrayList<>(), inputID)){
                this.addConnections(new Connection(1, inputID,outputID));
                return;
            }
        }

        System.out.println("No Connection established");
    }

    private static boolean Cyclic(ChaoticGenome chaoticCyclicGenome, List<Node> usedNodes, int currentNodeID){
        usedNodes.add(chaoticCyclicGenome.getNodes().get(currentNodeID));
        if(chaoticCyclicGenome.getNodes().get(currentNodeID).getType() != NodeType.OUTPUT ){
            for(Connection con: chaoticCyclicGenome.getConnections()){
                if(con.getInputNodeID() == currentNodeID){
                    if(!usedNodes.contains(chaoticCyclicGenome.getNodes().get(con.getOutputNodeID()))){
                        if(!Cyclic(chaoticCyclicGenome, usedNodes, con.getOutputNodeID())){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Map<Integer, Node> getNodes() {
        return super.getNodes();
    }

    @Override
    public List<Connection> getConnections() {
        return super.getConnections();
    }

    @Override
    public void count() {

    }

    @Override
    public ChaoticGenome copy() {
        return null;
    }
}
