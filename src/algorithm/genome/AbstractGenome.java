package algorithm.genome;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public abstract class AbstractGenome {
    private List<Connection> connections;
    private Map<Integer, Node>  Nodes;

    public AbstractGenome(Map<Integer, Node>  Nodes){
        this.connections = new ArrayList<>();
        this.Nodes = new HashMap<>();
    }

    public AbstractGenome(Map<Integer, Node> Nodes, List<Connection> connections){
        this.connections = connections;
        this.Nodes = Nodes;
    }

    public abstract void count();

    public abstract AbstractGenome copy();

    public List<Connection> getConnections() {
        return connections;
    }

    public Map<Integer,Node> getNodes() {
        return Nodes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" InputNodes: ");
        for(Node node: Nodes.values()){
            if(node.getType() == NodeType.INPUT) {
                sb.append(node.toString());
            }
        }
        sb.append(" || HiddenNodes: ");
        for(Node node: Nodes.values()){
            if(node.getType() == NodeType.HIDDEN) {
                sb.append(node.toString());
            }
        }
        sb.append(" || OutputNodes: ");
        for(Node node: Nodes.values()){
            if(node.getType() == NodeType.OUTPUT) {
                sb.append(node.toString());
            }
        }
        sb.append(" || Connections: ");
        for(Connection connection: connections){
            sb.append(connection.toString());
        }
        return sb.toString();
    }
}

