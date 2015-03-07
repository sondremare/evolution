package overskaug.evolution;

import overskaug.evolution.fitness.SumIntegerDiffFitness;
import overskaug.evolution.fitness.SumOfSquaresFitness;
import overskaug.evolution.geneticoperators.UnsupportedGeneticOperationException;
import overskaug.evolution.geneticoperators.crossover.BitCrossover;
import overskaug.evolution.geneticoperators.mutation.BitMutation;
import overskaug.evolution.genotypes.BitVectorGenotype;
import overskaug.evolution.selection.AdultSelection;
import overskaug.evolution.selection.ParentSelection;
import overskaug.evolution.selection.RouletteWheel;
import overskaug.evolution.util.Converter;
import overskaug.evolution.util.FixedBitSet;

import java.util.ArrayList;

public class Evolution {
    public static int MAXIMUM_POOL_SIZE = 20;
    public static int BIT_LENGTH = 20;
    public static int MAX_GENERATIONS = 500;
    public static double CROSSOVER_RATE = 1;
    public static double MUTATION_CHANCE = 0.05;

    public static void run() {
        Population population = new Population(MAXIMUM_POOL_SIZE, BIT_LENGTH);
        FixedBitSet solutionBitSet = new FixedBitSet(BIT_LENGTH);
        solutionBitSet.set(0, BIT_LENGTH);
        BitVectorGenotype solution = new BitVectorGenotype(solutionBitSet);
        SumOfSquaresFitness fitness = new SumOfSquaresFitness(Converter.convertToPhenotype(solution, 3)); //TODO FIX 3
        int generations = 0;
        while (generations < MAX_GENERATIONS && fitness.getThreshold() > getBestFitness(population.getAdults())) {
            for (Individual individual : population.getChildren()) {
                individual.setFitness(fitness.calculateFitness(individual.getPhenotype()));
            }
            population.setAdults(AdultSelection.fullGenerationalReplacement(population.getAdults(), population.getChildren()));
            population.clearChildren();

            RouletteWheel rouletteWheel = ParentSelection.fitnessProportionate(population.getAdults());
            //TODO add other selections
            BitCrossover crossover = new BitCrossover();


            //Sexual reproduction
            for (int i = 0; i < (double)MAXIMUM_POOL_SIZE / 2; i++) {
                Individual parent1 = rouletteWheel.nextParent();
                Individual parent2 = rouletteWheel.nextParent();
                try {
                    ArrayList<Individual> children = crossover.onePointCrossover(parent1, parent2, CROSSOVER_RATE);
                    population.getChildren().addAll(children);
                } catch (UnsupportedGeneticOperationException e) {
                    System.err.println(e.getMessage());
                }
            }

            //Asexual reproduction
            for (int j = 0; j < MAXIMUM_POOL_SIZE / 2; j++) {

            }

            //Mutation
            BitMutation bitMutation = new BitMutation();
            for (Individual child : population.getChildren()) {
                try {
                    bitMutation.mutate(child.getGenotype(), MUTATION_CHANCE);
                } catch (UnsupportedGeneticOperationException e) {
                    System.err.println(e.getMessage());
                }
            }
            generations++;
        }
        System.out.println("generations: "+generations);
        System.out.println("DONE");


    }

    public static double getBestFitness(ArrayList<Individual> individuals) {
        if (individuals != null && individuals.size() != 0) {
            return AdultSelection.getBestIndividual(individuals).getFitness();
        }
        return Double.MIN_VALUE;
    }
}
