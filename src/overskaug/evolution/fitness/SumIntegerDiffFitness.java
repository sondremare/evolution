package overskaug.evolution.fitness;

import overskaug.evolution.phenotypes.IntegerPhenotype;
import overskaug.evolution.phenotypes.Phenotype;
import overskaug.evolution.phenotypes.UnsupportedPhenotypeException;

import java.util.ArrayList;

public class SumIntegerDiffFitness implements Fitness {
    private final double BEST_FITNESS = 1.0;
    private Phenotype solution;

    public SumIntegerDiffFitness(Phenotype solution) {
        this.solution = solution;
    }

    @Override
    public double getOptimalFitness() {
        return BEST_FITNESS;
    }

    @Override
    public double calculateFitness(Phenotype current) throws UnsupportedPhenotypeException {
        double fitness = 1 / (1 + sumIntegerDiff(current));
        return fitness;
    }

    public double sumIntegerDiff(Phenotype phenotype) throws UnsupportedPhenotypeException {
        if (phenotype instanceof IntegerPhenotype) {
            IntegerPhenotype integerPhenotype = (IntegerPhenotype) phenotype;
            IntegerPhenotype solutionPhenotype = (IntegerPhenotype) solution;
            ArrayList<Integer> integers = integerPhenotype.getPhenotype();
            ArrayList<Integer> solution = solutionPhenotype.getPhenotype();
            double sum = 0;
            for (int i = 0; i < integers.size(); i++) {
                sum += Math.abs(integers.get(i) - solution.get(i));
            }
            return sum;
        } else {
            throw new UnsupportedPhenotypeException(phenotype.getClass().getSimpleName() + " is not supported by this fitness function");
        }
    }
}
