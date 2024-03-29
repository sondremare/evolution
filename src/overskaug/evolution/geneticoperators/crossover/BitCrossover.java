package overskaug.evolution.geneticoperators.crossover;

import overskaug.evolution.geneticoperators.UnsupportedGeneticOperationException;
import overskaug.evolution.genotypes.BitVectorGenotype;
import overskaug.evolution.population.BitVectorIndividual;
import overskaug.evolution.population.Individual;
import overskaug.evolution.util.FixedBitSet;

import java.util.ArrayList;
import java.util.Random;

public class BitCrossover implements Crossover {

    @Override
    public ArrayList<Individual> onePointCrossover(Individual parent1, Individual parent2, double crossoverRate) throws UnsupportedGeneticOperationException {
        if (parent1.getGenotype() instanceof BitVectorGenotype && parent2.getGenotype() instanceof BitVectorGenotype) {
            Random random = new Random();
            ArrayList<Individual> children = new ArrayList<Individual>();
            if (crossoverRate >= random.nextDouble()) {
                BitVectorGenotype bitVectorGenotypeParent1 = (BitVectorGenotype) parent1.getGenotype();
                BitVectorGenotype bitVectorGenotypeParent2 = (BitVectorGenotype) parent2.getGenotype();
                FixedBitSet parent1Genes = bitVectorGenotypeParent1.getGenes();
                FixedBitSet parent2Genes = bitVectorGenotypeParent2.getGenes();
                int bitLength = parent1Genes.length();
                int slicePoint = random.nextInt(bitLength);

                FixedBitSet parent1first = (FixedBitSet) parent1Genes.clone();
                parent1first.clear(slicePoint, bitLength);
                FixedBitSet parent1second = (FixedBitSet) parent1Genes.clone();
                parent1second.clear(0, slicePoint);
                FixedBitSet parent2first = (FixedBitSet) parent2Genes.clone();
                parent2first.clear(slicePoint, bitLength);
                FixedBitSet parent2second = (FixedBitSet) parent2Genes.clone();
                parent2second.clear(0, slicePoint);

                FixedBitSet child1Genes = new FixedBitSet(bitLength);
                child1Genes.or(parent1first);
                child1Genes.or(parent2second);
                FixedBitSet child2Genes = new FixedBitSet(bitLength);
                child2Genes.or(parent2first);
                child2Genes.or(parent1second);

                children.add(new BitVectorIndividual(new BitVectorGenotype(child1Genes)));
                children.add(new BitVectorIndividual(new BitVectorGenotype(child2Genes)));
            } else {
                children.add(parent1);
                children.add(parent2);
            }
            return children;
        } else {
            throw new UnsupportedGeneticOperationException(parent1.getClass().getSimpleName() + " and " + parent2.getClass().getSimpleName() + " is not supported but this onePointCrossover operation");
        }
    }
}
