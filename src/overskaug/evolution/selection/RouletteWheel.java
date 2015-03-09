package overskaug.evolution.selection;

import overskaug.evolution.population.Individual;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RouletteWheel {
    private NavigableMap<Double, Individual> rouletteWheel = new TreeMap<Double, Individual>();
    private Random random = new Random();
    private double range;

    public RouletteWheel(NavigableMap<Double, Individual> rouletteWheel, double range) {
        this.rouletteWheel = rouletteWheel;
        this.range = range;
    }

    public Individual nextParent() {
        double randomDouble = random.nextDouble() * range;
        return rouletteWheel.ceilingEntry(randomDouble).getValue();
    }
}
