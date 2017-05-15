import java.util.*;

/**
 * Created by marco on 5/9/2017.
 */
public class Genetic {

    //Initial population of algorithm stored in array list
    //Total variable used to for random selection of parents
    //K variable stores the size of the population's elements
    //Dimension variable used to determine terminal state of algorithm (ideal fitness value)

    private ArrayList<NQueenBoard> population = new ArrayList<>();
    private int total = 0, k, dimension;
    private Random gen = new Random();
    Comparator<NQueenBoard> c = new GenerationSort();

    //Constructor; initializing class members
    public Genetic(ArrayList<NQueenBoard> p){
        population = p;
        k = p.size();
        dimension = p.get(0).getBoard().length;
    }

    /*The Genetic Algorithm
        -Determine the fitness of all members of the population
        -Randomly select two members to be parents of next generation
        -Remove the least fit 25% of the population
        -Populate successor generation by crossing parents over with the remaining members
        -Apply random mutations to the least fit 25% of the successor generation
        -Repeat until the ideally fit individual is produced
     */
    public void solve(){
        ArrayList<NQueenBoard> successors = new ArrayList<>();
        int[] parent1, parent2;

        while(true) {
            fitnessFunction();

            if(population.get(k - 1).fitness() == (int) (dimension * ((dimension - 1) / 2.0))){
                Driver.print(population.get(k - 1).getBoard());
                break;
            }

            parent1 = selectParent().clone();
            parent2 = selectParent().clone();

            successors.add(new NQueenBoard(crossover(parent1, parent2)));

            for(int x = 0; x <= population.size() / 4; ++x)
                population.remove(0);

            selection(successors, parent1, parent2);

            population.clear();
            Collections.sort(successors, c);

            mutate(successors);

            population.addAll(successors);
            successors.clear();
        }
    }

    /*****************************************Fitness functions*****************************************/

    //Storing the fitness value for each population member
    //The weights represent the board's probability of being
    //Chosen for being parents of the next generation
    public void fitnessFunction(){
        total = 0;

        Collections.sort(population, c);

        for(int i = 0; i < population.size(); ++i) {
            population.get(i).setWeight(total);
            total += population.get(i).fitness();
        }
    }

    //Selecting the parents of the next generation fitter members
    //are more likely to be chosen than unfit members
    public int[] selectParent(){
        int val = gen.nextInt(total);
        int count = 0, decrement;

        while(count < population.size() - 1 && val > population.get(count).getWeight())
            ++count;

        int[] parentBoard = population.get(count).getBoard();

        //These decrements are applied to keep the probabilities consistent
        decrement = population.get(count).fitness();
        total -= decrement;
        population.remove(count);

        for(int i = count + 1; i < population.size(); ++i)
            population.get(i).decrementWeight(decrement);

        return parentBoard;
    }

    /*****************************************Selection, Crossover, Mutation*****************************************/

    //Simulates the "reproduction" of the parent boards with randomly chosen
    //members of the population
    public void selection(ArrayList<NQueenBoard> successors, int[] parent1, int[] parent2){
        while (successors.size() < k) {
            int select = gen.nextInt(population.size());
            int[] child;

            if (gen.nextBoolean()) {
                child = crossover(parent1, population.get(select).getBoard()).clone();
            } else {
                child = crossover(parent2, population.get(select).getBoard()).clone();
            }

            successors.add(new NQueenBoard(child));
        }
    }

    //This method performs the actual combination of board states
    //that is used to produce the new generation. The crossover
    //point is randomly chosen.
    public int[] crossover(int[] a1, int[] a2){
        int point = gen.nextInt(a1.length);
        int[] child = new int[a1.length];

        for(int i = 0; i < child.length; ++i){
            if(i < point){
                child[i] = a1[i];
            }else{
                child[i] = a2[i];
            }
        }

        return child;
    }

    //Applies mutations to the least fit 25% of the population
    public void mutate(ArrayList<NQueenBoard> successors){
        for(int j = 0; j < successors.size() / 4; ++j)
            successors.get(j).mutate();
    }
}