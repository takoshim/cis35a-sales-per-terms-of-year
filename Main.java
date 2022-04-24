import java.util.Scanner;

public class Main {
    private static final int NUMBER_OF_DIVISION = 6;

    public static void main(String[] args) {

        // Define necessary variables
        double[][] quarterlySalesPerDivision = new double[NUMBER_OF_DIVISION][4];
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

        // Prompts user to enter sales one division by another
        for (int div=0; div<NUMBER_OF_DIVISION; div++) {
            System.out.print("Division " + namesOfDivision[div]);
            for (int q=0; q<4; q++) {
                System.out.print("\tQ" + (q+1) + "?\t");
                quarterlySalesPerDivision[div][q] = in.nextDouble();
            }
        }

        // Prints header line
        System.out.printf("%8s %10.2s %10.2s %10.2s %10.2s\n", "Division", "Q1", "Q2", "Q3", "Q4");

        // Loop for each division
        for (int div=0; div<NUMBER_OF_DIVISION; div++) {

            // Prints name of the division
            System.out.printf("%8s ", namesOfDivision[div]);

            // Inner loop for quarterly sales for a division
            for (int q=0; q<4; q++) {
                System.out.printf("%10.2f ", quarterlySalesPerDivision[div][q]);
            }
            System.out.printf("\n");
        }
    }
}
