package overskaug.evolution.fitness;

import overskaug.evolution.phenotypes.IntegerPhenotype;
import overskaug.evolution.phenotypes.Phenotype;
import overskaug.evolution.phenotypes.UnsupportedPhenotypeException;

import java.util.ArrayList;

public class LocalSequenceFitness extends GlobalSequenceFitness {
    private final int DISTANCE = 1;

    @Override
    public double calculateFitness(Phenotype phenotype) throws UnsupportedPhenotypeException {
        if (phenotype instanceof IntegerPhenotype) {
            int sum = 0;
            ArrayList<Integer> integers = phenotype.getPhenotype();
            for (int i = 0; i < integers.size()-1; i++) {
                int first = integers.get(i);
                int second = integers.get(i+1);
                int occurences = findOccurences(integers, first, second, DISTANCE);
                if (occurences > 1) {
                    sum += occurences;
                }
            }
            return (double) 1 / (1 + sum);
        } else {
            throw new UnsupportedPhenotypeException(phenotype.getClass().getSimpleName() + " is not supported by this mutation operator");
        }
    }
}
