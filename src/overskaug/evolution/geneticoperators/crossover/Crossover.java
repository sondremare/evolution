package overskaug.evolution.geneticoperators.crossover;

import overskaug.evolution.geneticoperators.UnsupportedGeneticOperationException;
import overskaug.evolution.genotypes.Genotype;

import java.util.ArrayList;

public interface Crossover {
    abstract public ArrayList<Genotype> onePointCrossover(Genotype parent1, Genotype parent2, double crossoverRate) throws UnsupportedGeneticOperationException;
}
