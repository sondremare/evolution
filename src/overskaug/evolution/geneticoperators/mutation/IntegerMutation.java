package overskaug.evolution.geneticoperators.mutation;

import overskaug.evolution.geneticoperators.UnsupportedGeneticOperationException;
import overskaug.evolution.genotypes.Genotype;
import overskaug.evolution.genotypes.IntegerGenotype;

import java.util.ArrayList;
import java.util.Random;

public class IntegerMutation implements Mutation {
    @Override
    public void mutate(Genotype genotype, double chance) throws UnsupportedGeneticOperationException {
        if (genotype instanceof IntegerGenotype) {
            IntegerGenotype integerGenotype = (IntegerGenotype) genotype;
            ArrayList<Integer> genes = integerGenotype.getGenes();
            Random random = new Random();
            if (chance >= random.nextDouble()) {
                int geneNumber = random.nextInt(genes.size());
                boolean incrementUp = random.nextBoolean();
                if (incrementUp) {
                    int newVal = genes.get(geneNumber) + 1;
                    genes.set(geneNumber, newVal);
                } else {
                    int newVal = genes.get(geneNumber) - 1;
                    if (newVal >= 0) genes.set(geneNumber, newVal);
                }
            }
        } else {
            throw new UnsupportedGeneticOperationException(genotype.getClass().getSimpleName() + " is not supported by this mutation operator");
        }
    }

    @Override
    public void mutateAllComponents(Genotype genotype, double chance) throws UnsupportedGeneticOperationException {
        if (genotype instanceof IntegerGenotype) {
            IntegerGenotype integerGenotype = (IntegerGenotype) genotype;
            ArrayList<Integer> genes = integerGenotype.getGenes();
            Random random = new Random();
            for (int i = 0; i < genes.size(); i++) {
                if (chance >= random.nextDouble()) {
                    boolean incrementUp = random.nextBoolean();
                    if (incrementUp) {
                        int newVal = genes.get(i) + 1;
                        genes.set(i, newVal);
                    } else {
                        int newVal = genes.get(i) - 1;
                        if (newVal >= 0) genes.set(i, newVal);
                    }
                }
            }
        } else {
            throw new UnsupportedGeneticOperationException(genotype.getClass().getSimpleName() + " is not supported by this mutation operator");
        }
    }
}
