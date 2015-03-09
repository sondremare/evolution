package overskaug.evolution.problems;

import overskaug.evolution.Evolution;
import overskaug.evolution.fitness.Fitness;
import overskaug.evolution.fitness.GlobalSequenceFitness;
import overskaug.evolution.fitness.LocalSequenceFitness;
import overskaug.evolution.geneticoperators.crossover.BitCrossover;
import overskaug.evolution.geneticoperators.crossover.Crossover;
import overskaug.evolution.geneticoperators.mutation.BitMutation;
import overskaug.evolution.geneticoperators.mutation.Mutation;
import overskaug.evolution.phenotypes.IntegerPhenotype;
import overskaug.evolution.population.BitVectorIndividual;
import overskaug.evolution.population.Population;
import overskaug.evolution.util.Converter;

import java.util.ArrayList;
import java.util.Random;

public class SurprisingSequences implements Problem {
    private GlobalSequenceFitness fitness;
    private BitCrossover crossover = new BitCrossover();
    private BitMutation mutation = new BitMutation();
    private Population population = new Population();
    private int symbolSetSize;
    private Enum adultSelection;
    private Enum parentSelection;

    public SurprisingSequences(int symbolSetSize, int sequenceLength, boolean global) {
        /*this.symbolSetSize = symbolSetSize;
        int bitsPerInteger = (int) Math.ceil(Math.log(symbolSetSize) / Math.log(2));
        int bitLength = bitsPerInteger * sequenceLength;
        Converter.setBitsPerInteger(bitsPerInteger);
        if (global) {
            this.fitness = new GlobalSequenceFitness();
        } else {
            this.fitness = new LocalSequenceFitness();
        }
        System.out.println(System.currentTimeMillis());
        Random random = new Random();
        for (int i = 0; i < Evolution.MAXIMUM_POOL_SIZE; i++) {
            ArrayList<Integer> integers = new ArrayList<Integer>();
            for (int j = 0; j < sequenceLength; j++) {
                int randomInteger = random.nextInt(symbolSetSize);
                integers.add(randomInteger);
            }
            IntegerPhenotype integerPhenotype = new IntegerPhenotype(integers);
            population.addIndividual(new BitVectorIndividual(Converter.convertToGenotype(integerPhenotype)));

        }
        System.out.println(population.getChildren().size());
        /*int counter = 0;
        while (population.getChildren().size() < Evolution.MAXIMUM_POOL_SIZE) {
            BitVectorIndividual child = new BitVectorIndividual(bitLength);
            if (validPhenotype(child.getPhenotype())) {
                population.addIndividual(child);
            }
            counter++;
            System.out.println(counter + " - " + population.getChildren().size());
        }*/
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
