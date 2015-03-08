package overskaug.evolution.fitness;

import overskaug.evolution.phenotypes.Phenotype;
import overskaug.evolution.phenotypes.UnsupportedPhenotypeException;

public interface Fitness {
    abstract double getOptimalFitness();
    abstract double calculateFitness(Phenotype phenotype) throws UnsupportedPhenotypeException;
}
