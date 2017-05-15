import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by marco on 5/8/2017.
 */
public class HillClimbing {

    private static int[] board;
    private int cost = 0;

    public HillClimbing(int[] b){
        board = b;
    }

    //Hill climbing algorithm
    //Consider ALL possible next boards formed by moving ANY ONE queen
    //Evaluate h for EACH possibility
    //If none is better than current h, quit
    //Else, take the best as new current and repeat

    public boolean solve(){
        //The arraylist moves stores each different game board
        //attackingPairs stores the number of attacking queens per board
        ArrayList<int[]> moves = new ArrayList<>();
        ArrayList<Integer> attackingPairs = new ArrayList<>();
        int h = getAttackingPairs(board);

        while(true){
            moves.clear();
            attackingPairs.clear();

            //Generating every board that can result from moving any one queen
            for(int i = 0; i < board.length; ++i){
                for(int j = 0; j < board.length; ++j){

                    if(board[i] != j) {
                        int[] move = board.clone();
                        move[i] = j;

                        moves.add(move);
                        ++cost;
                        attackingPairs.add(getAttackingPairs(move));
                    }
                }
            }

            //Obtaining the value of the board with fewest attacking queens
            int min = Collections.min(attackingPairs);

            if(min >= h) {
                break;
            }else{
                //The index of the lowest attacking queens value related to its corresponding board
                board = moves.get(attackingPairs.indexOf(min)).clone();
                h = min;

                if(min == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    //Returns the total number of attacking queens in a given board
    public static int getAttackingPairs(int[] q){
        int pairs = 0;
        int relation;

        //Loop checks one column at a time; checks the queens the current
        //queen may attack to its right horizontally or diagonally
        for(int i = 0; i < q.length; ++i){
            for(int j = i + 1; j < q.length; ++j){
                relation = j - i;
                if(q[i] == q[j] || q[j] - relation == q[i] || q[j] + relation == q[i])
                    ++pairs;
            }
        }

        return pairs;
    }

    public static int[] getBoard(){
        return board;
    }

    public int getCost(){
        return cost;
    }
}