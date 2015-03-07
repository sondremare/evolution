package overskaug.evolution.util;

import java.util.BitSet;

public class FixedBitSet extends BitSet {

    private int fixedLength;

    public FixedBitSet(int fixedLength) {
        super(fixedLength);
        this.fixedLength = fixedLength;
    }

    @Override
    public int length() {
        return fixedLength;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < length(); i++) {
            output += get(i) ? "1" : "0";
        }
        return output;
    }
}
