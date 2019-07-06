package algorithm.genome;

public class Connection{
    private int InputNodeID, OutputNodeID;
    private double weight;

    public Connection(double weight, int InputNodeID, int OutputNodeID){
        this.InputNodeID = InputNodeID;
        this.OutputNodeID = OutputNodeID;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int getInputNodeID() {
        return InputNodeID;
    }

    public int getOutputNodeID() {
        return OutputNodeID;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Connection copy(){
        return this;
    }

    @Override
    public String toString() {
        return "InputNodeID: " + InputNodeID + "  OutputNodeID: " + OutputNodeID + "  Weight: " + weight;
    }
}
