package overskaug.evolution.population;

import overskaug.evolution.genotypes.BitVectorGenotype;
import overskaug.evolution.phenotypes.IntegerPhenotype;
import overskaug.evolution.util.Converter;

public class BitVectorIndividual implements Individual {

    private BitVectorGenotype genotype;
    private IntegerPhenotype phenotype;
    private double fitness = 0;

    public BitVectorIndividual(int bitLength) {
        this.genotype = new BitVectorGenotype(bitLength);
        this.phenotype = Converter.convertToPhenotype(genotype);
    }

    public BitVectorIndividual(BitVectorGenotype genotype) {
        this.genotype = genotype;
        this.phenotype = Converter.convertToPhenotype(genotype);
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public BitVectorGenotype getGenotype() {
        return genotype;
    }

    public void setGenotype(BitVectorGenotype genotype) {
        this.genotype = genotype;
    }

    @Override
    public IntegerPhenotype getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(IntegerPhenotype phenotype) {
        this.phenotype = phenotype;
    }
}

