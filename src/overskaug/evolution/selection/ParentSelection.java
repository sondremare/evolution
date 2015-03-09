package overskaug.evolution.selection;

import overskaug.evolution.population.Individual;
import overskaug.evolution.util.FitnessCalculations;

import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.TreeMap;

public class ParentSelection {
    public static enum ParentSelectionEnum {
        FITNESS_PROPORTIONATE,
        SIGMA_SCALING,
        BOLTZMANN_SELECTION,
        TOURNAMENT_SELECTION
    }

    public static RouletteWheel fitnessProportionate(ArrayList<Individual> individuals) {
        double fitnessSum = FitnessCalculations.getFitnessSum(individuals);
        NavigableMap<Double, Individual> rouletteWheelMap = new TreeMap<Double, Individual>();
        double accumulatedRange = 0;
        for (Individual individual : individuals) {
            accumulatedRange += individual.getFitness() / fitnessSum;
            rouletteWheelMap.put(accumulatedRange, individual);
        }
        return new RouletteWheel(rouletteWheelMap, accumulatedRange);
    }

    public static RouletteWheel sigmaScaling(ArrayList<Individual> individuals) {
        double fitnessAverage = FitnessCalculations.getFitnessAverage(individuals);
        double standardDeviation = FitnessCalculations.getStandardDeviation(individuals);
        NavigableMap<Double, Individual> rouletteWheelMap = new TreeMap<Double, Individual>();
        double accumulatedRange = 0;
        for (Individual individual : individuals) {
            accumulatedRange += (1 + (individual.getFitness() - fitnessAverage) / (2 * standardDeviation));
            rouletteWheelMap.put(accumulatedRange, individual);
        }
        return new RouletteWheel(rouletteWheelMap, accumulatedRange);
    }

    public static TournamentPool tournamentSelection(ArrayList<Individual> individuals, int tournamentSize, double probability) {
        return new TournamentPool(individuals, tournamentSize, probability);
    }

    public static RouletteWheel boltzmannSelection(ArrayList<Individual> individuals, double temperature) {
        double sum = 0;
        if (temperature < 0) temperature = 0;
        ArrayList<Double> individualBoltzmannValues = new ArrayList<Double>();
        for (Individual individual : individuals) {
            double individualValue = Math.exp(individual.getFitness() / temperature);
            sum += individualValue;
            individualBoltzmannValues.add(individualValue);
        }
        double average = sum / individuals.size();

        NavigableMap<Double, Individual> rouletteWheelMap = new TreeMap<Double, Individual>();
        double accumulatedRange = 0;
        for (int i = 0; i < individuals.size(); i++) {
            accumulatedRange += individualBoltzmannValues.get(i) / average;
            rouletteWheelMap.put(accumulatedRange, individuals.get(i));
        }
        return new RouletteWheel(rouletteWheelMap, accumulatedRange);
    }

}
