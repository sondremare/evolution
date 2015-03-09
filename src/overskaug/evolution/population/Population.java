package overskaug.evolution.population;

import java.util.ArrayList;

public class Population {

    private ArrayList<Individual> adults = new ArrayList<Individual>();
    private ArrayList<Individual> children = new ArrayList<Individual>();

    public void addIndividual(Individual individual) {
        children.add(individual);
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

    public void clearAdults() {
        this.adults = new ArrayList<Individual>();
    }

    public void clear() {
        clearChildren();
        clearAdults();
    }
}
