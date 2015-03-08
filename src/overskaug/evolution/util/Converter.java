package overskaug.evolution.util;

import overskaug.evolution.genotypes.BitVectorGenotype;
import overskaug.evolution.phenotypes.IntegerPhenotype;

import java.util.ArrayList;
import java.util.Collections;

public class Converter {

    public static int BITS_PER_INTEGER = 1;

    public static void setBitsPerInteger(int bitsPerInteger) {
        BITS_PER_INTEGER = bitsPerInteger;
    }

    public static IntegerPhenotype convertToPhenotype(BitVectorGenotype genotype) {
        FixedBitSet genes = genotype.getGenes();
        int shift = genes.length() - 1;
        ArrayList<Integer> integers = new ArrayList<Integer>();
        while (shift >= 0) {
            int value = 0;
            int bit = 0;
            for (int j = shift; j > ((shift - BITS_PER_INTEGER) >= 0 ? shift - BITS_PER_INTEGER : -1); j--) {
                value += genes.get(j) ? (1 << bit) : 0;
                bit++;
            }
            integers.add(value);
            shift -= BITS_PER_INTEGER;
        }
        Collections.reverse(integers);
        return new IntegerPhenotype(integers);
    }
}
