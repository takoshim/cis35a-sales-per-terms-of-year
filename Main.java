import java.util.Scanner;

public class Main {

    // Defines constants
    private static final int NUMBER_OF_DIVISION = 2;
    private static final int NUMBER_OF_TERMS_PER_YEAR = 4;

    public static void main(String[] args) {

        // Collects divisions' names
        String[] namesOfDivisions = new String[NUMBER_OF_DIVISION];
        getNames( namesOfDivisions, NUMBER_OF_DIVISION );

        // Constructs sales data
        double[][] salesData = constructSalesData(namesOfDivisions, NUMBER_OF_TERMS_PER_YEAR);
        // Prints sales data
//        for(int i=0; i<NUMBER_OF_DIVISION; i++) {
//            for (int j=0; j<NUMBER_OF_TERMS_PER_YEAR; j++) {
//                System.out.println("Division " + i + " Term" + j + ": " + salesData[i][j]);
//            }
//        }

        // Calculates the differences between terms for each division
        double[][] diffsPerDivision = new double[NUMBER_OF_DIVISION][NUMBER_OF_TERMS_PER_YEAR -1];
        for (int div =0; div<NUMBER_OF_DIVISION; div++) {
            diffsPerDivision[div] = calculateDiffsBetweenTerms(salesData[div]);
        }
        // Calculates the differences between terms for company
        double[] totalForCompany = calculateTotalsPerTerm(salesData);
        double[] diffsForCompany = calculateDiffsBetweenTerms(totalForCompany);
//        // Prints the differences
//        System.out.println("\n");
//        for (int i=0; i<NUMBER_OF_TERMS_PER_YEAR -1; i++) {
//            for (int j=0; j<NUMBER_OF_DIVISION; j++) {
//                System.out.println("Division " + j + " Term " + (i+1) + "compared to the previous: " + diffsPerDivision[j][i]);
//            }
//            System.out.println("For the whole company, Term " + (i+1) + "compared to the previous: " + diffsForCompany[i]);
//        }

        // Calculates the averages of each term
        double[] averagesPerTerm = calculateAveragesOfTerm(salesData);
//        // Prints the averages
//        System.out.println("\n");
//        for (int i= 0; i<NUMBER_OF_TERMS_PER_YEAR; i++) {
//            System.out.println("Average of Term " + i + ": " + averagesPerTerm[i]);
//        }

        String[] divisionsHighestSalesPerTerm = getNamesWithHighestSale(salesData);
    }
    /**
     * This function construct sales data for provided number of divisions and terms.
     * By prompting user to input sales data for specified number of term,
     * this function populate sales data for one division.
     * @param divisions
     * @param terms
     * @return
     */
    public static double[][] constructSalesData(String[] divisions, int terms) {
        Scanner in = new Scanner(System.in);
        double[][] salesData = new double[divisions.length][terms];
        for (int div=0; div<divisions.length; div++) {
            for (int term=0; term<terms; term++) {
                System.out.printf("Division %d sales for Term %d? ", div, term);
                salesData[div][term] = in.nextDouble();
            }
        }
        return salesData;
    }

    /**
     * This void function takes two parameters.
     * And prompts user to enter names for times specified by the second arg.
     * @param array
     * @param time
     */
    public static void getNames(String[] array, int time) {

        // Prompts user to enter names of each division of company
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < time; i++) {
            System.out.printf("Type name %d: ", i + 1);
            array[i] = in.nextLine();
        }
    }

    /**
     * This function return differences (changes) of sales between each term.
     * Returned data consists of array of double type size of the arg minus one.
     * Ex: if passed sales data are for total of 4 terms, returned array will consist of 3 elements.
     * Respectively represents: 1st data for diff between term2 and term1,
     * 2nd data for diff between term3 and term2, 3rd data for diff between term4 and term3.
     * @param sales
     * @return
     */
    public static double[] calculateDiffsBetweenTerms(double[] sales){

        double[] diffsAmongTerms = new double[sales.length -1];
        for (int i=0; i < sales.length -1; i++) {
            diffsAmongTerms[i] = sales[i +1] - sales[i];
        }
        return diffsAmongTerms;
    }

    /**
     * This function returns total sum of sales per terms.
     * @param salesData
     * @return
     */
    public static double[] calculateTotalsPerTerm(double[][] salesData) {

        int numberOfDivisions = salesData.length;
        int numberOfTerms = salesData[numberOfDivisions].length;

        double[] totalsPerTerms = new double[numberOfTerms];

        // Outer loop for each term
        for (int term=0; term < numberOfTerms; term++) {

            // Loop through division to sum up
            double totalOfTerm = 0;
            for (int div=0; div < numberOfDivisions -1; div++) {
                totalOfTerm += salesData[div][term];
            }
            // Store total for this term
            totalsPerTerms[term] = totalOfTerm;
        }
        return totalsPerTerms;
    }

    /**
     * This function returns averages sales per term.
     * This function calls function calculateTotalsPerTerm(salesData).
     * @param salesData
     * @return
     */
    public static double[] calculateAveragesOfTerm(double[][] salesData) {

        int numberOfDivisions = salesData.length;
        int numberOfTerms = salesData[numberOfDivisions].length;

        double[] totalsPerTerm = calculateTotalsPerTerm(salesData);
        double[] averagesOfTerm = new double[numberOfTerms];

        for (int term=0; term<numberOfTerms; term++) {
            averagesOfTerm[term] = totalsPerTerm[term] / numberOfDivisions -1;
        }
        return averagesOfTerm;
    }

    public static String[] getNamesWithHighestSale(double[][] salesData) {

    }


    /**
     * This function prints header for the table.
     */
    public static void printHeaderLine() {
        // Loop for printing header line
        // Prints  1st column
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
     * This function prints contents for the table.
     * @param namesOfDivision
     * @param salesData
     * @param diffsPerTerm
     */
    public static void printContents(String[] namesOfDivision, double[][] salesData, double[][] diffsPerTerm) {
        // Loop for printing the contents
        for (int div = 0; div < NUMBER_OF_DIVISION; div++) {

            // Prints division name
            System.out.printf("%20s", namesOfDivision[div]);

            // Prints the first term
            System.out.printf("%,16.2f", salesData[div][0]);

            // Prints the second term and over
            for (int term = 1; term < NUMBER_OF_TERMS_PER_YEAR; term++) {
                System.out.printf("%,16.2f", salesData[div][term]);
                System.out.printf("(%+,14.2f)", diffsPerTerm[div][term - 1]);
            }
            System.out.printf("\n");
        }
    }

    /**
     * This function prints the total row
     * @param totals
     * @param diffs
     */
    public static void printTotalRow(double[] totals, double[] diffs) {
        // Prints first column
        System.out.printf("%20s", "Total");

        // Prints the first term
        System.out.printf("%,16.2f", totals[0]);

        // Prints the second term and over
        for (int term = 1; term < NUMBER_OF_TERMS_PER_YEAR; term++) {
            System.out.printf("%,16.2f", totals[term]);
            System.out.printf("(%+,14.2f)", diffs[term - 1]);
        }
            System.out.printf("\n");
    }

    /**
     * This function prints the average row.
     * @param averages
     */
    public static void printAverageRow(double[] averages) {
        // Prints first column
        System.out.printf("%20s", "Average");

        // Prints the first term
        System.out.printf("%,16.2f", averages[0]);

        // Prints the second term and over
        for (int term = 1; term < NUMBER_OF_TERMS_PER_YEAR; term++) {
            System.out.printf("%,16.2f", averages[term]);
            System.out.printf("(%16s)", "");
        }
        System.out.printf("\n");
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
}
