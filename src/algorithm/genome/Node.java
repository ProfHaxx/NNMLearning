package algorithm.genome;

public class Node{

    /*
    * I had to do some changes
    * It is more efficient to use IDs in Map as keys to those Nodes, so they can be easily found with connection fields
    *
    * Q is a number that is used during calculations alike weight by connections
    * Value is a number that stores current value that moves through the genome
    * */

    private double value, Q;
    private NodeType type;

    public Node(double value, NodeType type, double Q){
        this.value = value;
        this.type = type;
        this.Q = Q;
    }

    public void setValue(double value) { this.value = value; }

    public NodeType getType() { return this.type; }

    public double getValue(){ return this.value; }

    public double getQ() { return Q; }

    public void setQ(double q) { Q = q; }

    public double calculateValue(int previousValue) {
        return previousValue + value;
    }

    public Node copyNode(){
        return new Node(this.value, this.type, this.Q);
    }

    @Override
    public String toString() {
        String ntype = "";
        switch(this.type) {
            case INPUT:
                ntype = "Input";
                break;
            case HIDDEN:
                ntype = "Hidden";
                break;
            case OUTPUT:
                ntype = "Output";
                break;
        }
        return ntype + ", Q number: " + this.Q + " , Value = " + this.value;
    }
}
