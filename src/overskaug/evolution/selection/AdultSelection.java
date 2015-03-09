package overskaug.evolution.selection;

import overskaug.evolution.population.Individual;
import overskaug.evolution.util.FitnessCalculations;

import java.util.ArrayList;

public class AdultSelection {
    public static enum AdultSelectionEnum {
        FULL_GENERATIONAL_REPLACEMENT,
        OVER_PRODUCTION,
        GENERATIONAL_MIXING
    }

    public static ArrayList<Individual> fullGenerationalReplacement(ArrayList<Individual> adults, ArrayList<Individual> children) {
        return children;
    }

    public static ArrayList<Individual> overProduction(ArrayList<Individual> adults, ArrayList<Individual> children, int adultPoolSize) {
        return FitnessCalculations.getNBestIndividuals(children, adultPoolSize);
    }

    public static ArrayList<Individual> generationalMixing(ArrayList<Individual> adults, ArrayList<Individual> children, int adultPoolSize) {
        adults.addAll(children);
        return FitnessCalculations.getNBestIndividuals(adults, adultPoolSize);
    }
}
