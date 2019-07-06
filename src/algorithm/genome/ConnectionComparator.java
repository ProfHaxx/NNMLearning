package algorithm.genome;

import java.util.Comparator;

public class ConnectionComparator implements Comparator<Connection> {
    @Override
    public int compare(Connection o1, Connection o2) {
        if((o1.getInputNodeID() == o2.getInputNodeID()) && (o1.getOutputNodeID() == o2.getOutputNodeID())){
            return 0;
        }
        else {
            return 1;
        }
    }
}
