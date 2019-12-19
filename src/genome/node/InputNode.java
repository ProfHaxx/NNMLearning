package genome.node;

public class InputNode<T> extends AbstractNode implements IConnected{
    @SuppressWarnings("unchecked")
    InputNode(T data) {
        super(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void connect(AbstractNode node, double weight) {
        connections.put(node, weight);
    }

    @Override
    public int connectionCount() {
        return this.connections.size();
    }
}
