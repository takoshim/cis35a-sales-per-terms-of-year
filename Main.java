import java.util.Scanner;

public class Main {
    private static final int NUMBER_OF_DIVISION = 2;
    private static final int NUMBER_OF_TERMS_PER_YEAR = 2;

    public static void main(String[] args) {

        // Define necessary variables
        double[][] quarterlySalesPerDivision = new double[NUMBER_OF_DIVISION][NUMBER_OF_TERMS_PER_YEAR];
        String[] namesOfDivision = new String[NUMBER_OF_DIVISION];

        // Prompts user to enter names of each division of company
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < NUMBER_OF_DIVISION; i++) {
            System.out.println("Name of division [" + (i+1) + "]? ");
            namesOfDivision[i] = in.nextLine();
        }

        // Prints division names
        for (int i = 0; i < NUMBER_OF_DIVISION; i++) {
            System.out.printf("Division %2d: %12s\n", i + 1, namesOfDivision[i]);
        }

        // Prompts user to enter sales one division by another
        for (int div=0; div<NUMBER_OF_DIVISION; div++) {

            // First prints the name of the division
            System.out.printf("Division %12s\n",namesOfDivision[div]);

            // Then prompts user to enter sales for each sales term
            for (int term=0; term<NUMBER_OF_TERMS_PER_YEAR; term++) {
                System.out.print("Term " + (term+1) + "?\t");
                quarterlySalesPerDivision[div][term] = in.nextDouble();
            }
        }

        // Calculates the differences between quarters


        // Loop for printing header line
        System.out.printf("%-18s", "Division");
        for (int term=0; term<NUMBER_OF_TERMS_PER_YEAR; term++) {
            String termNum = "Term".concat(Integer.toString(term +1));
            System.out.printf("%-18s", termNum);
        }
        System.out.printf("\n");


        // Loop for printing
        for (int div=0; div<NUMBER_OF_DIVISION; div++) {

            // Prints name of the division
            System.out.printf("%18s", namesOfDivision[div]);

            // Inner loop for quarterly sales for a division
            for (int term=0; term<NUMBER_OF_TERMS_PER_YEAR; term++) {
                System.out.printf("%18.2f", quarterlySalesPerDivision[div][term]);
            }
            System.out.printf("\n");
        }
    }
}
