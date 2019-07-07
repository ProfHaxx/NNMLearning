package algorithm.genome;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ChaoticCyclicGenome extends AbstractGenome {

    public static final ConnectionComparator connectionComparator = new ConnectionComparator();
    //helperMap is used in order to save the next step values
    public Map<Integer, Double> helperMap = new HashMap<>();

    //both constructors
    //the second one may be not used at all and only will be useful in recreating the save Genome

    public ChaoticCyclicGenome(Map<Integer, Node>  Nodes){
        super(Nodes);
    }

    public ChaoticCyclicGenome(Map<Integer, Node> Nodes, List<Connection> connections){
        super(Nodes, connections);
    }

    //private add functions in order to change Genome structure during it's mutations

    private void addHiddenNodes(Node... newHiddenNodes){
        for(Node node: newHiddenNodes){
            super.getNodes().put(super.getNodes().size(), node);
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
        int newNodeID = super.getNodes().size();
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

    private static final double selectionChance = 0.1;

    public void addConnectionMutation(Random r){
        Integer inputNodeID = null, outputNodeID = null;
        for(Integer i: super.getNodes().keySet()){
            switch (super.getNodes().get(i).getType()){
                case INPUT:
                    if(inputNodeID == null){
                        inputNodeID = i;
                    }else if(r.nextDouble() < selectionChance){
                        inputNodeID = 1;
                    }
                    break;
                case OUTPUT:
                    if(outputNodeID == null){
                        outputNodeID = i;
                    }else if(r.nextDouble() < selectionChance){
                        outputNodeID = 1;
                    }
                    break;
                case HIDDEN:
                    if(outputNodeID == null){
                        outputNodeID = i;
                    }else if(r.nextDouble() < selectionChance){
                        outputNodeID = 1;
                    }
                    if(inputNodeID == null){
                        inputNodeID = i;
                    }else if(r.nextDouble() < selectionChance){
                        inputNodeID = 1;
                    }
                    break;
            }
        }
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

    public void NodeQMutation(double mutationChance, double mutationRatio, Random r){
        for(Node node: super.getNodes().values()){
            if(mutationChance < r.nextDouble()){
                node.setQ((r.nextDouble() * 2f - 1f) * mutationRatio + node.getQ());
            }
        }
    }

    public static ChaoticCyclicGenome crossover(ChaoticCyclicGenome parent1, ChaoticCyclicGenome parent2, Random r){
        List<Connection> childConnections = parent1.getConnections();
        for(Connection connection: parent2.getConnections()){
            if(childConnections.contains(connection)){
                if(r.nextDouble() > 0.5){
                    childConnections.remove(connection);
                    childConnections.add(connection);
                }
            }
            else{
                childConnections.add(connection);
            }
        }
        Map<Integer, Node> childNodes = parent1.getNodes();
        for(int i: parent2.getNodes().keySet()){
            if(childNodes.containsKey(i)){
                if(r.nextDouble() > 0.5){
                    childNodes.replace(i, parent2.getNodes().get(i));
                }
            }
            else{
                childNodes.put(i, parent2.getNodes().get(i));
            }
        }
        return new ChaoticCyclicGenome(childNodes, childConnections);
    }

    //count function is used to find out the number this Genome produces at certain point of time

    @Override
    public void count() {
        helperMap.clear();
        for(int i: helperMap.keySet()){
            helperMap.put(i, 0.0);
        }
        for(Connection connection: super.getConnections()){
            helperMap.put(connection.getOutputNodeID(), super.getNodes().get(connection.getInputNodeID()).getValue() * connection.getWeight() + helperMap.get(connection.getOutputNodeID()));
        }
        for(int i: helperMap.keySet()){
            super.getNodes().get(i).setValue(helperMap.get(i));
        }
    }

    @Override
    public AbstractGenome copy() {
        return new ChaoticCyclicGenome(super.getNodes(), super.getConnections());
    }

    public static ChaoticCyclicGenome read(String name) {
        Map<Integer, Node> nodeMap = new HashMap<>();
        List<Connection> connections = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./savedGenomes/files/" + name))) {
            int roof = Integer.parseInt(reader.readLine());
            for (int i = 0; i < roof; i++) {
                String line2 = reader.readLine();
                String[] allInfo = line2.split(" ");
                connections.add(new Connection(Double.parseDouble(allInfo[2]), Integer.parseInt(allInfo[0]), Integer.parseInt(allInfo[1])));
            }
            roof = Integer.parseInt(reader.readLine());
            for (int i = 0; i < roof; i++) {
                String line2 = reader.readLine();
                String[] allInfo = line2.split(" ");
                NodeType nodeType = NodeType.HIDDEN;
                switch (allInfo[0].charAt(0)) {
                    case 'O':
                        nodeType = NodeType.OUTPUT;
                        break;
                    case 'I':
                        nodeType = NodeType.INPUT;
                        break;
                }
                nodeMap.put(i, new Node(Double.parseDouble(allInfo[2]), nodeType, Double.parseDouble(allInfo[1])));
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ChaoticCyclicGenome(nodeMap, connections);
    }
}
