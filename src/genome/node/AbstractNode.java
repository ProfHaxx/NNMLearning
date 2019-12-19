package genome.node;

import java.util.HashMap;

public class AbstractNode<T> {
    private T data;
    HashMap<AbstractNode, Double> connections = new HashMap<>();

    AbstractNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
