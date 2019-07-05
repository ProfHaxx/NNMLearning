package algorithm.genome;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGenome {
    private List<Connection> connections;
    private List<Node> HiddenNodes;
    private Node[] InputNodes;
    private Node[] OutputNodes;

    public AbstractGenome(Node[] InputNodes, Node[] OutputNodes){
        this.connections = new ArrayList<>();
        this.HiddenNodes = new ArrayList<>();
        this.InputNodes = InputNodes;
        this.OutputNodes = OutputNodes;
    }

    public AbstractGenome(List<Node> HiddenNodes, List<Connection> connections, Node[] InputNodes, Node[] OutputNodes){
        this.connections = connections;
        this.HiddenNodes = HiddenNodes;
        this.InputNodes = InputNodes;
        this.OutputNodes = OutputNodes;
    }

    public abstract double count(int NodeID);

    public abstract Genome copy();

    public void addConnection(Connection newConnection){
        connections.add(newConnection);
    }

    public void addHiddenNode(Node newNode){
        HiddenNodes.add(newNode);
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public List<Node> getHiddenNodes() {
        return HiddenNodes;
    }

    public Node[] getInputNodes() {
        return InputNodes;
    }

    public Node[] getOutputNodes() {
        return OutputNodes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" InputNodes: ");
        for(Node node: InputNodes){
            sb.append(node.toString());
        }
        sb.append(" || HiddenNodes: ");
        for(Node node: HiddenNodes){
            sb.append(node.toString());
        }
        sb.append(" || OutputNodes: ");
        for(Node node: OutputNodes){
            sb.append(node.toString());
        }
        sb.append(" || Connections: ");
        for(Connection connection: connections){
            sb.append(connection.toString());
        }
        return sb.toString();
    }
}

