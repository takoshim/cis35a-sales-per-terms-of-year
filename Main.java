import java.util.Scanner;

public class Main {
    private static final int NUMBER_OF_DIVISION = 6;

    public static void main(String[] args) {

        // Define necessary variables
        double[][] quarterlySalesPerDivision = new double[4][NUMBER_OF_DIVISION];
        String[] namesOfDivision = new String[NUMBER_OF_DIVISION];

        // Prompts user to enter names of each division of company
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < NUMBER_OF_DIVISION; i++) {
            System.out.println("Name of division [" + (i+1) + "]? ");
            namesOfDivision[i] = in.nextLine();
        }

        // Prints division names
        for (int i = 0; i < NUMBER_OF_DIVISION; i++) {
            System.out.printf("Division %2d: %10s\n", i + 1, namesOfDivision[i]);
        }
    }
}
