package algorithm.genome;

import java.math.BigDecimal;
import java.util.*;

public class ChaoticGenome extends AbstractGenome{

    public ChaoticGenome(Map<Integer, Node> Nodes, List<Connection> connections){
        super(Nodes, connections);
        for(Connection con: connections){
        }
    }

    private void addHiddenNodes(Node... newHiddenNodes){
        for(Node node: newHiddenNodes){
            super.getNodes().put(super.getNodes().size(), node);
        }
    }

    private void addConnections(Connection... newConnections){
        super.getConnections().addAll(Arrays.asList(newConnections));
        for(Connection con: newConnections){
        }
    }

    public void nodeWeightMutation(double mutationChance, double mutationRatio, Random r){
        for(Node node: super.getNodes().values()){
            if(mutationChance < r.nextDouble()){
                node.setQ((r.nextDouble() * 2f - 1f) * mutationRatio + node.getQ());
            }
        }
    }

    public void ConnectionWeightMutation(double mutationChance, double mutationRatio, Random r){
        for(Connection connection: super.getConnections()){
            if(mutationChance < r.nextDouble()){
                connection.setWeight((r.nextDouble() * 2f - 1f) * mutationRatio + connection.getWeight());
            }
        }
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
                Connection newConnection = new Connection(1, inputID,outputID);
                this.addConnections(newConnection);
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
        Map<Integer,BigDecimal> allCountedNodes = new HashMap<>();
        for(Integer i: super.getNodes().keySet()){
            if(super.getNodes().get(i).getType() == NodeType.OUTPUT){
                insideCount(i, allCountedNodes);
            }
        }
    }

    private BigDecimal insideCount(int currentNodeID, Map<Integer,BigDecimal> allCountedNodes){
        if(super.getNodes().get(currentNodeID).getType() == NodeType.INPUT){
            return new BigDecimal(super.getNodes().get(currentNodeID).getValue());
        }else{
            BigDecimal answer = new BigDecimal(0);
            for(Connection con: super.getConnections()){
                if(con.getOutputNodeID() == currentNodeID){
                    if(allCountedNodes.containsKey(con.getInputNodeID())){
                        answer.add(allCountedNodes.get(con.getInputNodeID()));
                    }else {
                        BigDecimal entry = insideCount(con.getInputNodeID(), allCountedNodes).multiply(new BigDecimal(con.getWeight()));
                        answer.add(entry);
                        allCountedNodes.put(con.getInputNodeID(), entry);
                    }
                }
            }
            answer.add(new BigDecimal(super.getNodes().get(currentNodeID).getQ()));
            return answer;
        }
    }

    @Override
    public ChaoticGenome copy() {
        return new ChaoticGenome(this.getNodes(), this.getConnections());
    }
}
