package overskaug.evolution;

import overskaug.evolution.fitness.Fitness;
import overskaug.evolution.geneticoperators.UnsupportedGeneticOperationException;
import overskaug.evolution.geneticoperators.crossover.Crossover;
import overskaug.evolution.geneticoperators.mutation.Mutation;
import overskaug.evolution.population.Individual;
import overskaug.evolution.population.Population;
import overskaug.evolution.problems.Problem;
import overskaug.evolution.selection.AdultSelection;
import overskaug.evolution.selection.ParentSelection;
import overskaug.evolution.selection.RouletteWheel;

import java.util.ArrayList;

public class Evolution {
    public static int MAXIMUM_POOL_SIZE = 20;
    public static int BIT_LENGTH = 40;
    public static int MAX_GENERATIONS = 1500;
    public static double CROSSOVER_RATE = 1;
    public static double MUTATION_CHANCE = 0.05;

    public static void run(Problem problem) {
        Population population = problem.getPopulation();
        Fitness fitness = problem.getFitness();
        int generations = 0;
        while (generations < MAX_GENERATIONS && fitness.getThreshold() > getBestFitness(population.getAdults())) {
            for (Individual individual : population.getChildren()) {
                individual.setFitness(fitness.calculateFitness(individual.getPhenotype()));
            }
            population.setAdults(AdultSelection.fullGenerationalReplacement(population.getAdults(), population.getChildren()));
            population.clearChildren();

            RouletteWheel rouletteWheel = ParentSelection.fitnessProportionate(population.getAdults());
            //TODO add other selections
            Crossover crossover = problem.getCrossover();

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

            //Mutation
            Mutation mutation = problem.getMutation();
            for (Individual child : population.getChildren()) {
                try {
                    mutation.mutate(child.getGenotype(), MUTATION_CHANCE);
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
