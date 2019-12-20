package genome.node;

import java.util.HashMap;

interface IConnected {
    int connectionCount();

    @SuppressWarnings("unused")
    HashMap getConnections();

    @SuppressWarnings("unused")
    default double angle() {
        return 180.0/(connectionCount()+1);
    }
}
