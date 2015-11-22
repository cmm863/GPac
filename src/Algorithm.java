import java.util.Random;

public class Algorithm {
	private static double mutationRate = 0;
	private static int tournamentSize = 0;
	
	
	public static Population evolvePopulation(Population pop) {
		Population newPop = new Population(pop.size());
		
		Random r = new Random();
		
		
		for(int i = 0; i < pop.size(); i++) {
			if(r.nextDouble() > mutationRate) {
				System.out.println("Crossover.");
				Individual[] children;
				Individual parentOne = selection(pop);
				Individual parentTwo = selection(pop);
				children = crossover(parentOne, parentTwo);
				newPop.saveIndividual(i++, children[0]);
				if(i < pop.size()) {
					newPop.saveIndividual(i, children[1]);
				}
			} else {
				System.out.println("Mutation.");
				Individual parent = selection(pop);
				Individual child = mutate(parent);
				newPop.saveIndividual(i, child);
			}
		}
		return newPop;
	}

	private static Individual mutate(Individual indiv) {
		return indiv;
	}
	
	private static Individual[] crossover(Individual one, Individual two) {
		Individual[] returnArray = {one, two};
		return returnArray;
	}
	
	private static Individual selection(Population pop) {
		return pop.getIndividual(0);
	}
	
	public static void setKSize(int k) {
		tournamentSize = k;
	}
	
	public static void setMutationRate(double m) {
		mutationRate = m;
	}
}
