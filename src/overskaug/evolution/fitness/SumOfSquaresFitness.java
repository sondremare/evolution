package overskaug.evolution.fitness;

import overskaug.evolution.phenotypes.Phenotype;

import java.util.ArrayList;

public class SumOfSquaresFitness implements Fitness {
    private final double BEST_FITNESS = 1.0;
    private Phenotype solution;

    public SumOfSquaresFitness(Phenotype solution) {
        this.solution = solution;
    }

    @Override
    public double getOptimalFitness() {
        return BEST_FITNESS;
    }

    @Override
    public double calculateFitness(Phenotype current) {
        double fitness = 1 / (1 + sumOfSquares(current));
        return fitness;
    }

    public double sumOfSquares(Phenotype current) {
        ArrayList<Integer> currentPhenotype = current.getPhenotype();
        ArrayList<Integer> solutionPhenotype = solution.getPhenotype();
        double sum = 0;
        for (int i = 0; i < currentPhenotype.size(); i++) {
            sum += Math.pow(currentPhenotype.get(i) - solutionPhenotype.get(i), 2);
        }
        return sum;
    }
}
