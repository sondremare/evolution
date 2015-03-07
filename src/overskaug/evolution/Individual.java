package overskaug.evolution;

import overskaug.evolution.genotypes.BitVectorGenotype;
import overskaug.evolution.genotypes.Genotype;
import overskaug.evolution.phenotypes.IntegerPhenotype;
import overskaug.evolution.util.Converter;

public class Individual {

    private BitVectorGenotype genotype;
    private IntegerPhenotype phenotype;
    private double fitness = Integer.MAX_VALUE;
    private int BITS_PER_INTEGER = 3;

    public Individual(int bitLength) {
        this.genotype = new BitVectorGenotype(bitLength);
        this.phenotype = Converter.convertToPhenotype(genotype, BITS_PER_INTEGER);
    }

    public Individual(BitVectorGenotype genotype) {
        this.genotype = genotype;
        this.phenotype = Converter.convertToPhenotype(genotype, BITS_PER_INTEGER);
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public BitVectorGenotype getGenotype() {
        return genotype;
    }

    public void setGenotype(BitVectorGenotype genotype) {
        this.genotype = genotype;
    }

    public IntegerPhenotype getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(IntegerPhenotype phenotype) {
        this.phenotype = phenotype;
    }
}

