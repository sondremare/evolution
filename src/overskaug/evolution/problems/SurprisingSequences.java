package overskaug.evolution.problems;

import overskaug.evolution.Evolution;
import overskaug.evolution.fitness.Fitness;
import overskaug.evolution.fitness.GlobalSequenceFitness;
import overskaug.evolution.fitness.LocalSequenceFitness;
import overskaug.evolution.geneticoperators.crossover.BitCrossover;
import overskaug.evolution.geneticoperators.crossover.Crossover;
import overskaug.evolution.geneticoperators.crossover.IntegerCrossover;
import overskaug.evolution.geneticoperators.mutation.BitMutation;
import overskaug.evolution.geneticoperators.mutation.IntegerMutation;
import overskaug.evolution.geneticoperators.mutation.Mutation;
import overskaug.evolution.genotypes.Genotype;
import overskaug.evolution.genotypes.IntegerGenotype;
import overskaug.evolution.phenotypes.IntegerPhenotype;
import overskaug.evolution.phenotypes.Phenotype;
import overskaug.evolution.population.BitVectorIndividual;
import overskaug.evolution.population.IntegerIndividual;
import overskaug.evolution.population.Population;
import overskaug.evolution.util.Converter;

import java.util.ArrayList;
import java.util.Random;

public class SurprisingSequences implements Problem {
    private GlobalSequenceFitness fitness;
    private IntegerCrossover crossover = new IntegerCrossover();
    private IntegerMutation mutation = new IntegerMutation();
    private Population population = new Population();
    private int symbolSetSize;

    public SurprisingSequences(int symbolSetSize, int sequenceLength, boolean global) {
        this.symbolSetSize = symbolSetSize;
        if (global) {
            this.fitness = new GlobalSequenceFitness();
        } else {
            this.fitness = new LocalSequenceFitness();
        }
        Random random = new Random();

        while (population.getChildren().size() < Evolution.MAXIMUM_POOL_SIZE) {
            ArrayList<Integer> integers = new ArrayList<Integer>();
            for (int j = 0; j < sequenceLength; j++) {
                int randomInteger = random.nextInt(symbolSetSize);
                integers.add(randomInteger);
            }
            IntegerGenotype integerGenotype = new IntegerGenotype(integers);
            IntegerIndividual individual = new IntegerIndividual(integerGenotype);
            if (isValidPhenotype(individual.getPhenotype())) {
                population.addIndividual(individual);
            }
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

    @Override
    public boolean isValidPhenotype(Phenotype phenotype) {
        IntegerPhenotype integerPhenotype = (IntegerPhenotype) phenotype;
        ArrayList<Integer> integers = integerPhenotype.getPhenotype();
        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) >= symbolSetSize || integers.get(i) < 0) {
                return false;
            }
        }
        return true;
    }
}
