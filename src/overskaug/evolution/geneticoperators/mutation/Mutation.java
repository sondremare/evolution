package overskaug.evolution.geneticoperators.mutation;

import overskaug.evolution.geneticoperators.UnsupportedGeneticOperationException;
import overskaug.evolution.genotypes.Genotype;

public interface Mutation {
    public enum MutationEnum {
        GENOME_MUTATION,
        PER_GENE_MUTATION
    }

    abstract public void mutate(Genotype genotype, double chance) throws UnsupportedGeneticOperationException;
    abstract public void mutateAllComponents(Genotype genotype, double chance) throws UnsupportedGeneticOperationException;


}
