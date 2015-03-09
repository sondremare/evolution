package overskaug.evolution.population;

import overskaug.evolution.genotypes.Genotype;
import overskaug.evolution.genotypes.IntegerGenotype;
import overskaug.evolution.phenotypes.IntegerPhenotype;
import overskaug.evolution.phenotypes.Phenotype;
import overskaug.evolution.util.Converter;

public class IntegerIndividual implements Individual{

    private IntegerGenotype genotype;
    private IntegerPhenotype phenotype;
    private double fitness = 0;

    public IntegerIndividual(IntegerGenotype genotype) {
        this.genotype = genotype;
        this.phenotype = Converter.convertToPhenotype(genotype);
    }

    @Override
    public Genotype getGenotype() {
        return genotype;
    }

    @Override
    public Phenotype getPhenotype() {
        return phenotype;
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
    public String toString() {
        return getPhenotype().toString();
    }
}
