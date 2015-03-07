package overskaug.evolution.problems;

import overskaug.evolution.population.Population;
import overskaug.evolution.fitness.Fitness;
import overskaug.evolution.geneticoperators.crossover.Crossover;
import overskaug.evolution.geneticoperators.mutation.Mutation;
import overskaug.evolution.genotypes.Genotype;

public interface Problem {
    abstract Population getPopulation();
    abstract Genotype getSolution();
    abstract Fitness getFitness();
    abstract Crossover getCrossover();
    abstract Mutation getMutation();
}
