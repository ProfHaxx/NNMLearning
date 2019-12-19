package genome.node;

public interface IConnected {
    void connect(AbstractNode node, double weight);

    int connectionCount();

    default double angle() {
        return 180.0/(connectionCount()+1);
    }
}
