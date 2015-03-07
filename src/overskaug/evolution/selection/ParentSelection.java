package overskaug.evolution.selection;

import overskaug.evolution.population.BitVectorIndividual;
import overskaug.evolution.population.Individual;

import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.TreeMap;

public class ParentSelection {

    public static RouletteWheel fitnessProportionate(ArrayList<Individual> individuals) {
        double fitnessSum = getFitnessSum(individuals);
        NavigableMap<Double, Individual> rouletteWheelMap = new TreeMap<Double, Individual>();
        double accumulatedRange = 0;
        for (Individual individual : individuals) {
            accumulatedRange += individual.getFitness() / fitnessSum;
            rouletteWheelMap.put(accumulatedRange, individual);
        }
        return new RouletteWheel(rouletteWheelMap);
    }

    public static RouletteWheel sigmaScaling(ArrayList<Individual> individuals) {
        double fitnessAverage = getFitnessAverage(individuals);
        double standardDeviation = getStandardDeviation(individuals);
        NavigableMap<Double, Individual> rouletteWheelMap = new TreeMap<Double, Individual>();
        double accumulatedRange = 0;
        for (Individual individual : individuals) {
            accumulatedRange += (1 + (individual.getFitness() - fitnessAverage) / (2 * standardDeviation));
            rouletteWheelMap.put(accumulatedRange, individual);
        }
        return new RouletteWheel(rouletteWheelMap);
    }

    public static void tournamentSelection() {
        //TODO
    }

    public static void boltzmannSelection() {
        //TODO
    }

    private static double getFitnessSum(ArrayList<Individual> individuals) {
        double sum = 0;
        for (Individual individual : individuals) {
            sum += individual.getFitness();
        }
        return sum;
    }

    private static double getFitnessAverage(ArrayList<Individual> individuals) {
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

    private static double getStandardDeviation(ArrayList<Individual> individuals) {
        return Math.sqrt(getVariance(individuals));
    }
}
