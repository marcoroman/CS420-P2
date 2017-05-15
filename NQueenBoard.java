import java.util.Random;

/**
 * Created by marco on 5/10/2017.
 */
public class NQueenBoard {
    private int[] board;
    private int fitness = 0;
    private int weight = 0;

    public NQueenBoard(int[] b){
        board = b;
        fitness = nonAttackingPairs();
    }

    /***************************************Mutators***************************************/

    public void setWeight(int w){
        weight = w;
    }

    public void decrementWeight(int d){
        weight -= d;
    }

    /***************************************Accessors***************************************/

    public int fitness(){
        return fitness;
    }

    public int[] getBoard(){
        return board;
    }

    public int getWeight(){
        return weight;
    }

    /***************************************Auxiliary Methods***************************************/

    //Returns number of NON-attacking queen pairs in the boards in the population
    public int nonAttackingPairs(){
        int pairs = 0;
        int relation;

        //Loop checks one column at a time; checks the queens the current
        //queen may not attack to its right horizontally or diagonally
        for(int i = 0; i < board.length; ++i){
            for(int j = i + 1; j < board.length; ++j){
                relation = j - i;
                if(!(board[i] == board[j]) && !(board[j] - relation == board[i]) && !(board[j] + relation == board[i]))
                    ++pairs;
            }
        }

        return pairs;
    }

    //Causes a mutation in the board by randomly selecting a queen and moving it
    //to a random position withinin its column
    public void mutate(){
        Random gen = new Random();
        board[gen.nextInt(board.length)] = gen.nextInt(board.length);
        fitness = nonAttackingPairs();
    }
}
