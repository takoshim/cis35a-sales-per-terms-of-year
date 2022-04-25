import java.util.Scanner;

public class Main {

    // Defines static variables
    private static final int NUMBER_OF_DIVISION = 2;
    private static final int NUMBER_OF_TERMS_PER_YEAR = 4;

    public static void main(String[] args) {

        String[] userDivisions = new String[NUMBER_OF_DIVISION];
        getNamesInTime( userDivisions, NUMBER_OF_DIVISION );
        printNames( userDivisions );

        double[] dev1Sales = new double[NUMBER_OF_TERMS_PER_YEAR];
        getSalesInTerm( dev1Sales, NUMBER_OF_TERMS_PER_YEAR);

        double[] dev2Sales = new double[NUMBER_OF_TERMS_PER_YEAR];
        getSalesInTerm( dev2Sales, NUMBER_OF_TERMS_PER_YEAR);

//        double[][] userQuarterlySalesPerDivision = getQuarterlySalesPerDivision(userNamesOfDivision);
//        double[][] userDiffsBetweenTerms = calcDiffBetweenTerms(userQuarterlySalesPerDivision);
//        printHeaderLine();
//        printContents(userNamesOfDivision, userQuarterlySalesPerDivision, userDiffsBetweenTerms);
    }

    /**
     * This void function takes two parameters.
     * And prompts user to enter names for times specified by the second arg.
     * @param array
     * @param time
     */
    public static void getNamesInTime(String[] array, int time) {

        // Prompts user to enter names of each division of company
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < time; i++) {
            System.out.printf("Type name %d: ", i + 1);
            array[i] = in.nextLine();
        }
    }

    /**
     * This void function prints Strings passed by arg.
     * Each value in the array of String passed are printed with preceding number of line.
     * @param names
     */
    public static void printNames(String[] names) {
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d: %20s\n", i +1, names[i]);
        }
    }

    /**
     * This void function takes two parameters.
     * And prompts user to enter sales for times of terms specified by the second arg.
     * @param sales
     * @param terms
     */
    public static void getSalesInTerm (double[] sales, int terms) {

        // Prompts user to enter sales for times specified by second arg
        Scanner in = new Scanner(System.in);

        for (int i=0; i< terms; i++) {
            System.out.printf("Sales for Term %d?\n", i +1);
            sales[i] = in.nextDouble();
        }
    }


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
