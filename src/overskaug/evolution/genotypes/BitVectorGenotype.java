package overskaug.evolution.genotypes;

import overskaug.evolution.util.FixedBitSet;
import java.util.Random;

public class BitVectorGenotype implements Genotype {

    private FixedBitSet genes;
    private final static Random random = new Random();

    public BitVectorGenotype(FixedBitSet genes) {
        this.genes = genes;
    }

    public BitVectorGenotype(int geneLength) {
        genes = new FixedBitSet(geneLength);
        for (int i = 0; i < geneLength; i++) {
            genes.set(i, random.nextBoolean());
        }
    }

    public FixedBitSet getGenes() {
        return genes;
    }
}

