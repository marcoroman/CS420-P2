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

    public int fitness(){
        return fitness;
    }

    public int[] getBoard(){
        return board;
    }

    public void setWeight(int w){
        weight = w;
    }

    public void decrementWeight(int d){
        weight -= d;
    }

    public int getWeight(){
        return weight;
    }

    //Returns number of NON-attacking queen pairs in the boards in the population
    public int nonAttackingPairs(){
        int pairs = 0;
        int relation;

        //Loop checks one column at a time; checks the queens the current
        //queen may not attack to its right horizontally or diagonally
        for(int i = 0; i < board.length; ++i){
            for(int j = i + 1; j < board.length; ++j){
                relation = j - i;
                if(!(board[i] == board[j] || board[j] - relation == board[i] || board[j] + relation == board[i]))
                    ++pairs;
            }
        }

        return pairs;
    }

    public void print(){
        for(int i = 0; i < board.length; ++i){
            for(int j = 0; j < board.length; ++j){
                if(i == board[j]){
                    System.out.print('Q' + " ");
                }else
                    System.out.print("*" + " ");
            }

            System.out.println();
        }
    }
}
