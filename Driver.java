import java.util.Random;
import java.util.Scanner;

/**
 * Created by marco on 5/8/2017.
 */

public class Driver {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int count = 0;

        for(int i = 0; i < 100; ++i) {
            int[] b = createBoard(8);
            HillClimbing hc = new HillClimbing(b);

            if(hc.solve()){
                System.out.println();
                print(b);
                System.out.println("ORIGINAL BOARD\n");

                print(hc.getBoard());
                System.out.println("SOLUTION FOUND");
                ++count;
            }
        }
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