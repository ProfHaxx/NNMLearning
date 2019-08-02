package algorithm.genome;

import java.io.*;
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

    public void save(String name){
        try {
            File file = new File("./savedGenomes/files/" + name + ".txt");
            OutputStream outputStream = new FileOutputStream("./savedGenomes/files/" + name + ".txt");
            String lineSeparator = System.getProperty("line.separator");
            //outputStream.write("Connections: ".getBytes());
            outputStream.write(Long.toString(connections.size()).getBytes());
            outputStream.write(lineSeparator.getBytes());
            for(Connection connection: connections){
                outputStream.write(connection.toStringSmall().getBytes());
                outputStream.write(lineSeparator.getBytes());
            }
            //outputStream.write("Nodes: ".getBytes());
            outputStream.write(Long.toString(Nodes.size()).getBytes());
            outputStream.write(lineSeparator.getBytes());
            for(Node node: Nodes.values()){
                outputStream.write(node.toStringSmall().getBytes());
                outputStream.write(lineSeparator.getBytes());
            }
        }
        catch (IOException ioe){
            System.out.print("fail");
        }
    }

    public void toJson(String name){
        try {
            File file = new File("./savedGenomes/files/" + name + ".json");
            OutputStream outputStream = new FileOutputStream("./savedGenomes/files/" + name + ".json");
            String lineSeparator = System.getProperty("line.separator");
            //opening NodeGenes
            outputStream.write("{".getBytes());
            outputStream.write(lineSeparator.getBytes());
            outputStream.write("    \"NodeGenes\": [".getBytes());
            outputStream.write(lineSeparator.getBytes());
            //all NodeGenes
            int nodeCounter = 0;
            for(int nodeID: this.Nodes.keySet()){
                outputStream.write(("        {").getBytes());
                outputStream.write(lineSeparator.getBytes());
                outputStream.write(("           \"NodeType\": \"" + this.Nodes.get(nodeID).getType() + "\" ,").getBytes());
                outputStream.write(lineSeparator.getBytes());
                outputStream.write(("           \"NodeID\": " + nodeID + " ,").getBytes());
                outputStream.write(lineSeparator.getBytes());
                outputStream.write(("           \"QNumber\": " + this.Nodes.get(nodeID).getQ() + " ,").getBytes());
                outputStream.write(lineSeparator.getBytes());
                //all "in" and "out" connections
                //Storing all of them
                List<Connection> inConnections = new ArrayList<>(), outConnections = new ArrayList<>();
                for(Connection connection: this.connections){
                    if(connection.getOutputNodeID() == nodeID){
                        outConnections.add(connection);
                    }
                    else if(connection.getInputNodeID() == nodeID){
                        inConnections.add(connection);
                    }
                }
                //Listing all input connection
                int inCounter = 0;
                outputStream.write(("           \"inputs\": [").getBytes());
                outputStream.write(lineSeparator.getBytes());
                for(Connection inConnection: inConnections){
                    outputStream.write(("               {").getBytes());
                    outputStream.write(lineSeparator.getBytes());
                    outputStream.write(("                   \"ID\": " + connections.indexOf(inConnection) + " ,").getBytes());
                    outputStream.write(lineSeparator.getBytes());
                    outputStream.write(("                   \"weight\": " + inConnection.getWeight()).getBytes());
                    outputStream.write(lineSeparator.getBytes());
                    outputStream.write(("               }").getBytes());
                    if (inCounter + 1 != inConnections.size()){
                        outputStream.write(",".getBytes());
                    }
                    inCounter++;
                    outputStream.write(lineSeparator.getBytes());
                }
                outputStream.write(("           ],").getBytes());
                outputStream.write(lineSeparator.getBytes());
                //Listing all output connection
                int outCounter = 0;
                outputStream.write(("           \"outputs\": [").getBytes());
                outputStream.write(lineSeparator.getBytes());
                for(Connection inConnection: outConnections){
                    outputStream.write(("               {").getBytes());
                    outputStream.write(lineSeparator.getBytes());
                    outputStream.write(("                   \"ID\": " + connections.indexOf(inConnection) + " ,").getBytes());
                    outputStream.write(lineSeparator.getBytes());
                    outputStream.write(("                   \"weight\": " + inConnection.getWeight()).getBytes());
                    outputStream.write(lineSeparator.getBytes());
                    outputStream.write(("               }").getBytes());
                    if (outCounter + 1 != outConnections.size()){
                        outputStream.write(",".getBytes());
                    }
                    outCounter++;
                    outputStream.write(lineSeparator.getBytes());
                }
                outputStream.write(("           ]").getBytes());
                outputStream.write(lineSeparator.getBytes());

                outputStream.write(("        }").getBytes());
                if (nodeCounter + 1 != this.Nodes.size()){
                    outputStream.write(",".getBytes());
                }
                nodeCounter++;
                outputStream.write(lineSeparator.getBytes());
            }
            //Closing NodeGenes
            outputStream.write("    ]".getBytes());
            outputStream.write(lineSeparator.getBytes());
            outputStream.write("}".getBytes());
            outputStream.write(lineSeparator.getBytes());
        }
        catch (IOException ioe){
            System.out.print("fail");
        }
    }
}

