import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by marco on 5/9/2017.
 */
public class Genetic {
    //Initial population of algorithm stored in array list
    //Size variable stores the size of the population's elements
    //The pairs array stores the number of non-attacking queens in the population boards
    //The fitness array stores the fitness values for the members of the population
    private ArrayList<int[]> population = new ArrayList<>();
    private int[] pairs, fitness;

    //Constructor; initializing class members
    public Genetic(ArrayList<int[]> p){
        population = p;
        pairs = new int[p.size()];
        fitness = new int[p.size()];
    }

    //The Genetic Algorithm
    public void solve(){
        fitnessFunction(population);
    }

    //Storing the fitness value for each population member
    //AS PROPORTIONS?
    public void fitnessFunction(ArrayList<int[]> p){
        int total = 0;

        for(int i = 0; i < p.size(); ++i){
            pairs[i] = nonAttackingPairs(p.get(i));
            total += pairs[i];
        }

        for(int i = 0; i < p.size(); ++i){
            fitness[i] = (int)Math.round(((double)pairs[i] / (double)total) * 100);
        }

        System.out.println(Arrays.toString(fitness));
    }

    //Returns number of NON-attacking queen pairs in the boards in the population
    public int nonAttackingPairs(int[] b){
        int pairs = 0;
        int relation;

        //Loop checks one column at a time; checks the queens the current
        //queen may not attack to its right horizontally or diagonally
        for(int i = 0; i < b.length; ++i){
            for(int j = i + 1; j < b.length; ++j){
                relation = j - i;
                if(!(b[i] == b[j] || b[j] - relation == b[i] || b[j] + relation == b[i]))
                    ++pairs;
            }
        }

        return pairs;
    }
}
