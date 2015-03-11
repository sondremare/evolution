package overskaug.evolution;

import javafx.scene.chart.XYChart;
import overskaug.evolution.fitness.Fitness;
import overskaug.evolution.geneticoperators.UnsupportedGeneticOperationException;
import overskaug.evolution.geneticoperators.crossover.Crossover;
import overskaug.evolution.geneticoperators.mutation.Mutation;
import overskaug.evolution.phenotypes.UnsupportedPhenotypeException;
import overskaug.evolution.population.Individual;
import overskaug.evolution.population.Population;
import overskaug.evolution.problems.Problem;
import overskaug.evolution.selection.AdultSelection;
import overskaug.evolution.selection.ParentPool;
import overskaug.evolution.selection.ParentSelection;
import overskaug.evolution.selection.RouletteWheel;
import overskaug.evolution.util.FitnessCalculations;

import java.util.ArrayList;

public class Evolution {
    public static int MAXIMUM_POOL_SIZE = 100;
    public static int REPRODUCTION_POOL_SIZE = 100;
    public static int MAX_GENERATIONS = 100;
    public static double CROSSOVER_RATE = 1;
    public static double MUTATION_RATE = 0.05;
    public static double TEMPERATURE = 5;
    public static double TEMPERATURE_DECREASE = 0.01;
    public static int TOURNAMENT_GROUP_SIZE = 10;
    public static double TOURNAMENT_RANDOM_PROBABILITY = 0.05;

    public static AdultSelection.AdultSelectionEnum adultSelectionType = AdultSelection.AdultSelectionEnum.FULL_GENERATIONAL_REPLACEMENT;
    public static ParentSelection.ParentSelectionEnum parentSelectionType = ParentSelection.ParentSelectionEnum.FITNESS_PROPORTIONATE;
    public static Mutation.MutationEnum mutationType = Mutation.MutationEnum.GENOME_MUTATION;

    //Workaround for dynamic plotting
    public static ArrayList<XYChart.Series> bestFitnessSeriesList = new ArrayList<XYChart.Series>();
    public static ArrayList<XYChart.Series> averageFitnessSeriesList = new ArrayList<XYChart.Series>();
    public static ArrayList<XYChart.Series> standardDeviationFitnessSeriesList = new ArrayList<XYChart.Series>();

    public static void run(Problem problem) throws Exception {
        Population population = problem.getPopulation();
        Fitness fitness = problem.getFitness();
        int generations = 0;
        while (generations < MAX_GENERATIONS && fitness.getOptimalFitness() > FitnessCalculations.getBestFitness(population.getAdults())) {
            for (Individual individual : population.getChildren()) {
                try {
                    individual.setFitness(fitness.calculateFitness(individual.getPhenotype()));
                } catch (UnsupportedPhenotypeException e) {
                    System.err.println(e.getMessage());
                }
            }
            if (adultSelectionType.equals(AdultSelection.AdultSelectionEnum.FULL_GENERATIONAL_REPLACEMENT)) {
                population.setAdults(AdultSelection.fullGenerationalReplacement(population.getAdults(), population.getChildren()));
            } else if (adultSelectionType.equals(AdultSelection.AdultSelectionEnum.OVER_PRODUCTION)) {
                population.setAdults(AdultSelection.overProduction(population.getAdults(), population.getChildren(), MAXIMUM_POOL_SIZE));
            } else if (adultSelectionType.equals(AdultSelection.AdultSelectionEnum.GENERATIONAL_MIXING)) {
                population.setAdults(AdultSelection.generationalMixing(population.getAdults(), population.getChildren(), MAXIMUM_POOL_SIZE));
            } else {
                throw new Exception("No valid adult selection method is selected");
            }
            population.clearChildren();

            ParentPool parentPool = null;
            if (parentSelectionType.equals(ParentSelection.ParentSelectionEnum.FITNESS_PROPORTIONATE)) {
                parentPool = ParentSelection.fitnessProportionate(population.getAdults());
            } else if (parentSelectionType.equals(ParentSelection.ParentSelectionEnum.SIGMA_SCALING)) {
                parentPool = ParentSelection.sigmaScaling(population.getAdults());
            } else if (parentSelectionType.equals(ParentSelection.ParentSelectionEnum.TOURNAMENT_SELECTION)) {
                parentPool = ParentSelection.tournamentSelection(population.getAdults(), TOURNAMENT_GROUP_SIZE, TOURNAMENT_RANDOM_PROBABILITY);
            } else if (parentSelectionType.equals(ParentSelection.ParentSelectionEnum.BOLTZMANN_SELECTION)) {
                parentPool = ParentSelection.boltzmannSelection(population.getAdults(), TEMPERATURE - (TEMPERATURE_DECREASE * generations));
            }

            Crossover crossover = problem.getCrossover();
            while (population.getChildren().size() < REPRODUCTION_POOL_SIZE) {
                Individual parent1 = parentPool.nextParent();
                Individual parent2 = parentPool.nextParent();
                try {
                    ArrayList<Individual> children = crossover.onePointCrossover(parent1, parent2, CROSSOVER_RATE);
                    Mutation mutation = problem.getMutation();
                    for (Individual child : children) {
                        if (mutationType.equals(Mutation.MutationEnum.GENOME_MUTATION)) {
                            mutation.mutate(child.getGenotype(), MUTATION_RATE);
                        } else if (mutationType.equals(Mutation.MutationEnum.PER_GENE_MUTATION)) {
                            mutation.mutateAllComponents(child.getGenotype(), MUTATION_RATE);
                        }
                        if (problem.isValidPhenotype(child.getPhenotype()) && population.getChildren().size() != REPRODUCTION_POOL_SIZE) {
                            population.getChildren().add(child);
                        }
                    }
                } catch (UnsupportedGeneticOperationException e) {
                    System.err.println(e.getMessage());
                }
            }
            //Workaround for dynamic plotting
            int series = Evolution.bestFitnessSeriesList.size() - 1;
            Evolution.bestFitnessSeriesList.get(series).getData().add(new XYChart.Data(generations, FitnessCalculations.getBestFitness(population.getAdults())));
            Evolution.averageFitnessSeriesList.get(series).getData().add(new XYChart.Data(generations, FitnessCalculations.getFitnessAverage(population.getAdults())));
            Evolution.standardDeviationFitnessSeriesList.get(series).getData().add(new XYChart.Data(generations, FitnessCalculations.getStandardDeviation(population.getAdults())));

            System.out.println("*********************** generation: "+ generations +" **********************");
            System.out.println("Best fitness: " + FitnessCalculations.getBestFitness(population.getAdults()));
            System.out.println("Average fitness: "+ FitnessCalculations.getFitnessAverage(population.getAdults()));
            System.out.println("Standard devitation: "+ FitnessCalculations.getFitnessAverage(population.getAdults()));
            System.out.println("Best phenotype: "+FitnessCalculations.getBestIndividual(population.getAdults()).getPhenotype());

            generations++;
        }
    }
}
