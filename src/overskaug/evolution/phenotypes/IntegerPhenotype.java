package overskaug.evolution.phenotypes;

import java.util.ArrayList;

public class IntegerPhenotype implements Phenotype {
    private ArrayList<Integer> phenotype = new ArrayList<Integer>();

    public IntegerPhenotype(ArrayList<Integer> phenotype) {
        this.phenotype = phenotype;
    }

    public ArrayList<Integer> getPhenotype() {
        return phenotype;
    }
}
