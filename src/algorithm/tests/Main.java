package algorithm.tests;

import algorithm.genome.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Timer.startTimer();
        Map<Integer, Node> nodeMap = new HashMap<>();
        nodeMap.put(0, new Node(0, NodeType.INPUT, 0));
        nodeMap.put(1, new Node(0, NodeType.INPUT, 2));
        nodeMap.put(2, new Node(0, NodeType.OUTPUT, 1));
        nodeMap.put(3, new Node(0, NodeType.HIDDEN, 3));
        List<Connection> connections = new ArrayList<>();
        connections.add(new Connection(1,0,2));
        connections.add(new Connection(1,1,2));
        connections.add(new Connection(1,1,3));
        connections.add(new Connection(1,2,3));
        ChaoticCyclicGenome chaoticCyclicGenome = new ChaoticCyclicGenome(nodeMap, connections);
        chaoticCyclicGenome.save("Test_1.txt");
        Timer.addEvent("Test_1 Genome saved");
        ChaoticCyclicGenome c2 = ChaoticCyclicGenome.read("Test.txt");
        Timer.addEvent("Test_1 Genome downloaded");
        c2.save("Test_2.txt");
        Timer.addEvent("Test_2 Genome saved");
    }
}
