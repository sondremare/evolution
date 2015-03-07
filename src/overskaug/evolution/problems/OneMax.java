package overskaug.evolution.problems;

import overskaug.evolution.Evolution;
import overskaug.evolution.population.BitVectorIndividual;
import overskaug.evolution.population.Population;
import overskaug.evolution.fitness.Fitness;
import overskaug.evolution.fitness.SumOfSquaresFitness;
import overskaug.evolution.geneticoperators.crossover.BitCrossover;
import overskaug.evolution.geneticoperators.crossover.Crossover;
import overskaug.evolution.geneticoperators.mutation.BitMutation;
import overskaug.evolution.geneticoperators.mutation.Mutation;
import overskaug.evolution.genotypes.BitVectorGenotype;
import overskaug.evolution.genotypes.Genotype;
import overskaug.evolution.util.Converter;
import overskaug.evolution.util.FixedBitSet;

public class OneMax implements Problem {
    private BitVectorGenotype solution;
    private SumOfSquaresFitness fitness;
    private BitCrossover crossover = new BitCrossover();
    private BitMutation mutation = new BitMutation();
    private Population population = new Population();

    public OneMax(int bitLength) {
        FixedBitSet solutionBitSet = new FixedBitSet(bitLength);
        solutionBitSet.set(0, bitLength);
        this.solution = new BitVectorGenotype(solutionBitSet);
        this.fitness = new SumOfSquaresFitness(Converter.convertToPhenotype(solution));
        for (int i = 0; i < Evolution.MAXIMUM_POOL_SIZE; i++) {
            population.addIndividual(new BitVectorIndividual(Evolution.BIT_LENGTH));
        }
    }

    @Override
    public Population getPopulation() {
        return population;
    }

    @Override
    public Genotype getSolution() {
        return solution;
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
