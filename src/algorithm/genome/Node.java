package algorithm.genome;

public class Node{
    private double value;
    private int ID;
    private NodeType type;

    public Node(double value, int ID, NodeType type){
        this.value = value;
        this.ID = ID;
        this.type = type;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public NodeType getType() {
        return this.type;
    }

    public double getValue(){
        return this.value;
    }

    public int getID(){
        return this.ID;
    }

    public double calculateValue(int previousValue) {
        return previousValue + value;
    }

    public Node copyNode(){
        return this;
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
        return ntype + " Node ID: " + this.ID + ", Value = " + this.value;
    }
}
