package algorithm.evolution;

import algorithm.genome.AbstractGenome;
import algorithm.genome.Connection;

import java.util.*;


public class Genome_comparator {

    /**
     * those factors are used to set preference and importance of different variables
     */

    public static final double Qfactor = 0.5;
    public static final double Weightfactor = 0.5;
    public static final double Connectionfactor = 0.5;
    public static final double Nodefactor = 0.5;


    public static double Genome_distance(AbstractGenome Genome1, AbstractGenome Genome2){

        double weightDistance = 0.0;
        double QDistance = 0.0;
        double ConnectionsDifference = 0.0;
        double NodesDifference = 0.0;

        /**
         * Nodes
         */

        Set<Integer> nodeSet = new LinkedHashSet<>();
        nodeSet.addAll(Genome1.getNodes().keySet());
        nodeSet.addAll(Genome2.getNodes().keySet());

        //this factor is used to suppress the difference of non overlapping nodes
        double overlappingQfactor = 0.3;

        for(int Integer: nodeSet){
            if(Genome1.getNodes().containsKey(Integer) && Genome2.getNodes().containsKey(Integer)){
                QDistance += Math.abs(Genome1.getNodes().get(Integer).getQ() - Genome2.getNodes().get(Integer).getQ());
            }
            else if (Genome1.getNodes().containsKey(Integer)){
                NodesDifference ++;
                QDistance += Genome1.getNodes().get(Integer).getQ() * overlappingQfactor;
            }
            else if (Genome2.getNodes().containsKey(Integer)){
                NodesDifference ++;
                QDistance += Genome2.getNodes().get(Integer).getQ() * overlappingQfactor;
            }
        }

        /**
         * Connections
        */

        Set<Connection> connectionSet = new LinkedHashSet<>();

        //this factor is used to suppress the difference of non overlapping connection
        double overlappingWeightfactor = 0.3;

        for(int i = 0; i < Genome1.getConnections().size(); i++){
            if(Genome2.getConnections().contains(Genome1.getConnections().get(i))){
                weightDistance += Math.abs(Genome1.getConnections().get(i).getWeight() - Genome2.getConnections().get(i).getWeight());
            }
            else{
                weightDistance += Genome1.getConnections().get(i).getWeight() * overlappingWeightfactor;
                ConnectionsDifference ++;
            }
        }
        for(int i = 0; i < Genome2.getConnections().size(); i++){
            if(connectionSet.add(Genome2.getConnections().get(i))){
                weightDistance += Genome2.getConnections().get(i).getWeight() * overlappingWeightfactor;
                ConnectionsDifference ++;
            }
        }

        /**
         *
         */

        return (weightDistance * Weightfactor + QDistance * Qfactor + ConnectionsDifference * Connectionfactor + NodesDifference * Nodefactor);
    }
}
