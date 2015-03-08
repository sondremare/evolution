package overskaug.evolution.geneticoperators.mutation;

import overskaug.evolution.geneticoperators.UnsupportedGeneticOperationException;
import overskaug.evolution.genotypes.BitVectorGenotype;
import overskaug.evolution.genotypes.Genotype;
import overskaug.evolution.util.FixedBitSet;

import java.util.Random;

public class BitMutation implements Mutation {
    @Override
    public void mutate(Genotype genotype, double chance) throws UnsupportedGeneticOperationException {
        if (genotype instanceof BitVectorGenotype) {
            BitVectorGenotype bitVectorGenotype = (BitVectorGenotype) genotype;
            FixedBitSet genes = bitVectorGenotype.getGenes();
            Random random = new Random();
            if (chance >= random.nextDouble()) {
                int geneNumber = random.nextInt(genes.length());
                genes.flip(geneNumber);
            }
        } else {
            throw new UnsupportedGeneticOperationException(genotype.getClass().getSimpleName() + " is not supported by this mutation operator");
        }
    }

    @Override
    public void mutateAllComponents(Genotype genotype, double chance) throws UnsupportedGeneticOperationException {
        if (genotype instanceof BitVectorGenotype) {
            BitVectorGenotype bitVectorGenotype = (BitVectorGenotype) genotype;
            FixedBitSet genes = bitVectorGenotype.getGenes();
            Random random = new Random();
            for (int i = 0; i < genes.length(); i++) {
                if (chance <= random.nextDouble()) {
                    genes.flip(i);
                }
            }
        } else {
            throw new UnsupportedGeneticOperationException(genotype.getClass().getSimpleName() + " is not supported by this mutation operator");
        }
    }
}
