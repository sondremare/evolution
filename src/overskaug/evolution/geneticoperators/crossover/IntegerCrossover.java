package overskaug.evolution.geneticoperators.crossover;

import overskaug.evolution.geneticoperators.UnsupportedGeneticOperationException;
import overskaug.evolution.genotypes.BitVectorGenotype;
import overskaug.evolution.genotypes.IntegerGenotype;
import overskaug.evolution.population.BitVectorIndividual;
import overskaug.evolution.population.Individual;
import overskaug.evolution.population.IntegerIndividual;
import overskaug.evolution.util.FixedBitSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntegerCrossover implements Crossover {

    @Override
    public ArrayList<Individual> onePointCrossover(Individual parent1, Individual parent2, double crossoverRate) throws UnsupportedGeneticOperationException {
        if (parent1.getGenotype() instanceof IntegerGenotype && parent2.getGenotype() instanceof IntegerGenotype) {
            Random random = new Random();
            ArrayList<Individual> children = new ArrayList<Individual>();
            if (crossoverRate >= random.nextDouble()) {
                IntegerGenotype genotypeParent1 = (IntegerGenotype) parent1.getGenotype();
                IntegerGenotype genotypeParent2 = (IntegerGenotype) parent2.getGenotype();
                ArrayList<Integer> parent1Genes = genotypeParent1.getGenes();
                ArrayList<Integer> parent2Genes = genotypeParent2.getGenes();
                int geneLength = parent1Genes.size();
                int slicePoint = random.nextInt(geneLength);

                List<Integer> parent1first = parent1Genes.subList(0, slicePoint);
                List<Integer> parent1second = parent1Genes.subList(slicePoint, geneLength);

                List<Integer> parent2first = parent2Genes.subList(0, slicePoint);
                List<Integer> parent2second = parent2Genes.subList(slicePoint, geneLength);

                ArrayList<Integer> child1Genes = new ArrayList<Integer>();
                child1Genes.addAll(parent1first);
                child1Genes.addAll(parent2second);
                ArrayList<Integer> child2Genes = new ArrayList<Integer>();
                child2Genes.addAll(parent2first);
                child2Genes.addAll(parent1second);

                children.add(new IntegerIndividual(new IntegerGenotype(child1Genes)));
                children.add(new IntegerIndividual(new IntegerGenotype(child2Genes)));
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
