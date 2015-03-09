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
        for (Individual individual : individuals) {;
            accumulatedRange += (1 + (individual.getFitness() - fitnessAverage) / (2 * standardDeviation));
            rouletteWheelMap.put(accumulatedRange, individual);
        }
        return new RouletteWheel(rouletteWheelMap, accumulatedRange);
    }

    public static RouletteWheel tournamentSelection() {
        //TODO
        return null;
    }

    public static RouletteWheel boltzmannSelection() {
        //TODO
        return null;
    }

}
