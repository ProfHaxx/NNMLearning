package algorithm.genome;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public abstract class AbstractGenome {
    private List<Connection> connections;
    private Map<Integer, Node>  HiddenNodes;
    private Map<Integer, Node>  InputNodes;
    private Map<Integer, Node> OutputNodes;

    public AbstractGenome(Map<Integer, Node>  InputNodes, Map<Integer, Node>  OutputNodes){
        this.connections = new ArrayList<>();
        this.HiddenNodes = new HashMap<>();
        this.InputNodes = InputNodes;
        this.OutputNodes = OutputNodes;
    }

    public AbstractGenome(Map<Integer, Node> HiddenNodes, List<Connection> connections, Map<Integer, Node>  InputNodes, Map<Integer, Node>  OutputNodes){
        this.connections = connections;
        this.HiddenNodes = HiddenNodes;
        this.InputNodes = InputNodes;
        this.OutputNodes = OutputNodes;
    }

    public abstract void count();

    public abstract AbstractGenome copy();

    public List<Connection> getConnections() {
        return connections;
    }

    public Map<Integer,Node> getHiddenNodes() {
        return HiddenNodes;
    }

    public Map<Integer,Node> getInputNodes() {
        return InputNodes;
    }

    public Map<Integer,Node> getOutputNodes() {
        return OutputNodes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" InputNodes: ");
        for(Node node: InputNodes.values()){
            sb.append(node.toString());
        }
        sb.append(" || HiddenNodes: ");
        for(Node node: HiddenNodes.values()){
            sb.append(node.toString());
        }
        sb.append(" || OutputNodes: ");
        for(Node node: OutputNodes.values()){
            sb.append(node.toString());
        }
        sb.append(" || Connections: ");
        for(Connection connection: connections){
            sb.append(connection.toString());
        }
        return sb.toString();
    }
}

