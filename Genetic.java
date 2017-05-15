import java.util.*;

/**
 * Created by marco on 5/9/2017.
 */
public class Genetic {

    //Initial population of algorithm stored in array list
    //K variable stores the size of the population's elements
    private ArrayList<NQueenBoard> population = new ArrayList<>();
    private int total = 0, k, dimension;
    private Random gen = new Random();

    //Constructor; initializing class members
    public Genetic(ArrayList<NQueenBoard> p){
        population = p;
        k = p.size();
        dimension = p.get(0).getBoard().length;
    }

    //The Genetic Algorithm
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
            Comparator<NQueenBoard> c = new GenerationSort();
            Collections.sort(successors, c);

            //MUTATE
            mutate(successors);

            population.addAll(successors);
            successors.clear();
        }
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
            total += population.get(i).fitness();
        }
    }

    public int[] selectParent(){
        int val = gen.nextInt(total);
        int count = 0, decrement;

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

    public void mutate(ArrayList<NQueenBoard> successors){
        for(int j = 0; j < successors.size() / 4; ++j)
            successors.get(j).mutate();
    }
}