package overskaug.evolution;

import overskaug.evolution.genotypes.BitVectorGenotype;
import overskaug.evolution.phenotypes.IntegerPhenotype;
import overskaug.evolution.util.Converter;

public class Individual {

    private BitVectorGenotype genotype;
    private IntegerPhenotype phenotype;
    private double fitness = Integer.MAX_VALUE;

    public Individual() {
        genotype = new BitVectorGenotype(10);
        phenotype = Converter.convertToPhenotype(genotype, 3);
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

}

