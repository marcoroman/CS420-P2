import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by marco on 5/8/2017.
 */

public class Driver {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int option = -1;

        System.out.println("\nN-Queen Problem Solver");

        while(option != 3){
            System.out.print("\n1) Solve by Hill-Climbing\n2) Solve using Genetic Algorithm\n3) Exit\n>");
            option = reader.nextInt();

            if(option == 1){
                hillClimbing(reader);
            }else if(option == 2){
                geneticAlgorithm(reader);
            }else if(option == 3){
                System.out.println("\nProgram exited.");
            }else{
                System.out.println("Please enter a valid option.");
            }
        }

        reader.close();
    }

    public static void hillClimbing(Scanner reader){
        int sub = -1;

        while(sub != 3){
            System.out.print("\n1) Solve single N-Queen problem (may fail)\n2) Run 200 cases\n3) Back\n>");
            sub = reader.nextInt();

            if(sub == 1){
                System.out.print("\nEnter number of queens (N): ");
                int n = reader.nextInt();

                int[] board = createBoard(n);
                HillClimbing hc = new HillClimbing(board);

                print(board);

                if(hc.solve()){
                    print(hc.getBoard());
                    System.out.println("Solution found.");
                }else
                    System.out.println("Hill climbing failed to find a solution.");
            }else if(sub == 2){
                System.out.print("\nEnter the number of queens (N): ");
                int n = reader.nextInt();
                int count = 0;

                for(int i = 0; i < 200; ++i){
                    int[] board = createBoard(n);
                    HillClimbing hc = new HillClimbing(board);

                    if(hc.solve())
                        ++count;
                }

                System.out.println("Hill climbing solved approximately " + (count / 2) + "% of problems.");
            }else{
                System.out.println("Please enter a valid option.");
            }
        }
    }

    public static void geneticAlgorithm(Scanner reader){
        System.out.print("Enter the number of queens (N): ");
        int n = reader.nextInt();

        int k = -1;

        while(k <= 3) {
            System.out.print("\nEnter the size of the population for the algorithm: ");
            k = reader.nextInt();
        }

        ArrayList<NQueenBoard> p = new ArrayList<>();

        for(int i = 0; i < k; ++i){
            p.add(new NQueenBoard(createBoard(n)));
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