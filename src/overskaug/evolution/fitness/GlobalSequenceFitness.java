package overskaug.evolution.fitness;

import overskaug.evolution.phenotypes.IntegerPhenotype;
import overskaug.evolution.phenotypes.Phenotype;
import overskaug.evolution.phenotypes.UnsupportedPhenotypeException;

import java.util.ArrayList;

public class GlobalSequenceFitness implements Fitness {
    private final double BEST_FITNESS = 1.0;

    @Override
    public double getOptimalFitness() {
        return BEST_FITNESS;
    }

    @Override
    public double calculateFitness(Phenotype phenotype) throws UnsupportedPhenotypeException {
        if (phenotype instanceof IntegerPhenotype) {
            int sum = 0;
            ArrayList<Integer> integers = phenotype.getPhenotype();
            for (int i = 0; i < integers.size()-1; i++) {
                int first = integers.get(i);
                for (int j = i+1; j < integers.size(); j++) {
                    int second = integers.get(j);
                    int distance = j - i;
                    int occurences = findOccurences(integers, first, second, distance);
                    if (occurences > 1) {
                        sum += occurences;
                    }
                }
            }
            return (double) 1 / (1 + sum);
        } else {
            throw new UnsupportedPhenotypeException(phenotype.getClass().getSimpleName() + " is not supported by this mutation operator");
        }
    }

    protected int findOccurences(ArrayList<Integer> integers, int first, int second, int distance) {
        int occurences = 0;
        for (int i = 0; i < integers.size() - distance; i++) {
            if (integers.get(i) == first && integers.get(i+distance) == second) {
                occurences++;
            }
        }
        return occurences;
    }
}
