package overskaug.evolution.selection;

import overskaug.evolution.Individual;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RouletteWheel {
    private NavigableMap<Double, Individual> rouletteWheel = new TreeMap<Double, Individual>();
    Random random = new Random();

    public RouletteWheel(NavigableMap<Double, Individual> rouletteWheel) {
        this.rouletteWheel = rouletteWheel;
    }

    public Individual nextParent() {
        double randomDouble = random.nextDouble();
        return rouletteWheel.ceilingEntry(randomDouble).getValue();
    }
}
