package overskaug.evolution.fitness;

import overskaug.evolution.phenotypes.Phenotype;

import java.util.ArrayList;

public class LeadingNumbersFitness implements Fitness {
    private final double BEST_FITNESS = 1.0;
    private int threshold;

    public LeadingNumbersFitness(int threshold) {
        this.threshold = threshold;
    }

    public int getThreshold() {
        return threshold;
    }

    @Override
    public double getOptimalFitness() {
        return BEST_FITNESS;
    }

    @Override
    public double calculateFitness(Phenotype phenotype) {
        ArrayList<Integer> numbers = phenotype.getPhenotype();
        int firstValue = numbers.get(0);
        int size = numbers.size();
        int threshold = getThreshold();
        if (firstValue == 0 && threshold < size) {
            threshold = getThreshold();
        } else {
            threshold = size;
        }

        int count = 1;
        for (int i = 1; i < threshold; i++) {
            if (numbers.get(i) == firstValue) {
                count++;
            } else {
                break;
            }
        }
        return (double) count / numbers.size();
    }
}
