package algorithm.genome;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        if (o1.getID() == o2.getID()){
            return 0;
        }
        else{
            return 1;
        }
    }
}
