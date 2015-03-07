package overskaug.evolution.geneticoperators.crossover;

import overskaug.evolution.Individual;
import overskaug.evolution.geneticoperators.UnsupportedGeneticOperationException;
import overskaug.evolution.genotypes.Genotype;

import java.util.ArrayList;

public interface Crossover {
    abstract public ArrayList<Individual> onePointCrossover(Individual parent1, Individual parent2, double crossoverRate) throws UnsupportedGeneticOperationException;
}
