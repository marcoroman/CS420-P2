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

    //Handles the steepest hill climbing option
    public static void hillClimbing(Scanner reader){
        int sub = -1;

        while(sub != 3){
            System.out.print("\n1) Solve single N-Queen problem (may fail)\n2) Run 200 test cases\n3) Back\n>");
            sub = reader.nextInt();

            //Option for attempting to solve a single case
            if(sub == 1){
                System.out.print("\nEnter number of queens (N): ");
                int n = reader.nextInt();

                int[] board = createBoard(n);
                HillClimbing hc = new HillClimbing(board);

                print(board);
                System.out.println("Original board.");

                if(hc.solve()){
                    print(hc.getBoard());
                    System.out.println("Solution found!");
                }else
                    System.out.println("Steepest hill-climbing failed to find a solution.");

            //Option for running 200 test cases (displays percent of solved cases)
            }else if(sub == 2){
                System.out.print("\nEnter the number of queens (N): ");
                int n = reader.nextInt();
                int count = 0;
                long start, stop, time = 0;

                System.out.println("Running 200 test cases.\n");

                for(int i = 0; i < 200; ++i){
                    int[] board = createBoard(n);
                    HillClimbing hc = new HillClimbing(board);

                    start = System.currentTimeMillis();

                    if(hc.solve())
                        ++count;

                    stop = System.currentTimeMillis();

                    time += (stop - start);
                }

                System.out.println("Steepest hill climbing solved approximately " + (count / 2) + "% of problems.");
                System.out.println("Average time: " + (time / 200.0) + " milliseconds");

            }else{
                if(sub != 3)
                    System.out.println("Please enter a valid option.");
            }
        }
    }

    //Handles the genetic algorithm option
    public static void geneticAlgorithm(Scanner reader){
        int n = -1;

        while(n < 4) {
            System.out.print("Enter the number of queens (N): ");
            n = reader.nextInt();
        }

        int k = -1;

        while(k <= 3) {
            System.out.print("\nEnter the size of the population for the algorithm: ");
            k = reader.nextInt();
        }

        //Populating the initial population with randomly generated boards
        ArrayList<NQueenBoard> p = new ArrayList<>();

        for(int i = 0; i < k; ++i){
            p.add(new NQueenBoard(createBoard(n)));
        }

        Genetic g = new Genetic(p);

        long start = System.currentTimeMillis();

        g.solve();

        long stop = System.currentTimeMillis();

        System.out.println("Total time: " + (stop - start) + " milliseconds");
    }

    //Returns a randomly generated board
    public static int[] createBoard(int size){
        Random gen = new Random();
        int[] board = new int[size];

        for(int i = 0; i < size; ++i)
            board[i] = gen.nextInt(size);

        return board;
    }

    //Translates N-Queen board represented as an array of integers
    //to a visual representation with Q's indicating a queen
    public static void print(int[] board){
        System.out.println();

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