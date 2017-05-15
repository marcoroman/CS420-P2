import java.util.*;

/**
 * Created by marco on 5/9/2017.
 */
public class Genetic {

    //Initial population of algorithm stored in array list
    //Size variable stores the size of the population's elements
    //The pairs array stores the number of non-attacking queens in the population boards
    //The fitness array stores the fitness values for the members of the population
    private ArrayList<NQueenBoard> population = new ArrayList<>();
    private int[] pairs, fitness;
    private int total = 0;
    private Random gen = new Random();

    //Constructor; initializing class members
    public Genetic(ArrayList<NQueenBoard> p){
        population = p;
        pairs = new int[p.size()];
        fitness = new int[p.size()];
    }

    //The Genetic Algorithm
    public void solve(){
        ArrayList<NQueenBoard> successors = new ArrayList<>();
        int[] parent1, parent2;

        fitnessFunction();
        parent1 = selectParent().clone();
        parent2 = selectParent().clone();

        successors.add(new NQueenBoard(crossover(parent1, parent2)));

        //FIND A WAY TO POPULATE SUCCESSOR ARRAYLIST USING SELECTION
        //CROSSOVER, AND MUTATION (start with the first two)
        //RUN SINGLE CASE FIRST
        while(!population.isEmpty()){
            int select = gen.nextInt(population.size());
            int[] child;

            if(gen.nextBoolean()){
                child = crossover(parent1, population.get(select).getBoard()).clone();
            }else{
                child = crossover(parent2, population.get(select).getBoard()).clone();
            }

            population.remove(select);
            successors.add(new NQueenBoard(child));
        }

        System.out.println(successors.size());
    }

    //Storing the fitness value for each population member
    //These proportions represent the board's probability of being
    //Chosen for reproduction
    public void fitnessFunction(){
        total = 0;

        Comparator<NQueenBoard> c = new GenerationSort();
        Collections.sort(population, c);

        for(int i = 0; i < population.size(); ++i) {
            population.get(i).setWeight(total);
            fitness[i] = population.get(i).fitness();
            total += fitness[i];
        }

        System.out.println(Arrays.toString(fitness));
    }

    public int[] selectParent(){
        int val = gen.nextInt(total);
        int count = 0, decrement = 0;

        while(count < population.size() - 1 && val > population.get(count).getWeight())
            ++count;

        int[] childBoard = population.get(count).getBoard();
        decrement = population.get(count).fitness();
        total -= decrement;
        population.remove(count);

        for(int i = count + 1; i < population.size(); ++i)
            population.get(i).decrementWeight(decrement);

        return childBoard;
    }

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

    /*
    * Production of a child requires selection, cross-over, mutation
    * The population for following iterations of the algorithm consists of
    * children of the current iteration.
    *
    * +HOW ARE THE POTENTIAL PARENTS CHOSEN? DOES THE FITTEST PARENT REPRODUCE
    *   WITH THE FOLLOWING TWO WHILE THE LEAST FIT IS DISCARDED? OR ARE THEY
    *   RANDOMLY PAIRED?
    *
    * +SELECTION HAPPENS AT RANDOMLY CHOSEN POINT?
    *
    * +HOW EXACTLY DOES THE MUTATION WORK?
    *   +WHAT DOES "IF SMALL RANDOM PROBABILITY" MEAN?
    * */
}