package overskaug.evolution;

import java.util.ArrayList;

public class Population {

    private ArrayList<Individual> adults = new ArrayList<Individual>();
    private ArrayList<Individual> children = new ArrayList<Individual>();
    private int populationSize;

    public Population(int populationSize, int bitLength) {
        this.populationSize = populationSize;
        for (int i = 0; i < populationSize; i++) {
            children.add(new Individual(bitLength));
        }
    }

    public ArrayList<Individual> getAdults() {
        return adults;
    }

    public void setAdults(ArrayList<Individual> adults) {
        this.adults = adults;
    }

    public ArrayList<Individual> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Individual> children) {
        this.children = children;
    }

    public void clearChildren() {
        this.children = new ArrayList<Individual>();
    }
}
