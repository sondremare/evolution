package overskaug.evolution.fitness;

import overskaug.evolution.phenotypes.Phenotype;

import java.util.ArrayList;

public class SumIntegerDiffFitness implements Fitness {
    private final double BEST_FITNESS = 1.0;
    private Phenotype solution;

    public SumIntegerDiffFitness(Phenotype solution) {
        this.solution = solution;
    }

    @Override
    public double getThreshold() {
        return BEST_FITNESS;
    }

    @Override
    public double calculateFitness(Phenotype current) {
        double fitness = 1 / (1 + sumIntegerDiff(current));
        return fitness;
    }

    public double sumIntegerDiff(Phenotype current) {
        ArrayList<Integer> currentPhenotype = current.getPhenotype();
        ArrayList<Integer> solutionPhenotype = solution.getPhenotype();
        double sum = 0;
        for (int i = 0; i < currentPhenotype.size(); i++) {
            sum += Math.abs(currentPhenotype.get(i) - solutionPhenotype.get(i));
        }
        return sum;
    }
}
