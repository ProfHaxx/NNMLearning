package algorithm.genome;

import java.util.*;

public class Genome {
    private List<Connection> connections = new ArrayList<>();
    private List<Node> nodes = new ArrayList<>();

    public Genome(List<Connection> connections, List<Node> nodes){
        this.connections = connections;
        this.nodes = nodes;
    }


}
