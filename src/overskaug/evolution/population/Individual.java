package overskaug.evolution.population;

import overskaug.evolution.genotypes.Genotype;
import overskaug.evolution.phenotypes.Phenotype;

public interface Individual {
    abstract Genotype getGenotype();
    abstract Phenotype getPhenotype();
    abstract double getFitness();
    abstract void setFitness(double fitness);
}
