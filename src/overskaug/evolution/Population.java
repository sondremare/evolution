package overskaug.evolution;

import overskaug.evolution.genotypes.Genotype;

import java.util.ArrayList;

public class Population {
    private ArrayList<Individual> individuals = new ArrayList<Individual>();
    private int populationSize;

    public Population(int populationSize) {
        this.populationSize = populationSize;
        for (int i = 0; i < populationSize; i++) {
            individuals.add(new Individual());
        }
    }

    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }
}
