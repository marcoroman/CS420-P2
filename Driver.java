import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by marco on 5/8/2017.
 */

public class Driver {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int count = 0;

        ArrayList<NQueenBoard> p = new ArrayList<>();

        int[] one = {2,4,7,4,8,5,5,2};
        int[] two = {3,2,7,5,2,4,1,1};
        int[] three = {2,4,4,1,5,1,2,4};
        int[] four = {3,2,5,4,3,2,1,3};

        p.add(new NQueenBoard(one));
        p.add(new NQueenBoard(two));
        p.add(new NQueenBoard(three));
        p.add(new NQueenBoard(four));

        Genetic g = new Genetic(p);
        g.solve();
    }

    public static int[] createBoard(int size){
        Random gen = new Random();
        int[] board = new int[size];

        for(int i = 0; i < size; ++i)
            board[i] = gen.nextInt(size);

        return board;
    }

    public static void print(int[] board){
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