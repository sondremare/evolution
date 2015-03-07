package overskaug.evolution.util;

import overskaug.evolution.genotypes.BitVectorGenotype;
import overskaug.evolution.phenotypes.IntegerPhenotype;

import java.util.ArrayList;

public class Converter {

    public static int BITS_PER_INTEGER = 3;

    public static IntegerPhenotype convertToPhenotype(BitVectorGenotype genotype) {
        FixedBitSet genes = genotype.getGenes();
        int shift = genes.length() - 1;
        ArrayList<Integer> integers = new ArrayList<Integer>();
        while (shift > 0) {
            int value = 0;
            int bit = 0;
            for (int j = shift; j > ((shift - BITS_PER_INTEGER) >= 0 ? shift - BITS_PER_INTEGER : -1); j--) {
                value += genes.get(j) ? (1 << bit) : 0;
                bit++;
            }
            integers.add(value);
            shift -= BITS_PER_INTEGER;
        }

        return new IntegerPhenotype(integers);
    }

   /* private FixedBitSet addLeadingZeroes(BitSet bitVector, int bitsPerInteger) {
        int numberOfLeadingZeroes = bitVector.size() % bitsPerInteger;
        FixedBitSet leadingZeroesVector = new FixedBitSet(numberOfLeadingZeroes + bitVector.size());
        for (int i = numberOfLeadingZeroes; i < leadingZeroesVector.size(); i++) {
            leadingZeroesVector.set(i, bitVector.get(i));
        }
        return leadingZeroesVector;
    } */
}
