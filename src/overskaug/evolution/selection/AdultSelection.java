package overskaug.evolution.selection;

import overskaug.evolution.population.Individual;
import overskaug.evolution.population.IndividualComparator;

import java.util.ArrayList;
import java.util.Collections;

public class AdultSelection {

    public static ArrayList<Individual> fullGenerationalReplacement(ArrayList<Individual> adults, ArrayList<Individual> children) {
        return children;
    }

    public static ArrayList<Individual> overProduction(ArrayList<Individual> adults, ArrayList<Individual> children, int adultPoolSize) {
        return getNBestIndividuals(children, adultPoolSize);
    }

    public static ArrayList<Individual> generationalMixing(ArrayList<Individual> adults, ArrayList<Individual> children, int adultPoolSize) {
        adults.addAll(children);
        return getNBestIndividuals(adults, adultPoolSize);
    }

    private static ArrayList<Individual> getNBestIndividuals(ArrayList<Individual> individuals, int n) {
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
