import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by marco on 5/8/2017.
 */
public class HillClimbing {

    private static int[] board;
    private static int pairs = 0;

    public HillClimbing(int[] b){
        board = b;
    }

    //Hill climbing algorithm
    //Consider ALL possible next boards formed by moving ANY ONE queen
    //Evaluate h for EACH possibility
    //If none is better than current h, quit
    //Else, take the best as new current and repeat

    public static boolean solve(){
        ArrayList<int[]> moves = new ArrayList<>();
        ArrayList<Integer> attackingPairs = new ArrayList<>();
        int h = getAttackingPairs(board);

        while(true){
            moves.clear();
            attackingPairs.clear();

            for(int i = 0; i < board.length; ++i){
                for(int j = 0; j < board.length; ++j){

                    if(board[i] != j) {
                        int[] move = board.clone();
                        move[i] = j;

                        moves.add(move);
                        attackingPairs.add(getAttackingPairs(move));
                    }
                }
            }

            int min = Collections.min(attackingPairs);

            if(min >= h) {
                break;
            }else{
                board = moves.get(attackingPairs.indexOf(min)).clone();
                h = min;

                if(min == 0) {
                    Driver.print(board);
                    System.out.println("SOLUTION FOUND");
                    return true;
                }
            }
        }

        return false;
    }

    public static int getAttackingPairs(int[] q){
        pairs = 0;
        int relation;

        for(int i = 0; i < q.length; ++i){
            for(int j = i + 1; j < q.length; ++j){
                relation = j - i;
                if(q[i] == q[j] || q[j] - relation == q[i] || q[j] + relation == q[i])
                    ++pairs;
            }
        }

        return pairs;
    }
}