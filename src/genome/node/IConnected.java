package genome.node;

import java.util.HashMap;

public interface IConnected {
    void connect(AbstractNode node, double weight);

    int connectionCount();

    HashMap getConnections();

    default double angle() {
        return 180.0/(connectionCount()+1);
    }
}
