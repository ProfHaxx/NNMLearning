package genome.node;

public class HiddenNode<T> extends AbstractNode implements IConnected {
    @SuppressWarnings("unchecked")
    HiddenNode(T data) {
        super(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void connect(AbstractNode node, double weight) {
        this.connections.put(node, weight);
    }

    @Override
    public int connectionCount() {
        return this.connections.size();
    }
}
