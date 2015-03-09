package overskaug.evolution.genotypes;

import overskaug.evolution.util.FixedBitSet;

import java.util.ArrayList;

public class IntegerGenotype implements Genotype {

    private ArrayList<Integer> integers;

    public IntegerGenotype(ArrayList<Integer> integers) {
        this.integers = integers;
    }

    @Override
    public ArrayList<Integer> getGenes() {
        return integers;
    }
}
