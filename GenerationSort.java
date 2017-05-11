import java.util.Comparator;

/**
 * Created by marco on 5/10/2017.
 */
public class GenerationSort implements Comparator<NQueenBoard> {
    public int compare(NQueenBoard x, NQueenBoard y){
        if(x.fitness() < y.fitness())
            return -1;

        if(x.fitness() > y.fitness())
            return 1;

        return 0;
    }
}
