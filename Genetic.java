import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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

    //Constructor; initializing class members
    public Genetic(ArrayList<NQueenBoard> p){
        population = p;
        pairs = new int[p.size()];
        fitness = new int[p.size()];
    }

    //The Genetic Algorithm
    public void solve(){
        fitnessFunction();
    }

    //Storing the fitness value for each population member
    //These proportions represent the board's probability of being
    //Chosen for reproduction
    public void fitnessFunction(){
        int total = 0;

        Comparator<NQueenBoard> c = new GenerationSort();
        Collections.sort(population, c);

        for(int i = 0; i < population.size(); ++i) {
            population.get(i).setWeight(total);
            fitness[i] = population.get(i).fitness();
            total += fitness[i];
        }

        /*for(int i = 0; i < population.size(); ++i){
            fitness[i] = (int)Math.round(((double)pairs[i] / (double)total) * 100);
        }*/

        System.out.println(Arrays.toString(fitness));
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