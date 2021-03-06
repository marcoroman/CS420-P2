import java.util.Comparator;

/**
 * Created by marco on 5/10/2017.
 */

//This class sorts each generation of the genetic algorithm in order of increasing fitness value
//Each member's fitness value is the number of non-attacking queens on its board
public class GenerationSort implements Comparator<NQueenBoard> {
    public int compare(NQueenBoard x, NQueenBoard y){
        if(x.fitness() < y.fitness())
            return -1;

        if(x.fitness() > y.fitness())
            return 1;

        return 0;
    }
}
