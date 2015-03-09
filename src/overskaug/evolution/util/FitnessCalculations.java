package overskaug.evolution.util;

import overskaug.evolution.population.Individual;
import overskaug.evolution.population.IndividualComparator;

import java.util.ArrayList;
import java.util.Collections;

public class FitnessCalculations
{
    public static double getFitnessSum(ArrayList<Individual> individuals) {
        double sum = 0;
        for (Individual individual : individuals) {
            sum += individual.getFitness();
        }
        return sum;
    }

    public static double getFitnessAverage(ArrayList<Individual> individuals) {
        double sum = getFitnessSum(individuals);
        return sum / individuals.size();
    }

    private static double getVariance(ArrayList<Individual> individuals) {
        double fitnessAverage = getFitnessAverage(individuals);
        double sumOfSquares = 0;
        for (Individual individual : individuals) {
            sumOfSquares += Math.pow(individual.getFitness() - fitnessAverage, 2);
        }
        return sumOfSquares / (individuals.size() - 1);
    }

    public static double getStandardDeviation(ArrayList<Individual> individuals) {
        return Math.sqrt(getVariance(individuals));
    }

    public static double getBestFitness(ArrayList<Individual> individuals) {
        if (individuals != null && individuals.size() != 0) {
            return getBestIndividual(individuals).getFitness();
        }
        return Double.MIN_VALUE;
    }

    public static ArrayList<Individual> getNBestIndividuals(ArrayList<Individual> individuals, int n) {
        Collections.sort(individuals, new IndividualComparator());
        ArrayList<Individual> bestIndividuals = new ArrayList<Individual>();
        for (int i = 0; i < n; i++) {
            bestIndividuals.add(individuals.get(i));
        }
        return bestIndividuals;
    }

    public static Individual getBestIndividual(ArrayList<Individual> individuals) {
        Collections.sort(individuals, new IndividualComparator());
        return individuals.get(0);
    }
}
