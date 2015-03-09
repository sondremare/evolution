package overskaug.evolution.selection;

import overskaug.evolution.population.Individual;

public interface ParentPool {
    abstract Individual nextParent();
}
