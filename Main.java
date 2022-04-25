import java.util.Scanner;

public class Main {
    private static final int NUMBER_OF_DIVISION = 2;
    private static final int NUMBER_OF_TERMS_PER_YEAR = 4;

    public static void main(String[] args) {

    }


    /**
     *
     * @return
     */
    public static String[] getNamesOfDivision() {
        String[] namesOfDivision = new String[NUMBER_OF_DIVISION];

        // Prompts user to enter names of each division of company
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < NUMBER_OF_DIVISION; i++) {
            System.out.println("Name of division [" + (i + 1) + "]? ");
            namesOfDivision[i] = in.nextLine();
        }
        return namesOfDivision;
    }

    /**
     *
     * @param namesOfDivision
     */
    public static void printDivision(String[] namesOfDivision) {
        // Prints division names
        for (int i = 0; i < NUMBER_OF_DIVISION; i++) {
            System.out.printf("Division %2d: %12s\n", i + 1, namesOfDivision[i]);
        }
    }


    /**
     *
     * @param namesOfDivision
     * @return
     */
    public static double[][] getQuarterlySalesPerDivision(String[] namesOfDivision) {
        // Define necessary variables
        double[][] quarterlySalesPerDivision = new double[NUMBER_OF_DIVISION][NUMBER_OF_TERMS_PER_YEAR];

        // Prompts user to enter sales one division by another
        for (int div=0; div<NUMBER_OF_DIVISION; div++) {

            // First prints the name of the division
            Scanner in = new Scanner(System.in);
            System.out.printf("Division %20s\n",namesOfDivision[div]);

            // Then prompts user to enter sales for each sales term
            for (int term=0; term<NUMBER_OF_TERMS_PER_YEAR; term++) {
                System.out.print("Term " + (term+1) + "?\t");
                quarterlySalesPerDivision[div][term] = in.nextDouble();
            }
        }
        return quarterlySalesPerDivision;
    }


    /**
     *
     * @param quarterlySalesPerDivision
     * @return
     */
    public static double[][] calcDiffBetweenTerms(double[][] quarterlySalesPerDivision){
        // Calculates the differences between quarters
        double[][] diffsBetweenTerms = new double[NUMBER_OF_DIVISION][NUMBER_OF_TERMS_PER_YEAR - 1];

        // Loop for each division
        for (int division = 0; division < NUMBER_OF_DIVISION; division++) {
            for (int diff = 0; diff < NUMBER_OF_TERMS_PER_YEAR - 1; diff++) {
                diffsBetweenTerms[division][diff] = quarterlySalesPerDivision[division][diff + 1] - quarterlySalesPerDivision[division][diff];
            }
        }
        return diffsBetweenTerms;
    }

    /**
     *
     */
    public static void printHeaderLine() {
        // Loop for printing header line
        // Print "Division" at 1st column
        System.out.printf("%20s%16s", "Division", "Term1");

        // For Term2 and over
        for (int term = 1; term < NUMBER_OF_TERMS_PER_YEAR; term++) {

            // Construct colum header String
            String termNum = "Term";
            termNum = termNum.concat(Integer.toString(term + 1));

            System.out.printf("%16s", termNum);
            System.out.printf("%14s", "(+/-)");
        }
        System.out.printf("\n");
    }

    /**
     *
     * @param namesOfDivision
     * @param quarterlySalesPerDivision
     * @param diffsBetweenTerms
     */
    public static void printContents(   String[] namesOfDivision,
                                        double[][] quarterlySalesPerDivision,
                                        double[][] diffsBetweenTerms) {
        // Loop for printing the contents
        for (int div = 0; div < NUMBER_OF_DIVISION; div++) {

            // Prints division name
            System.out.printf("%20s", namesOfDivision[div]);

            // Prints the first term
            System.out.printf("%,16.2f", quarterlySalesPerDivision[div][0]);

            // Prints the second term and over
            for (int term = 1; term < NUMBER_OF_TERMS_PER_YEAR; term++) {
                System.out.printf("%,16.2f", quarterlySalesPerDivision[div][term]);
                System.out.printf("(%+,14.2f)", diffsBetweenTerms[div][term - 1]);
            }
            System.out.printf("\n");
        }
    }
}
