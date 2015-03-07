package overskaug.evolution.fitness;

import overskaug.evolution.phenotypes.Phenotype;

public interface Fitness {
    abstract double getThreshold();
    abstract double calculateFitness(Phenotype phenotype);
}
