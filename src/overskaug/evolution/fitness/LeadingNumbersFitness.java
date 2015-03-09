package overskaug.evolution.fitness;

import overskaug.evolution.phenotypes.IntegerPhenotype;
import overskaug.evolution.phenotypes.Phenotype;
import overskaug.evolution.phenotypes.UnsupportedPhenotypeException;

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
    public double calculateFitness(Phenotype phenotype) throws UnsupportedPhenotypeException {
        if (phenotype instanceof IntegerPhenotype) {
            IntegerPhenotype integerPhenotype = (IntegerPhenotype) phenotype;
            ArrayList<Integer> integers = integerPhenotype.getPhenotype();
            int firstValue = integers.get(0);
            int size = integers.size();
            int threshold = getThreshold();
            if (firstValue == 0 && threshold < size) {
                threshold = getThreshold();
            } else {
                threshold = size;
            }

            int count = 1;
            for (int i = 1; i < threshold; i++) {
                if (integers.get(i) == firstValue) {
                    count++;
                } else {
                    break;
                }
            }
            return (double) count / integers.size();
        } else {
            throw new UnsupportedPhenotypeException(phenotype.getClass().getSimpleName() + " is not supported by this fitness function");
        }

    }
}
