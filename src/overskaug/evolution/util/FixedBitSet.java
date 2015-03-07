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
}
