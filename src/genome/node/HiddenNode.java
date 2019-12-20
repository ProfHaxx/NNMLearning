package genome.node;

import exception.InvalidConnectionException;

import java.util.HashMap;

public class HiddenNode<T> extends AbstractNode implements IConnected {
    @SuppressWarnings({"unused", "unchecked"})
    HiddenNode(T data) {
        super(data);
    }

    @SuppressWarnings({"unchecked", "unused"})
    public void connect(AbstractNode node, double weight) {
        if(!(node instanceof InputNode)) {
            this.connections.put(node, weight);
        } else {
            throw new InvalidConnectionException();
        }
    }

    @Override
    public int connectionCount() {
        return this.connections.size();
    }

    @Override
    public HashMap getConnections() {
        return this.connections;
    }
}
