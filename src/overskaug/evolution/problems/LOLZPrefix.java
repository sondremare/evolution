package overskaug.evolution.problems;

import overskaug.evolution.Evolution;
import overskaug.evolution.fitness.Fitness;
import overskaug.evolution.fitness.LeadingNumbersFitness;
import overskaug.evolution.geneticoperators.crossover.BitCrossover;
import overskaug.evolution.geneticoperators.crossover.Crossover;
import overskaug.evolution.geneticoperators.mutation.BitMutation;
import overskaug.evolution.geneticoperators.mutation.Mutation;
import overskaug.evolution.population.BitVectorIndividual;
import overskaug.evolution.population.Population;

public class LOLZPrefix implements Problem {
    private LeadingNumbersFitness fitness;
    private BitCrossover crossover = new BitCrossover();
    private BitMutation mutation = new BitMutation();
    private Population population = new Population();

    public LOLZPrefix(int bitLength, int threshold) {
        this.fitness = new LeadingNumbersFitness(threshold);
        for (int i = 0; i < Evolution.MAXIMUM_POOL_SIZE; i++) {
            population.addIndividual(new BitVectorIndividual(bitLength));
        }
    }

    @Override
    public Population getPopulation() {
        return population;
    }

    @Override
    public Fitness getFitness() {
        return fitness;
    }

    @Override
    public Crossover getCrossover() {
        return crossover;
    }

    @Override
    public Mutation getMutation() {
        return mutation;
    }
}
