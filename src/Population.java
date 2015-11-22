
public class Population {
	Individual[] individuals;
	
	
	public Population(int size) {
		this.individuals = new Individual[size];
	}
	public Population(int size, int maxDepth) {
		this.individuals = new Individual[size];
		for(int i = 0; i < size; i++) {
			Individual newIndiv = new Individual();
			newIndiv.generateIndividual(maxDepth);
			saveIndividual(i, newIndiv);
		}
	}
	
	public void saveIndividual(int index, Individual indiv) {
		this.individuals[index] = indiv;
	}
	
	public Individual getIndividual(int index) {
		return this.individuals[index];
	}
	
	public int size() {
		return this.individuals.length;
	}
}
