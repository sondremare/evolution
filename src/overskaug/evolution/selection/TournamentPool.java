package overskaug.evolution.selection;

import overskaug.evolution.population.Individual;
import overskaug.evolution.population.IndividualComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TournamentPool implements ParentPool {
    private ArrayList<Individual> parentPool;
    private int counter;
    private Random random;
    private int tournamentSize;
    private double probability;

    public TournamentPool(ArrayList<Individual> parentPool, int tournamentSize, double probability) {
        this.parentPool = parentPool;
        this.counter = 0;
        this.random = new Random();
        this.tournamentSize = tournamentSize;
        this.probability = probability;
    }
    @Override
    public Individual nextParent() {
        ArrayList<Individual> tournamentGroup = getTournamentGroup();
        Collections.sort(tournamentGroup, new IndividualComparator());
        if (probability >= random.nextDouble()) {
            return tournamentGroup.get(random.nextInt(tournamentGroup.size()));
        } else {
            return tournamentGroup.get(0);
        }

    }

    private ArrayList<Individual> getTournamentGroup() {
        ArrayList<Individual> tournamentGroup = new ArrayList<Individual>();
        while (tournamentGroup.size() < tournamentSize) {
            tournamentGroup.add(parentPool.get(random.nextInt(parentPool.size())));
        }
        return tournamentGroup;
    }
}
