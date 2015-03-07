package overskaug.evolution;

import java.util.Comparator;

public class IndividualComparator implements Comparator<Individual> {

    @Override
    public int compare(Individual o1, Individual o2) {
        if (o1.getFitness() > o2.getFitness()) {
            return -1;
        } else if (o1.getFitness() == o2.getFitness()) {
            return 0;
        } else {
            return 1;
        }
    }
}
