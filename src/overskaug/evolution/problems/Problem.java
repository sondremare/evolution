package overskaug.evolution.problems;

import overskaug.evolution.phenotypes.Phenotype;
import overskaug.evolution.population.Population;
import overskaug.evolution.fitness.Fitness;
import overskaug.evolution.geneticoperators.crossover.Crossover;
import overskaug.evolution.geneticoperators.mutation.Mutation;

public interface Problem {

    abstract Population getPopulation();
    abstract Fitness getFitness();
    abstract Crossover getCrossover();
    abstract Mutation getMutation();
    abstract boolean isValidPhenotype(Phenotype phenotype);
}
