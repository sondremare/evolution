package overskaug.evolution.geneticoperators.mutation;

import overskaug.evolution.geneticoperators.UnsupportedGeneticOperationException;
import overskaug.evolution.genotypes.Genotype;

public interface Mutation {

    abstract public void mutate(Genotype genotype, double percentage) throws UnsupportedGeneticOperationException;
    abstract public void mutateAllComponents(Genotype genotype, double percentage) throws UnsupportedGeneticOperationException;
}
