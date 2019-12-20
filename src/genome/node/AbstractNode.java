package genome.node;

import java.util.HashMap;

class AbstractNode<T> {
    //To be changed later: Due to Variable Data
    final private T data;
    final HashMap<AbstractNode, Double> connections = new HashMap<>();

    AbstractNode(T data) {
        this.data = data;
    }

    @SuppressWarnings("unused")
    public T getData() {
        return data;
    }
}
