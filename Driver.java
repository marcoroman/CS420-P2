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

        for(int i = 0; i < 10; ++i){
            p.add(new NQueenBoard(createBoard(8)));
        }

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