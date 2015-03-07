package overskaug.evolution.geneticoperators.crossover;

import overskaug.evolution.geneticoperators.UnsupportedGeneticOperationException;
import overskaug.evolution.population.Individual;

import java.util.ArrayList;

public interface Crossover {
    abstract public ArrayList<Individual> onePointCrossover(Individual parent1, Individual parent2, double crossoverRate) throws UnsupportedGeneticOperationException;
}
